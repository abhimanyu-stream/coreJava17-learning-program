package com.java17.interview.prepartion;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerLinkedBlockingQueueSemaphore {

    private static final int CAPACITY = 10;
    private static final int MAX_PRODUCE_VALUE = 50;

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        Semaphore emptySlots = new Semaphore(CAPACITY);  // initially all empty
        Semaphore filledSlots = new Semaphore(0);        // nothing to consume
        Semaphore mutex = new Semaphore(1);              // mutual exclusion

        Thread producer = new Thread(
                new Producer(queue, emptySlots, filledSlots, mutex),
                "Producer");

        Thread consumer = new Thread(
                new Consumer(queue, emptySlots, filledSlots, mutex),
                "Consumer");

        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {

        private final Queue<Integer> queue;
        private final Semaphore emptySlots;
        private final Semaphore filledSlots;
        private final Semaphore mutex;

        public Producer(Queue<Integer> queue,
                        Semaphore emptySlots,
                        Semaphore filledSlots,
                        Semaphore mutex) {
            this.queue = queue;
            this.emptySlots = emptySlots;
            this.filledSlots = filledSlots;
            this.mutex = mutex;
        }

        @Override
        public void run() {
            int value = 0;

            try {
                while (value < MAX_PRODUCE_VALUE) {

                    emptySlots.acquire();  // wait if no space

                    mutex.acquire();       // enter critical section
                    value++;
                    queue.add(value);
                    System.out.println("Produced: " + value);
                    mutex.release();       // exit critical section

                    filledSlots.release(); // signal item available

                    Thread.sleep(500);
                }

                System.out.println("Producer finished production.");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {

        private final Queue<Integer> queue;
        private final Semaphore emptySlots;
        private final Semaphore filledSlots;
        private final Semaphore mutex;

        public Consumer(Queue<Integer> queue,
                        Semaphore emptySlots,
                        Semaphore filledSlots,
                        Semaphore mutex) {
            this.queue = queue;
            this.emptySlots = emptySlots;
            this.filledSlots = filledSlots;
            this.mutex = mutex;
        }

        @Override
        public void run() {
            try {
                while (true) {

                    filledSlots.acquire();  // wait if nothing to consume

                    mutex.acquire();        // enter critical section
                    Integer value = queue.poll();
                    System.out.println("Consumed: " + value);
                    mutex.release();        // exit critical section

                    emptySlots.release();   // signal space available

                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
/**
 * Now we enter the elegant territory: Semaphore-based backpressure.
 * This is clean, explicit, and very interview-friendly.
 *
 * Conceptually:
 *
 * emptySlots → how many items producer can produce
 *
 * filledSlots → how many items consumer can consume
 *
 * mutex → protects queue consistency
 *
 * This is the classic Dijkstra producer–consumer pattern, but implemented with modern Java.
 *
 * Here is your ProducerConsumerLinkedBlockingQueueSemaphore:
 *
 * package com.java17.interview.prepartion;
 *
 * import java.util.LinkedList;
 * import java.util.Queue;
 * import java.util.concurrent.Semaphore;
 *
 * public class ProducerConsumerLinkedBlockingQueueSemaphore {
 *
 *     private static final int CAPACITY = 10;
 *     private static final int MAX_PRODUCE_VALUE = 50;
 *
 *     public static void main(String[] args) {
 *
 *         Queue<Integer> queue = new LinkedList<>();
 *
 *         Semaphore emptySlots = new Semaphore(CAPACITY);  // initially all empty
 *         Semaphore filledSlots = new Semaphore(0);        // nothing to consume
 *         Semaphore mutex = new Semaphore(1);              // mutual exclusion
 *
 *         Thread producer = new Thread(
 *                 new Producer(queue, emptySlots, filledSlots, mutex),
 *                 "Producer");
 *
 *         Thread consumer = new Thread(
 *                 new Consumer(queue, emptySlots, filledSlots, mutex),
 *                 "Consumer");
 *
 *         producer.start();
 *         consumer.start();
 *     }
 *
 *     static class Producer implements Runnable {
 *
 *         private final Queue<Integer> queue;
 *         private final Semaphore emptySlots;
 *         private final Semaphore filledSlots;
 *         private final Semaphore mutex;
 *
 *         public Producer(Queue<Integer> queue,
 *                         Semaphore emptySlots,
 *                         Semaphore filledSlots,
 *                         Semaphore mutex) {
 *             this.queue = queue;
 *             this.emptySlots = emptySlots;
 *             this.filledSlots = filledSlots;
 *             this.mutex = mutex;
 *         }
 *
 *         @Override
 *         public void run() {
 *             int value = 0;
 *
 *             try {
 *                 while (value < MAX_PRODUCE_VALUE) {
 *
 *                     emptySlots.acquire();  // wait if no space
 *
 *                     mutex.acquire();       // enter critical section
 *                     value++;
 *                     queue.add(value);
 *                     System.out.println("Produced: " + value);
 *                     mutex.release();       // exit critical section
 *
 *                     filledSlots.release(); // signal item available
 *
 *                     Thread.sleep(500);
 *                 }
 *
 *                 System.out.println("Producer finished production.");
 *
 *             } catch (InterruptedException e) {
 *                 Thread.currentThread().interrupt();
 *             }
 *         }
 *     }
 *
 *     static class Consumer implements Runnable {
 *
 *         private final Queue<Integer> queue;
 *         private final Semaphore emptySlots;
 *         private final Semaphore filledSlots;
 *         private final Semaphore mutex;
 *
 *         public Consumer(Queue<Integer> queue,
 *                         Semaphore emptySlots,
 *                         Semaphore filledSlots,
 *                         Semaphore mutex) {
 *             this.queue = queue;
 *             this.emptySlots = emptySlots;
 *             this.filledSlots = filledSlots;
 *             this.mutex = mutex;
 *         }
 *
 *         @Override
 *         public void run() {
 *             try {
 *                 while (true) {
 *
 *                     filledSlots.acquire();  // wait if nothing to consume
 *
 *                     mutex.acquire();        // enter critical section
 *                     Integer value = queue.poll();
 *                     System.out.println("Consumed: " + value);
 *                     mutex.release();        // exit critical section
 *
 *                     emptySlots.release();   // signal space available
 *
 *                     Thread.sleep(1000);
 *                 }
 *
 *             } catch (InterruptedException e) {
 *                 Thread.currentThread().interrupt();
 *             }
 *         }
 *     }
 * }
 *
 * Let’s decode the elegance:
 *
 * emptySlots.acquire()
 * → Producer blocks automatically when capacity is full.
 *
 * filledSlots.acquire()
 * → Consumer blocks automatically when nothing exists.
 *
 * mutex
 * → Ensures queue modification is atomic.
 *
 * No busy waiting.
 * No wait() / notify().
 * No manual size checking.
 * Pure coordination mathematics.
 *
 * This pattern scales beautifully to:
 *
 * Multiple producers
 *
 * Multiple consumers
 *
 * Batching
 *
 * Rate limiting
 *
 * Here’s the deeper insight.
 *
 * Semaphore is fundamentally a counter of permissions.
 * You are not “waiting for the queue.”
 * You are “waiting for permission.”
 *
 * That subtle framing shift is how you start thinking like a concurrency engineer instead of a thread survivor.
 *
 * And if you really want to level up: try removing mutex and replace the queue with ConcurrentLinkedQueue — then reason about whether correctness still holds. That mental exercise is where race conditions reveal their teeth.
 *
 */