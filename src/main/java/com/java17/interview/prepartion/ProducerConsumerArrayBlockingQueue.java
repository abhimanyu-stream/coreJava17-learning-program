package com.java17.interview.prepartion;

import org.springframework.util.LinkedMultiValueMap;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class ProducerConsumerArrayBlockingQueue {
   // BlockingQueue,ArrayBlockingQueue, LinkedBlockingQueue,LinkedTransferQueue, LinkedMultiValueMap
    public static void main(String[] args) {
        // Shared resource
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);

        // Create producer and consumer
        Thread producer = new Thread(new Producer(sharedQueue), "Producer");
        Thread consumer = new Thread(new Consumer(sharedQueue), "Consumer");

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

