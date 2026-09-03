package com.java17.interview.prepartion;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableWithSubmitMethod {

    public static void main(String[] args)
            throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        Runnable task = () -> {

            System.out.println(
                    "Runnable executed by: "
                            + Thread.currentThread().getName());
        };

        Future<?> future =
                executor.submit(task);

        future.get();

        System.out.println(
                "Runnable completed");

        executor.shutdown();
    }
}
/**
 * submit(Runnable) vs submit(Callable)
Method	        Returns a value	        Returns Future	        Throws checked exceptions
submit(Runnable)	❌	                Future<?>	                                ❌
submit(Callable<T>)	✅	                Future<T>	                                            ✅
 */