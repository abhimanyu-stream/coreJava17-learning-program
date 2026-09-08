package com.java17.interview.prepartion;

import java.util.concurrent.*;

/**
 * Future vs CompletableFuture — Practical Interview Guide
 *
 * <pre>
 * One-line summary:
 *   Future              = a blocking handle to a future result
 *   CompletableFuture   = a composable, non-blocking async pipeline
 * </pre>
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * OVERALL COMPARISON
 * ─────────────────────────────────────────────────────────────────────────────
 * <pre>
 * Aspect                        Future                  CompletableFuture
 * ──────────────────────────────────────────────────────────────────────────
 * Introduced In                 Java 5                  Java 8
 * Programming Model             Imperative, blocking    Declarative, async
 * Result Retrieval              get() blocks            Callbacks (thenApply…)
 * Blocking Nature               Always blocks           Non-blocking by default
 * Manual Completion             ❌ No                   ✅ complete(value)
 * Chaining / Composition        ❌ Not supported        ✅ Fully supported
 * Combining Multiple Tasks      Manual + blocking       allOf(), anyOf()
 * Functional Interface Support  ❌ No                   ✅ Function/Consumer/Supplier
 * Exception Handling            ExecutionException only  exceptionally(), handle()
 * Exception Recovery / Fallback ❌ No                   ✅ Yes
 * Cancellation Propagation      ❌ Limited              ✅ Propagates through chain
 * Thread Management             Fixed at submission      Per stage or ForkJoinPool
 * Performance at Scale          Poor (thread blocking)  Better (non-blocking)
 * Reactive/Streaming Support    ❌ No                   ❌ No (use Project Reactor)
 * Typical Use Case              Simple background task  Async workflows, aggregation
 * </pre>
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * METHOD-LEVEL COMPARISON
 * ─────────────────────────────────────────────────────────────────────────────
 * <pre>
 * Category           Future                  CompletableFuture
 * ──────────────────────────────────────────────────────────────────────────
 * Result Access      get()                   get() / join() / callbacks
 * Status Check       isDone()                isDone()
 * Cancellation       cancel()                cancel() — propagates
 * Async Creation     Executor only           supplyAsync() / runAsync()
 * Transform Result   ❌                      thenApply(Function)
 * Chain Async Task   ❌                      thenCompose(Function)
 * Consume Result     ❌                      thenAccept(Consumer)
 * Run after done     ❌                      thenRun(Runnable)
 * Combine two tasks  ❌                      thenCombine() / allOf() / anyOf()
 * Error handling     ExecutionException      exceptionally() / handle()
 * Manual Completion  ❌                      complete() / completeExceptionally()
 * </pre>
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * EXCEPTION HANDLING COMPARISON
 * ─────────────────────────────────────────────────────────────────────────────
 * <pre>
 * Feature                    Future                  CompletableFuture
 * ──────────────────────────────────────────────────────────────────────────
 * Checked Exceptions          Wrapped in              Handled declaratively
 *                             ExecutionException
 * Recovery / Fallback         ❌ No                   ✅ exceptionally()
 * Global Error Handling       ❌ No                   ✅ handle() / whenComplete()
 * Inline Error Logic          ❌ No                   ✅ Yes — in the chain
 * </pre>
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * USE CASE GUIDE
 * ─────────────────────────────────────────────────────────────────────────────
 * <pre>
 * Scenario                      Preferred               Reason
 * ──────────────────────────────────────────────────────────────────────────
 * Simple fire-and-forget         Future                 Simpler API
 * Parallel REST/service calls    CompletableFuture      Non-blocking aggregation
 * Multi-step async workflow      CompletableFuture      Chaining + error recovery
 * Legacy executor-based code     Future                 Compatibility
 * High-throughput microservices  CompletableFuture      Better scalability
 * Streaming / unbounded data     ❌ Neither             Use Project Reactor / RxJava
 * </pre>
 */
public class FutureVsCompletableFuture {

    // ─── 1. Future — blocking get() ──────────────────────────────────────────
    // Simple background task. Caller BLOCKS at get() until the task finishes.
    static void futureExample() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(500);
            return 42;
        });

        // Blocks here until task completes
        Integer result = future.get();                      // blocking
        System.out.println("Future result     : " + result); // 42

        // get() with timeout — throws TimeoutException if not done in time
        // future.get(2, TimeUnit.SECONDS);

        executor.shutdown();
    }

    // ─── 2. CompletableFuture — non-blocking callbacks ───────────────────────
    // thenApply  : transform result       (Function<T, R>)
    // thenAccept : consume result         (Consumer<T>)
    // thenRun    : run after completion   (Runnable — no result)
    static void completableFutureBasic() throws Exception {
        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> {
                    return 10;                              // runs on ForkJoinPool
                })
                .thenApply(n -> n * 2)                     // transform: 10 → 20
                .thenApply(n -> n + 5);                    // transform: 20 → 25

        System.out.println("CF basic          : " + cf.get()); // 25
    }

    // ─── 3. Exception handling — Future vs CompletableFuture ─────────────────
    static void exceptionHandling() throws Exception {

        // Future: exception surfaces only when get() is called
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> badFuture = executor.submit(() -> {
            throw new RuntimeException("Task failed");
        });
        try {
            badFuture.get();
        } catch (ExecutionException e) {
            // Must unwrap cause — cannot recover inline
            System.out.println("Future exception  : " + e.getCause().getMessage());
        }
        executor.shutdown();

        // CompletableFuture: recover inline with exceptionally()
        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> {
                    if (true) throw new RuntimeException("CF failed");
                    return 99;
                })
                .exceptionally(ex -> {
                    System.out.println("CF exception      : " + ex.getMessage());
                    return -1;                              // fallback value
                });
        System.out.println("CF recovered      : " + cf.get()); // -1

        // handle() — runs whether success or failure; gives both result and exception
        CompletableFuture<String> handled = CompletableFuture
                .supplyAsync(() -> "OK")
                .handle((result, ex) -> ex != null ? "Error: " + ex.getMessage() : result);
        System.out.println("CF handle()       : " + handled.get()); // OK
    }

    // ─── 4. Combining tasks — allOf / thenCombine ────────────────────────────
    // allOf() — wait for ALL tasks to complete (like parallel service calls)
    // anyOf() — proceed when ANY one task completes first
    static void combiningTasks() throws Exception {
        CompletableFuture<String> userCall    = CompletableFuture.supplyAsync(() -> "User:Ravi");
        CompletableFuture<String> orderCall   = CompletableFuture.supplyAsync(() -> "Orders:3");
        CompletableFuture<String> paymentCall = CompletableFuture.supplyAsync(() -> "Balance:5000");

        // Wait for all, then collect results
        CompletableFuture<Void> all = CompletableFuture.allOf(userCall, orderCall, paymentCall);
        all.get();  // waits for all three

        System.out.println("allOf results     : "
                + userCall.get() + " | " + orderCall.get() + " | " + paymentCall.get());

        // thenCombine() — combine results of exactly two tasks
        CompletableFuture<String> combined = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenCombine(
                        CompletableFuture.supplyAsync(() -> " World"),
                        (a, b) -> a + b
                );
        System.out.println("thenCombine()     : " + combined.get()); // Hello World
    }

    // ─── 5. thenCompose — chaining async tasks (flatMap equivalent) ──────────
    // thenApply   wraps the result in another CompletableFuture → CF<CF<T>>  ❌
    // thenCompose flattens it                                    → CF<T>     ✅
    static void thenComposeExample() throws Exception {
        CompletableFuture<String> result = CompletableFuture
                .supplyAsync(() -> "orderId-123")
                .thenCompose(orderId ->
                        CompletableFuture.supplyAsync(() -> "Payment for " + orderId)
                );
        System.out.println("thenCompose()     : " + result.get());
    }

    // ─── 6. Manual completion ─────────────────────────────────────────────────
    // complete() allows external code to resolve a CompletableFuture.
    // Useful for bridging callbacks or testing.
    static void manualCompletion() throws Exception {
        CompletableFuture<String> promise = new CompletableFuture<>();

        // Another thread / callback resolves it later
        new Thread(() -> {
            try { Thread.sleep(200); } catch (InterruptedException e) {}
            promise.complete("Resolved externally");
        }).start();

        System.out.println("Manual complete   : " + promise.get()); // waits for .complete()
    }

    // ─── main — run all examples ─────────────────────────────────────────────
    public static void main(String[] args) throws Exception {
        System.out.println("=== Future ===");
        futureExample();

        System.out.println("\n=== CompletableFuture Basic ===");
        completableFutureBasic();

        System.out.println("\n=== Exception Handling ===");
        exceptionHandling();

        System.out.println("\n=== Combining Tasks ===");
        combiningTasks();

        System.out.println("\n=== thenCompose ===");
        thenComposeExample();

        System.out.println("\n=== Manual Completion ===");
        manualCompletion();
    }
}
