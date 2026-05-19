package com.java17.interview.prepartion;

public class NotificationStrategz {
    public static void main(String[] args) {
        //Notification Strategy
        //Email, SMS, Push Notification

        String notificationType = "SMS";

        switch (notificationType) {
            case "Email" -> System.out.println("Sending Email Notification");
            case "SMS" -> System.out.println("Sending SMS Notification");
            case "Push" -> System.out.println("Sending Push Notification");
            default -> System.out.println("Unknown Notification Type");
        }
    }

    /**
     * Here’s a practical, side-by-side comparison of parallelStream() vs ExecutorService, plus runnable Core Java examples so you can see the difference clearly.
     *
     * ⚔️ parallelStream vs ExecutorService
     * Feature	               parallelStream()	            ExecutorService
     * Ease of use	         ✅ Very simple (1 line)	   ❌ More boilerplate
     * Control over threads	 ❌ Uses common ForkJoinPool	✅ Full control (pool size, queue)
     * Best for	               CPU-bound tasks	           IO-bound / async / controlled workloads
     * Ordering	Maintained     (if needed)	               Must manage manually
     * Error handling	        Harder	                   Easier with Future
     * Performance tuning	   Limited	                   Highly customizable
     * Production usage	       Limited control	           Preferred
     * 🧪 Example Problem
     *
     * Same pipeline:
     *
     * Filter available strategies
     * Sort by priority
     * Extract names
     * ✅ 1. Using parallelStream()
     * List<String> result = strategies.parallelStream()
     *         .filter(NotificationStrategy::isAvailable)
     *         .sorted(Comparator.comparingInt(NotificationStrategy::getPriority).reversed())
     *         .map(NotificationStrategy::getChannelName)
     *         .collect(Collectors.toList());
     * ✔ Pros
     * Very clean
     * Minimal code
     * Automatic parallelism
     * ❌ Cons
     * Uses common ForkJoinPool
     * Can interfere with other parallel tasks in app
     * Not ideal for blocking calls (DB/API)
     *
     *
     */


    /***
     * 2. Using ExecutorService
     * import java.util.concurrent.*;
     *
     * ExecutorService executor = Executors.newFixedThreadPool(4);
     *
     * List<Callable<String>> tasks = new ArrayList<>();
     *
     * for (NotificationStrategy strategy : strategies) {
     *     tasks.add(() -> {
     *         if (strategy.isAvailable()) {
     *             return strategy.getChannelName() + ":" + strategy.getPriority();
     *         }
     *         return null;
     *     });
     * }
     *
     * List<Future<String>> futures = executor.invokeAll(tasks);
     *
     * List<String> result = new ArrayList<>();
     *
     * for (Future<String> future : futures) {
     *     String value = future.get();
     *     if (value != null) {
     *         result.add(value);
     *     }
     * }
     *
     * // Manual sort
     * result.sort((a, b) -> {
     *     int p1 = Integer.parseInt(a.split(":")[1]);
     *     int p2 = Integer.parseInt(b.split(":")[1]);
     *     return Integer.compare(p2, p1);
     * });
     *
     * executor.shutdown();
     * ✔ Pros
     * Full control over threads
     * Safe for IO operations
     * Better error handling
     * Can add retries, timeouts, batching
     * ❌ Cons
     * More code
     * Manual coordination needed
     */


    /**
     * Real Difference (Critical Insight)
     * ⚠️ parallelStream() uses:
     *
     * 👉 Common ForkJoinPool
     *
     * ForkJoinPool.commonPool()
     *
     * This means:
     *
     * Shared across entire JVM
     * Can cause thread starvation
     * Bad for blocking tasks
     * ✅ ExecutorService lets you control:
     * Executors.newFixedThreadPool(10)
     *
     * You decide:
     *
     * Thread count
     * Queue size
     * Rejection policy
     * 🧠 When to Use What
     * ✅ Use parallelStream() when:
     * CPU-heavy tasks
     * Large collections
     * Stateless operations
     * No blocking calls
     * ✅ Use ExecutorService when:
     * Calling APIs / DB / network
     * Need retries / timeout
     * Need custom thread pool
     * Production-grade systems
     * 🚀 Interview Killer Answer
     *
     * If interviewer asks:
     *
     * “parallelStream vs ExecutorService?”
     *
     * Say this:
     *
     * “parallelStream is good for simple CPU-bound parallelism, but it uses the common ForkJoinPool which I can’t control.
     * In production, especially for IO or external calls, I prefer ExecutorService because it gives me control over thread pools, error handling, and scalability.”
     *
     *
     */
}


