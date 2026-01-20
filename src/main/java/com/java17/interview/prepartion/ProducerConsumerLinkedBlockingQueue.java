package com.java17.interview.prepartion;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerLinkedBlockingQueue  {

    public static void main(String[] args) {
        // Shared resource
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>(100);

        // Create producer and consumer
        Thread producer = new Thread(new ProducerConsumerArrayBlockingQueue.Producer(sharedQueue), "Producer");
        Thread consumer = new Thread(new ProducerConsumerArrayBlockingQueue.Consumer(sharedQueue), "Consumer");

        // Start threads
        producer.start();
        consumer.start();
    }

    // Producer thread
    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int value = 0;
            try {
                while (true) {
                    System.out.println("Produced: " + value);
                    queue.put(value++);
                    Thread.sleep(1000); // simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }
        }
    }

    // Consumer thread
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
                    Thread.sleep(1500); // simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
            }
        }
    }
}
