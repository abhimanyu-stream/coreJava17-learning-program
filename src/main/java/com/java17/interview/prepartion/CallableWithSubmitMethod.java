package com.java17.interview.prepartion;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableWithSubmitMethod {

    public static void main(String[] args) throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        Callable<Integer> task = () -> {

            System.out.println(
                    "Callable executed by: "
                            + Thread.currentThread().getName());

            return 1000;
        };

        Future<Integer> future =
                executor.submit(task);

        Integer result =
                future.get();

        System.out.println(
                "Result: "
                        + result);

        executor.shutdown();
    }
}
/**
 * submit(Runnable) vs submit(Callable)
Method	        Returns a value	        Returns Future	        Throws checked exceptions
submit(Runnable)	❌	Future<?>	❌
submit(Callable<T>)	✅	Future<T>	✅
 */