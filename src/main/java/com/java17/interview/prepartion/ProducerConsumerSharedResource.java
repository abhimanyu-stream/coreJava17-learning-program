package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProducerConsumerSharedResource {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        producer.start();
        consumer.start();

        try {
            producer.join();//
            consumer.join();
            //above two join() called by main Thread, and main Thread will wait till both threads(producer, consumer) has not finished their task and control will come there inside main() which belongs to main Thread
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread was interrupted.");
        }
    }
}

class SharedResource {
    private final List<Integer> data = Collections.synchronizedList(new ArrayList<>());

    public synchronized void consume() throws InterruptedException {
        while (data.isEmpty()) {
            wait(); // Wait until data is available
        }

        //data.forEach(System.out::println);
        System.out.println("data consuming stated :");
        data.forEach(d->System.out.println("consumed data d is "+d));
        data.clear(); // Clear the list after consuming
        System.out.println("All data has been consumed :");
        System.out.println("consume() is calling notifyAll to inform Producer thread who is waiting for Consumer response. [ here when data when become zero then only consumer is informing Producer, " +
                "You can inform Producer before too, having a threshold value]");
        notifyAll(); // Notify producer
    }

    public synchronized void produce(int value) throws InterruptedException {

        // Note: - here we are checking for empty. There can be any other implementation too. Like produce up to the space available in Inventory.
        while (!data.isEmpty()) {
            wait(); // Wait until data is consumed
        }
        data.add(value); // Add new data
        System.out.println("Producer has produced: " + value);
        notifyAll(); // Notify consumer
    }
}

class Producer extends Thread {
    private final SharedResource resource;

    Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {

        // At a time Producer is producing 5 elements only
        for (int i = 0; i < 5; i++) {
            try {

                // We can use Math.random() to get a value from 1 to a defined max value to insert into stock or store in stock by a value which is being passed as argument in method
                resource.produce(i+1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                System.err.println("Producer was interrupted.");
            }
        }
    }
}

class Consumer extends Thread {
    private final SharedResource resource;

    Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {

        // At a time Consumer is trying to consumer 5 elements only
        for (int i = 0; i < 5; i++) {
            try {
                resource.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                System.err.println("Consumer was interrupted.");
            }
        }
    }
}
