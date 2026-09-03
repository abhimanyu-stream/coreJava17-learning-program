package com.java17.interview.prepartion;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallerRunsPolicyUsage {
    
    public static void main(String[] args) {

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(
                        1,
                        2,
                        60,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(2),
                        new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 10; i++) {

            int taskId = i;

            executor.submit(() -> {

                System.out.println(
                        "Task "
                                + taskId
                                + " executed by "
                                + Thread.currentThread()
                                        .getName());

                try {

                    Thread.sleep(3000);

                } catch (InterruptedException e) {

                    Thread.currentThread()
                            .interrupt();
                }
            });
        }

        executor.shutdown();
    }
}