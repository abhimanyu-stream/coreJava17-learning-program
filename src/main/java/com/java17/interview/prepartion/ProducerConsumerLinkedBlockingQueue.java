package com.java17.interview.prepartion;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLinkedBlockingQueue {

    private static final int MAX_QUEUE_SIZE = 10;   // pause producer when reached
    private static final int MIN_QUEUE_SIZE = 3;    // resume producer when reached
    private static final int MAX_PRODUCE_VALUE = 50;

    private static final Lock lock = new ReentrantLock();
    private static final Condition canProduce = lock.newCondition();

    public static void main(String[] args) {

        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>(100);

        Thread producer = new Thread(new Producer(sharedQueue), "Producer");
        Thread consumer = new Thread(new Consumer(sharedQueue), "Consumer");

        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int value = 0;

            try {
                while (value < MAX_PRODUCE_VALUE) {

                    lock.lock();
                    try {
                        while (queue.size() >= MAX_QUEUE_SIZE) {
                            System.out.println("Queue full. Producer waiting...");
                            canProduce.await();
                        }

                        value++;
                        queue.put(value);
                        System.out.println("Produced: " + value);

                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(500);
                }

                System.out.println("Producer finished production.");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {

                    Integer value = queue.take();
                    System.out.println("Consumed: " + value);

                    lock.lock();
                    try {
                        if (queue.size() <= MIN_QUEUE_SIZE) {
                            System.out.println("Queue low. Signalling producer...");
                            canProduce.signal();
                        }
                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
/**
 * You’re trying to reinvent backpressure on top of a class that already is backpressure. That’s delightfully human.
 *
 * Here’s the key truth: BlockingQueue already handles waiting logic internally.
 * You should not use wait() / notify() here at all. That’s like installing manual brakes on a Tesla.
 *
 * Instead, you want:
 *
 * • Producer produces up to a MAX value
 * • If queue reaches HIGH_THRESHOLD → producer pauses
 * • When consumer reduces to LOW_THRESHOLD → producer resumes
 *
 * We’ll implement this cleanly using ReentrantLock + Condition.
 *
 * No illegal wait() calls. No random notify(). No undefined monitor behavior. Just controlled coordination.
 *
 * Here’s the corrected and upgraded version:
 *
 * package com.java17.interview.prepartion;
 *
 * import java.util.concurrent.BlockingQueue;
 * import java.util.concurrent.LinkedBlockingQueue;
 * import java.util.concurrent.locks.Condition;
 * import java.util.concurrent.locks.Lock;
 * import java.util.concurrent.locks.ReentrantLock;
 *
 * public class ProducerConsumerLinkedBlockingQueue {
 *
 *     private static final int MAX_QUEUE_SIZE = 10;   // pause producer when reached
 *     private static final int MIN_QUEUE_SIZE = 3;    // resume producer when reached
 *     private static final int MAX_PRODUCE_VALUE = 50;
 *
 *     private static final Lock lock = new ReentrantLock();
 *     private static final Condition canProduce = lock.newCondition();
 *
 *     public static void main(String[] args) {
 *
 *         BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>(100);
 *
 *         Thread producer = new Thread(new Producer(sharedQueue), "Producer");
 *         Thread consumer = new Thread(new Consumer(sharedQueue), "Consumer");
 *
 *         producer.start();
 *         consumer.start();
 *     }
 *
 *     static class Producer implements Runnable {
 *         private final BlockingQueue<Integer> queue;
 *
 *         public Producer(BlockingQueue<Integer> queue) {
 *             this.queue = queue;
 *         }
 *
 *         @Override
 *         public void run() {
 *             int value = 0;
 *
 *             try {
 *                 while (value < MAX_PRODUCE_VALUE) {
 *
 *                     lock.lock();
 *                     try {
 *                         while (queue.size() >= MAX_QUEUE_SIZE) {
 *                             System.out.println("Queue full. Producer waiting...");
 *                             canProduce.await();
 *                         }
 *
 *                         value++;
 *                         queue.put(value);
 *                         System.out.println("Produced: " + value);
 *
 *                     } finally {
 *                         lock.unlock();
 *                     }
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
 *         private final BlockingQueue<Integer> queue;
 *
 *         public Consumer(BlockingQueue<Integer> queue) {
 *             this.queue = queue;
 *         }
 *
 *         @Override
 *         public void run() {
 *             try {
 *                 while (true) {
 *
 *                     Integer value = queue.take();
 *                     System.out.println("Consumed: " + value);
 *
 *                     lock.lock();
 *                     try {
 *                         if (queue.size() <= MIN_QUEUE_SIZE) {
 *                             System.out.println("Queue low. Signalling producer...");
 *                             canProduce.signal();
 *                         }
 *                     } finally {
 *                         lock.unlock();
 *                     }
 *
 *                     Thread.sleep(1000);
 *                 }
 *             } catch (InterruptedException e) {
 *                 Thread.currentThread().interrupt();
 *             }
 *         }
 *     }
 * }
 *
 * What’s happening here in clean mental model terms:
 *
 * • Producer pauses when queue >= 10
 * • Consumer signals producer when queue <= 3
 * • Producer only produces up to 50 items total
 * • No illegal monitor usage
 * • No race condition around wait/notify
 *
 * Now let’s elevate the thinking.
 *
 * If this were production-grade:
 *
 * You wouldn’t check queue.size() frequently (it’s O(n) for some queues).
 *
 * You’d use bounded queue capacity instead of manual thresholds.
 *
 * Or even better — use Semaphore for fine-grained control.
 *
 * Also note: LinkedBlockingQueue already blocks on put() when full. If your goal is just bounded production, you can simply set capacity to 10 and delete all lock logic entirely.
 *
 * The elegant version:
 *
 * BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
 *
 * Now producer auto-blocks at capacity. Zero extra coordination needed. The JVM engineers already did the hard math for you.
 *
 * Concurrency lesson:
 * If you find yourself writing wait() manually in 2026, pause and ask — “Is there a higher-level abstraction?”
 *
 * There usually is.
 *
 * And that instinct — choosing the right abstraction — is what separates concurrency survivors from concurrency victims.
 */