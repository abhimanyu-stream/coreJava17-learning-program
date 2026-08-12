package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPoolNoExecutors {
    public static void main(String[] args) {

        CustomThreadPool pool = new CustomThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            pool.submit(() -> {
                System.out.println(
                        Thread.currentThread().getName()
                                + " executing task " + taskId);
                
            });
        }
        try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
            }

        pool.shutdown();
    }

}

class CustomThreadPool {

    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    private final List<Thread> workers = new ArrayList<>();

    private volatile boolean shutdown = false;

    public CustomThreadPool(int size) {

        for (int i = 0; i < size; i++) {

            Thread worker = new Thread(() -> {

                while (true) {

                    try {

                        Runnable task = queue.poll(
                                1,
                                java.util.concurrent.TimeUnit.SECONDS);

                        if (task != null) {

                            try {
                                task.run();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        if (shutdown && queue.isEmpty()) {
                            break;
                        }

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();

                        if (shutdown) {
                            break;
                        }
                    }
                }

            }, "worker-" + i);

            workers.add(worker);

            worker.start();
        }
    }

    public void submit(Runnable task) {

        if (shutdown) {
            throw new IllegalStateException(
                    "Thread pool is shut down");
        }

        queue.offer(task);
    }

    public void shutdown() {
        shutdown = true;
    }

    public void awaitTermination()
            throws InterruptedException {

        for (Thread worker : workers) {
            worker.join();
        }
    }
}
/**
 * write a producer Consumer program, Producer produce data and increase count of data, Consumer consume and reduce, the Producer can produce at a highest level of data 10 in inventory. The Consumer can consume data upto data is present in inventory, both call notify method, write this java program with best practices
 * 
 */