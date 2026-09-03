/**
 * 
 * 
 * 
 * BlockingQueue in Java (Complete Guide)

BlockingQueue is one of the most important classes in Java concurrency.

A BlockingQueue is a thread-safe queue in which:

A producer inserts data into the queue.
A consumer removes data from the queue.
If the queue is full, the producer waits (blocks).
If the queue is empty, the consumer waits (blocks).

BlockingQueue solves the classic Producer-Consumer problem.

Why do we need BlockingQueue?

Without BlockingQueue, we usually write code like this:

Queue<Integer> queue = new LinkedList<>();

synchronized (queue) {

    while (queue.isEmpty()) {
        queue.wait();
    }

    Integer item = queue.remove();

    queue.notifyAll();
}

Problems:

Complex synchronization
wait()
notify()
notifyAll()
Manual locking
Deadlock risk
Race conditions

BlockingQueue handles all of this internally.

BlockingQueue hierarchy
Collection
        │
        ▼
      Queue
        │
        ▼
 BlockingQueue
        │
 ┌──────┼───────────────┬───────────────┬────────────────┐
 ▼      ▼               ▼               ▼                ▼
ArrayBlockingQueue
LinkedBlockingQueue
PriorityBlockingQueue
DelayQueue
SynchronousQueue
LinkedTransferQueue
Important methods
Method	If queue is full	If queue is empty
add()	Exception	-
offer()	false	-
put()	Blocks	-
remove()	-	Exception
poll()	-	null
take()	-	Blocks
put()

put() blocks when the queue is full.

BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(2);

queue.put(10);

queue.put(20);

queue.put(30);

The third insertion blocks because the queue is already full.

take()

take() blocks when the queue is empty.

BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(2);

Integer value = queue.take();

The thread waits until another thread inserts an element.

offer()

Returns false instead of blocking.

BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(2);

System.out.println(queue.offer(10));

System.out.println(queue.offer(20));

System.out.println(queue.offer(30));

Output:

true
true
false
poll()

Returns null instead of blocking.

BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(2);

System.out.println(queue.poll());

Output:

null
offer() with timeout
BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(2);

queue.offer(100, 5, TimeUnit.SECONDS);

The producer waits for 5 seconds.

poll() with timeout
BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(2);

Integer value =
        queue.poll(5, TimeUnit.SECONDS);

The consumer waits for 5 seconds.

ArrayBlockingQueue

A bounded queue backed by an array.

BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(5);

Characteristics:

Fixed size
FIFO
Thread-safe
Uses one lock
Example: Producer-Consumer using ArrayBlockingQueue
package com.java17.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(5);

        Runnable producer = () -> {

            for (int i = 1; i <= 10; i++) {

                try {

                    queue.put(i);

                    System.out.println(
                            "Produced: "
                                    + i);

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumer = () -> {

            while (true) {

                try {

                    Integer value =
                            queue.take();

                    System.out.println(
                            "Consumed: "
                                    + value);

                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    break;
                }
            }
        };

        new Thread(producer).start();

        new Thread(consumer).start();
    }
}
LinkedBlockingQueue

Internally uses linked nodes.

BlockingQueue<Integer> queue =
        new LinkedBlockingQueue<>();

Or with capacity:

BlockingQueue<Integer> queue =
        new LinkedBlockingQueue<>(100);

Characteristics:

Optionally bounded
Better throughput
Separate locks for producers and consumers
PriorityBlockingQueue

Elements are ordered by priority.

BlockingQueue<Integer> queue =
        new PriorityBlockingQueue<>();

queue.put(50);

queue.put(10);

queue.put(30);

System.out.println(queue.take());

System.out.println(queue.take());

System.out.println(queue.take());

Output:

10
30
50
DelayQueue

Elements become available only after a specified delay.

Example use cases:

Cache expiration
Token expiration
Session timeout
Retry mechanisms
SynchronousQueue

Capacity = 0

Producer
     │
     ▼
SynchronousQueue
     ▲
     │
Consumer

No element is stored.

Every put() must wait for a take().

BlockingQueue<String> queue =
        new SynchronousQueue<>();

Use cases:

Thread pools
Direct handoff between threads
LinkedTransferQueue

Supports direct transfer between producer and consumer.

TransferQueue<String> queue =
        new LinkedTransferQueue<>();

Producer:

queue.transfer("Message");

The producer waits until the consumer receives the message.

Custom BlockingQueue implementation

Let's implement our own queue using:

wait()
notifyAll()
synchronized
package com.java17.customqueue;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueue<T> {

    private final Queue<T> queue =
            new LinkedList<>();

    private final int capacity;

    public CustomBlockingQueue(int capacity) {

        this.capacity = capacity;
    }

    public synchronized void put(T item)
            throws InterruptedException {

        while (queue.size() == capacity) {

            wait();
        }

        queue.offer(item);

        notifyAll();
    }

    public synchronized T take()
            throws InterruptedException {

        while (queue.isEmpty()) {

            wait();
        }

        T item = queue.poll();

        notifyAll();

        return item;
    }

    public synchronized int size() {

        return queue.size();
    }
}
Testing the custom queue
package com.java17.customqueue;

public class Main {

    public static void main(String[] args) {

        CustomBlockingQueue<Integer> queue =
                new CustomBlockingQueue<>(5);

        Thread producer =
                new Thread(() -> {

                    try {

                        for (int i = 1; i <= 20; i++) {

                            queue.put(i);

                            System.out.println(
                                    "Produced: "
                                            + i);

                            Thread.sleep(500);
                        }

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                });

        Thread consumer =
                new Thread(() -> {

                    try {

                        while (true) {

                            Integer item =
                                    queue.take();

                            System.out.println(
                                    "Consumed: "
                                            + item);

                            Thread.sleep(1000);
                        }

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                });

        producer.start();

        consumer.start();
    }
}
How wait() and notifyAll() work internally
Queue capacity = 3

Queue = [1, 2, 3]

Producer tries to insert 4

Queue is full

Producer enters WAITING state

Consumer removes 1

Queue = [2, 3]

Consumer calls notifyAll()

Producer wakes up

Producer inserts 4

Queue = [2, 3, 4]
Real-world use cases
System	BlockingQueue usage
Kafka	Message buffering
RabbitMQ	Message processing
Log processing	Producer-consumer
Email service	Email queue
Payment gateway	Transaction queue
Web crawler	URL queue
ThreadPoolExecutor	Task queue
ThreadPoolExecutor internally uses a BlockingQueue
ExecutorService executor =
        new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

Tasks are stored inside the queue.

Task
 ↓
BlockingQueue
 ↓
Worker thread
 ↓
Execution

Java interview questions:

Queue vs BlockingQueue
put() vs offer()
take() vs poll()
ArrayBlockingQueue vs LinkedBlockingQueue
notify() vs notifyAll()
Why should wait() be used inside a while loop instead of an if statement?
How does ThreadPoolExecutor use BlockingQueue internally?


 */