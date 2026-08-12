package com.java17.interview.prepartion;

/**
 * A Producer-Consumer problem has these rules:

The Producer adds data to the inventory.
The Consumer removes data from the inventory.
The inventory capacity is 10.
The producer must wait if the inventory is full.
The consumer must wait if the inventory is empty.
Both threads should use wait() and notifyAll() for communication.
Always use while instead of if before wait() (best practice to prevent spurious wakeups).
Producer-Consumer flow
                    Inventory (Max = 10)

        Producer                           Consumer
            │                                  │
            ▼                                  ▲
      produce()                           consume()
            │                                  │
            ▼                                  ▲
       count = count + 1                  count = count - 1
            │                                  │
            ▼                                  ▲
       inventory full?                    inventory empty?
            │                                  │
         yes │                                  │ yes
            ▼                                  ▼
          wait()                             wait()
            │                                  │
            └──────────── notifyAll() ─────────┘
            
 * ProducerConsumerWorking
 */
public class ProducerConsumerWorking {

    public static void main(String[] args)
            throws InterruptedException {

        Inventory inventory = new Inventory();

        Thread producer1 =
                new Thread(
                        new Producer(inventory),
                        "Producer-1"
                );

        Thread producer2 =
                new Thread(
                        new Producer(inventory),
                        "Producer-2"
                );

        Thread consumer1 =
                new Thread(
                        new Consumer(inventory),
                        "Consumer-1"
                );

        Thread consumer2 =
                new Thread(
                        new Consumer(inventory),
                        "Consumer-2"
                );

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();

        Thread.sleep(10000);

        producer1.interrupt();
        producer2.interrupt();

        consumer1.interrupt();
        consumer2.interrupt();
    }
}

class Inventory {

    private static final int MAX_CAPACITY = 10;

    private int count = 0;

    public synchronized void produce() {

        while (count == MAX_CAPACITY) {

            try {
                System.out.println(
                        Thread.currentThread().getName()
                                + " waiting. Inventory is full."
                );

                wait();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                return;
            }
        }

        count++;

        System.out.println(
                Thread.currentThread().getName()
                        + " produced one item. Inventory = "
                        + count
        );

        notifyAll();
    }

    public synchronized void consume() {

        while (count == 0) {

            try {
                System.out.println(
                        Thread.currentThread().getName()
                                + " waiting. Inventory is empty."
                );

                wait();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                return;
            }
        }

        count--;

        System.out.println(
                Thread.currentThread().getName()
                        + " consumed one item. Inventory = "
                        + count
        );

        notifyAll();
    }
}

class Producer implements Runnable {

    private final Inventory inventory;

    public Producer(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            inventory.produce();

            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {

    private final Inventory inventory;

    public Consumer(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            inventory.consume();

            try {
                Thread.sleep(800);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }
    }
}
