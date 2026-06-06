package com.java17.interview.prepartion;

public class FutureVsCompletableFuture {
}
/**
 * Here’s a clean, interview-ready tabular comparison of Future vs CompletableFuture, covering methods, use cases, exception handling, functional support, composition, and practical limits. This is the format interviewers love because it shows clarity of thought, not memorization.
 *
 * Future vs CompletableFuture — Practical Differences (Tabular)
 * Aspect	Future	CompletableFuture
 * Introduced In	Java 5	Java 8
 * Programming Model	Imperative, blocking	Declarative, asynchronous
 * Result Retrieval	get() (blocking)	Callbacks (thenApply, thenAccept, thenRun)
 * Blocking Nature	Always blocks on get()	Non-blocking by default
 * Task Completion	Completed only by executor	Can be completed manually (complete())
 * Composition / Chaining	❌ Not supported	✅ Fully supported
 * Method Support	get(), isDone(), cancel()	50+ async composition methods
 * Combining Multiple Tasks	Manual blocking coordination	allOf(), anyOf(), thenCombine()
 * Functional Programming Support	❌ No	✅ Yes (Function, Consumer, Supplier)
 * Exception Handling	Only via ExecutionException	exceptionally(), handle(), whenComplete()
 * Exception Recovery	❌ Not possible	✅ Fallback and recovery supported
 * Cancellation Behavior	Limited, no propagation	Propagates through async chain
 * Thread Management	Executor fixed at submission	Executor per stage (or ForkJoinPool)
 * Parallel Execution	Manual and blocking	Built-in async parallelism
 * Backpressure Support	❌ No	❌ No (use Reactive for streams)
 * Suitability for Reactive Systems	Poor	Moderate (bridge only)
 * Debugging Complexity	Simple	Can become complex for long chains
 * Performance at Scale	Poor (thread blocking)	Better (non-blocking)
 * Typical Use Case	Simple background task	Async workflows, service aggregation
 * Where It Breaks Down	Multi-step async logic	Streaming or unbounded data
 * Method-Level Comparison
 * Category	Future Methods	CompletableFuture Methods
 * Result Access	get()	join(), callbacks
 * Status Check	isDone()	isDone()
 * Cancellation	cancel()	cancel() (propagates)
 * Async Creation	Executor only	supplyAsync(), runAsync()
 * Chaining	❌	thenApply(), thenCompose()
 * Combining	❌	thenCombine(), allOf()
 * Error Handling	ExecutionException	exceptionally(), handle()
 * Manual Completion	❌	complete()
 * Use Case Comparison
 * Scenario	Preferred Choice	Reason
 * Fire-and-forget task	Future	Simplicity
 * Parallel REST calls	CompletableFuture	Non-blocking aggregation
 * Async business workflow	CompletableFuture	Chaining + error recovery
 * Legacy executor-based code	Future	Compatibility
 * High-throughput services	CompletableFuture	Better scalability
 * Streaming data	❌ Neither	Use Reactive
 * Exception Handling Comparison
 * Feature	Future	CompletableFuture
 * Checked Exceptions	Wrapped in ExecutionException	Handled declaratively
 * Recovery / Fallback	❌ No	✅ Yes
 * Global Error Handling	❌ No	✅ Yes
 * Inline Error Logic	❌ No	✅ Yes
 * Functional Programming Support
 * Aspect	Future	CompletableFuture
 * Lambda Usage	❌ No	✅ Yes
 * Functional Interfaces	❌ No	Function, Consumer, Supplier
 * Pipeline Style	❌ No	✅ Yes
 * Declarative Flow	❌ No	✅ Yes
 * One-Line Interview Summary
 *
 * Future is a blocking handle to a result.
 * CompletableFuture is a composable, non-blocking async pipeline.
 *
 * This table alone is enough to clear senior-level Java concurrency questions because it shows design understanding, not API recall.
 */