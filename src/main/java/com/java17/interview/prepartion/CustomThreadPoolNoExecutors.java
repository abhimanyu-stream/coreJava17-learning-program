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
                                + " executing task " + taskId
                );
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            });
        }

        pool.shutdown();
    }

}

class CustomThreadPool {

    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final List<Worker> workers = new ArrayList<>();
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int size) {
        for (int i = 0; i < size; i++) {
            Worker worker = new Worker("worker-" + i);
            workers.add(worker);
            worker.start();
        }
    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            queue.offer(task);
        }
    }

    public void shutdown() {
        isShutdown = true;
        workers.forEach(Thread::interrupt);
    }

    private class Worker extends Thread {

        Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isShutdown || !queue.isEmpty()) {
                try {
                    Runnable task = queue.take();
                    task.run();
                } catch (InterruptedException e) {
                    // Exit gracefully during shutdown
                    if (isShutdown) {
                        break;
                    }
                }
            }
        }
    }
}
