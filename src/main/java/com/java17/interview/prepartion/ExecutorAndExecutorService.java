package com.java17.interview.prepartion;

public class ExecutorAndExecutorService {


}
/**
 *
 * Java provides several Executor and ExecutorService implementations for different scenarios, all of which manage thread pools to efficiently execute concurrent tasks. Here’s a list of the primary executor pool provider classes and when to use each.
 *
 * 1. SingleThreadExecutor
 * Class: Executors.newSingleThreadExecutor()
 * Usage Scenario: Use when tasks need to be executed sequentially, in a single background thread, ensuring that tasks are processed one after another without concurrency.
 * Example:
 * java
 * Copy code
 * ExecutorService executor = Executors.newSingleThreadExecutor();
 * executor.submit(() -> System.out.println("Task executed by a single thread"));
 * 2. FixedThreadPool
 * Class: Executors.newFixedThreadPool(int nThreads)
 * Usage Scenario: Best when the number of concurrent tasks is known and does not change, such as a server handling a fixed number of clients. Limits the number of threads to nThreads, reusing them for different tasks.
 * Example:
 * java
 * Copy code
 * ExecutorService executor = Executors.newFixedThreadPool(4);
 * for (int i = 0; i < 10; i++) {
 *     executor.submit(() -> System.out.println("Task executed by a fixed thread pool"));
 * }
 * 3. CachedThreadPool
 * Class: Executors.newCachedThreadPool()
 * Usage Scenario: Suitable for a large number of short-lived asynchronous tasks where threads may sit idle often, such as lightweight background tasks or handling HTTP requests. The pool grows as needed but reuses existing threads if possible.
 * Example:
 * java
 * Copy code
 * ExecutorService executor = Executors.newCachedThreadPool();
 * for (int i = 0; i < 10; i++) {
 *     executor.submit(() -> System.out.println("Task executed by a cached thread pool"));
 * }
 * 4. ScheduledThreadPool
 * Class: Executors.newScheduledThreadPool(int corePoolSize)
 * Usage Scenario: Ideal for periodic or delayed task execution, like scheduled maintenance tasks or repeated checks. Threads are reused across scheduled tasks.
 * Example:
 * java
 * Copy code
 * ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
 * scheduler.scheduleAtFixedRate(() -> System.out.println("Scheduled task executed"), 0, 10, TimeUnit.SECONDS);
 * 5. WorkStealingPool (Java 8 and above)
 * Class: Executors.newWorkStealingPool(int parallelism)
 * Usage Scenario: Best suited for large numbers of small, asynchronous tasks that can benefit from load balancing across multiple cores, such as large parallel computations. Threads steal tasks from each other to maintain high utilization.
 * Example:
 * java
 * Copy code
 * ExecutorService executor = Executors.newWorkStealingPool();
 * for (int i = 0; i < 10; i++) {
 *     executor.submit(() -> System.out.println("Task executed by a work-stealing pool"));
 * }
 * 6. ForkJoinPool
 * Class: new ForkJoinPool(int parallelism)
 * Usage Scenario: Optimized for tasks that can be recursively split into smaller sub-tasks (divide and conquer approach), like processing large datasets or recursive algorithms. Uses a work-stealing algorithm to maximize efficiency.
 * Example:
 * java
 * Copy code
 * ForkJoinPool forkJoinPool = new ForkJoinPool(4);
 * forkJoinPool.submit(() -> System.out.println("Task executed by ForkJoinPool"));
 * 7. SingleThreadScheduledExecutor
 * Class: Executors.newSingleThreadScheduledExecutor()
 * Usage Scenario: Useful when tasks need to be scheduled on a single thread, but also require scheduling at fixed intervals or with delays. For example, periodic maintenance tasks in a single-threaded environment.
 * Example:
 * java
 * Copy code
 * ScheduledExecutorService singleScheduler = Executors.newSingleThreadScheduledExecutor();
 * singleScheduler.schedule(() -> System.out.println("Single-thread scheduled task"), 5, TimeUnit.SECONDS);
 * 8. VirtualThreadPerTaskExecutor (Java 19 and above - Preview Feature)
 * Class: Executors.newVirtualThreadPerTaskExecutor()
 * Usage Scenario: Optimized for massive concurrency by creating lightweight virtual threads instead of OS threads. Suitable for high-throughput applications like handling a large number of concurrent user requests or microservices, without being limited by the number of OS threads.
 * Example:
 * java
 * Copy code
 * ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
 * for (int i = 0; i < 10; i++) {
 *     executor.submit(() -> System.out.println("Task executed by a virtual thread pool"));
 * }
 * Summary Table
 * Executor Pool	Class	Use Case
 * SingleThreadExecutor	newSingleThreadExecutor()	Sequential task execution, no concurrency.
 * FixedThreadPool	newFixedThreadPool(int n)	Fixed number of threads for predictable task loads.
 * CachedThreadPool	newCachedThreadPool()	Short-lived tasks, dynamically growing pool.
 * ScheduledThreadPool	newScheduledThreadPool(int n)	Delayed or periodic task execution.
 * WorkStealingPool	newWorkStealingPool(int n)	Large, asynchronous, parallelizable tasks.
 * ForkJoinPool	new ForkJoinPool(int n)	Divide-and-conquer recursive tasks.
 * SingleThreadScheduledExecutor	newSingleThreadScheduledExecutor()	Single-threaded, periodic execution.
 * VirtualThreadPerTaskExecutor (Java 19+)	newVirtualThreadPerTaskExecutor()	Massive concurrency with lightweight threads.
 * Each executor type provides a unique set of capabilities for different workload patterns, making it possible to efficiently handle both lightweight and resource-intensive tasks.
 *
 *
 * */