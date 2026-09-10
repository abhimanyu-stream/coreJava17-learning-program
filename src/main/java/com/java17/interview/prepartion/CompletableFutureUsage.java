package com.java17.interview.prepartion;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture — Method Chaining Guide
 *
 * <pre>
 * Key chaining methods:
 *
 * Method              Input        Output       Use when
 * ─────────────────────────────────────────────────────────────────────────────
 * thenApply(fn)       T            CF&lt;R&gt;        transform result (sync)
 * thenApplyAsync(fn)  T            CF&lt;R&gt;        transform result (async, new thread)
 * thenAccept(fn)      T            CF&lt;Void&gt;     consume result, no return
 * thenAcceptAsync(fn) T            CF&lt;Void&gt;     consume result (async)
 * thenRun(fn)         –            CF&lt;Void&gt;     run after done, ignores result
 * thenCompose(fn)     T            CF&lt;R&gt;        chain another CF (flatMap)
 * thenCombine(cf,fn)  T + U        CF&lt;R&gt;        combine results of two CFs
 * exceptionally(fn)   Throwable    CF&lt;T&gt;        recover from exception (fallback)
 * handle(fn)          T,Throwable  CF&lt;R&gt;        success OR failure — always runs
 * whenComplete(fn)    T,Throwable  CF&lt;T&gt;        side effect on success or failure
 * allOf(cf...)        –            CF&lt;Void&gt;     wait for ALL to complete
 * anyOf(cf...)        –            CF&lt;Object&gt;   proceed when ANY one completes
 * </pre>
 */
public class CompletableFutureUsage {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // ══════════════════════════════════════════════════════════════════════
        // 1. Original: step-by-step (separate variables)
        //    supplyAsync → thenApplyAsync → thenAccept
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("═══ 1. Step-by-step (separate variables) ═══");

        CompletableFuture<String> userInfoFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching user info...");
            delay(1);
            return "User123";
        });

        CompletableFuture<Integer> creditScoreFuture = userInfoFuture.thenApplyAsync(userId -> {
            System.out.println("Fetching credit score for: " + userId);
            delay(1);
            return 700;
        });

        CompletableFuture<Void> combined = creditScoreFuture.thenAccept(score ->
                System.out.println("Credit score: " + score));

        combined.get(); // block main thread until pipeline completes

        // ══════════════════════════════════════════════════════════════════════
        // 2. Same pipeline written with METHOD CHAINING
        //    supplyAsync → thenApplyAsync → thenAccept → get()
        //
        //    Each method returns a new CompletableFuture, so we can chain
        //    directly without storing intermediate variables.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 2. Method chaining (same pipeline) ═══");

        // ── 2a. Happy path — no exception
        CompletableFuture.supplyAsync(() -> {
                    System.out.println("Fetching user info...");
                    delay(1);
                    return "User123";                                   // CF<String>
                })
                .thenApplyAsync(userId -> {                             // String → Integer
                    System.out.println("Fetching credit score for: " + userId);
                    delay(1);
                    return 700;                                         // CF<Integer>
                })
                .thenAccept(score ->                                    // Integer → void
                        System.out.println("Credit score: " + score))  // CF<Void>
                .get();                                                 // block until done

        // ── 2b. exceptionally() — recover with a fallback value when any stage throws
        //    If no exception occurs, exceptionally() is skipped entirely.
        //    fn receives the Throwable; must return a value of same type as the CF.
        System.out.println("\n── 2b. exceptionally (fallback on error) ──");
        CompletableFuture.supplyAsync(() -> {
                    System.out.println("Fetching user info...");
                    delay(1);
                    return "User123";
                })
                .thenApplyAsync(userId -> {
                    System.out.println("Fetching credit score for: " + userId);
                    delay(1);
                    throw new RuntimeException("Credit service down"); // simulated failure
                })
                .exceptionally(ex -> {
                    // receives wrapped CompletionException — getCause() gives the original
                    System.out.println("exceptionally caught : " + ex.getCause().getMessage());
                    return -1;                                          // fallback score
                })
                .thenAccept(score -> System.out.println("Credit score (fallback): " + score))
                .get();

        // ── 2c. handle() — ALWAYS runs, whether success or failure
        //    BiFunction<T, Throwable, R>
        //      result  = value from previous stage (null if exception)
        //      ex      = exception (null if success)
        //    Returns a new value that continues down the chain.
        System.out.println("\n── 2c. handle (always runs — success or failure) ──");
        CompletableFuture.supplyAsync(() -> {
                    System.out.println("Fetching user info...");
                    delay(1);
                    return "User123";
                })
                .thenApplyAsync(userId -> {
                    System.out.println("Fetching credit score for: " + userId);
                    delay(1);
                    throw new RuntimeException("Timeout fetching score"); // simulated failure
                })
                .handle((score, ex) -> {                               // BiFunction<Integer, Throwable, Integer>
                    if (ex != null) {
                        System.out.println("handle caught    : " + ex.getCause().getMessage());
                        return -1;                                     // recover
                    }
                    return score;                                      // pass through on success
                })
                .thenAccept(score -> System.out.println("Credit score (handle) : " + score))
                .get();

        // ── 2d. whenComplete() — side effect only, does NOT change the result
        //    BiConsumer<T, Throwable> — same args as handle() but returns void.
        //    Result/exception continues unchanged down the chain.
        System.out.println("\n── 2d. whenComplete (observe, don't change result) ──");
        CompletableFuture.supplyAsync(() -> {
                    System.out.println("Fetching user info...");
                    delay(1);
                    return "User123";
                })
                .thenApplyAsync(userId -> {
                    System.out.println("Fetching credit score for: " + userId);
                    delay(1);
                    return 720;
                })
                .whenComplete((score, ex) -> {                         // BiConsumer — no return
                    if (ex != null) {
                        System.out.println("whenComplete error: " + ex.getCause().getMessage());
                    } else {
                        System.out.println("whenComplete log  : pipeline finished, score=" + score);
                    }
                })
                .thenAccept(score -> System.out.println("Credit score (whenComplete): " + score))
                .get();

        // ── Quick comparison ────────────────────────────────────────────────
        // exceptionally(fn)   → only on failure  | changes result (fallback value)
        // handle(fn)          → always           | changes result (transform or recover)
        // whenComplete(fn)    → always           | does NOT change result (side effect only)

        // ══════════════════════════════════════════════════════════════════════
        // 3. Longer chain: supplyAsync → thenApply → thenApply → thenAccept
        //    Each thenApply transforms the value; thenAccept consumes it.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 3. Multi-step transform chain ═══");

        CompletableFuture.supplyAsync(() -> "  order-99  ")             // fetch raw order id
                .thenApply(String::trim)                                // clean whitespace
                .thenApply(String::toUpperCase)                         // normalise
                .thenApply(id -> "Processed: " + id)                   // enrich
                .thenAccept(System.out::println)                        // Processed: ORDER-99
                .get();

        // ══════════════════════════════════════════════════════════════════════
        // 4. thenCompose — chaining dependent async tasks (flatMap)
        //    Use thenCompose when the next step itself returns a CompletableFuture.
        //    thenApply would give CF<CF<R>>; thenCompose flattens it to CF<R>.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 4. thenCompose (async flatMap) ═══");

        CompletableFuture.supplyAsync(() -> "user-42")                  // step 1: get userId
                .thenCompose(userId ->                                  // step 2: use userId in another CF
                        CompletableFuture.supplyAsync(() -> {
                            delay(1);
                            return "Orders for " + userId + ": [ORD-1, ORD-2]";
                        })
                )
                .thenAccept(System.out::println)
                .get();

        // ══════════════════════════════════════════════════════════════════════
        // 5. thenCombine — merge results of two independent tasks
        //    Both run in parallel; combine when both finish.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 5. thenCombine (parallel + merge) ═══");

        CompletableFuture<String> userCF = CompletableFuture.supplyAsync(() -> {
            delay(1); return "Ravi";
        });
        CompletableFuture<Integer> scoreCF = CompletableFuture.supplyAsync(() -> {
            delay(1); return 750;
        });

        userCF.thenCombine(scoreCF, (name, score) ->
                        name + " has credit score: " + score)           // merge
                .thenAccept(System.out::println)                        // consume
                .get();

        // ══════════════════════════════════════════════════════════════════════
        // 6. exceptionally — recover from failure in the chain
        //    If any stage throws, exceptionally() provides a fallback value.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 6. exceptionally (fallback on error) ═══");

        CompletableFuture.supplyAsync(() -> {
                    if (true) throw new RuntimeException("DB connection failed");
                    return 800;
                })
                .exceptionally(ex -> {
                    System.out.println("Caught: " + ex.getMessage());
                    return -1;                                           // fallback score
                })
                .thenAccept(score -> System.out.println("Score (fallback): " + score))
                .get();

        // ══════════════════════════════════════════════════════════════════════
        // 7. handle — runs for BOTH success and failure (always executes)
        //    Unlike exceptionally(), handle() also has access to the result.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 7. handle (success or failure) ═══");

        CompletableFuture.supplyAsync(() -> "OK-data")
                .handle((result, ex) -> {
                    if (ex != null) return "Error: " + ex.getMessage();
                    return "Success: " + result;
                })
                .thenAccept(System.out::println)   // Success: OK-data
                .get();

        // ══════════════════════════════════════════════════════════════════════
        // 8. thenRun — run a Runnable after completion, ignores the result
        //    Useful for logging, metrics, cleanup.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 8. thenRun (post-completion side effect) ═══");

        CompletableFuture.supplyAsync(() -> "payment processed")
                .thenApply(String::toUpperCase)
                .thenAccept(r  -> System.out.println("Result  : " + r))
                .thenRun(()    -> System.out.println("Audit log written"))   // no access to result
                .get();

        // ══════════════════════════════════════════════════════════════════════
        // 9. allOf — wait for ALL parallel tasks, then collect results
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 9. allOf (wait for all) ═══");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> { delay(1); return "User:Ravi"; });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> { delay(1); return "Orders:3"; });
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> { delay(1); return "Balance:5000"; });

        CompletableFuture.allOf(cf1, cf2, cf3)   // wait for all three
                .thenRun(() -> {
                    try {
                        // .join() instead of .get() — no checked exception needed
                        System.out.println(cf1.join() + " | " + cf2.join() + " | " + cf3.join());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .get();

        // ══════════════════════════════════════════════════════════════════════
        // 10. Custom executor — run stages on a specific thread pool
        //     Pass executor as second arg to supplyAsync / thenApplyAsync
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══ 10. Custom executor ═══");

        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> "raw-data", pool)           // runs on pool
                .thenApplyAsync(data -> data.toUpperCase(), pool)       // runs on pool
                .thenAcceptAsync(result ->
                        System.out.println("On pool: " + result), pool)
                .get();

        pool.shutdown();

        System.out.println("\nAll pipelines complete.");
    }

    // ─── helper ──────────────────────────────────────────────────────────────
    private static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}
