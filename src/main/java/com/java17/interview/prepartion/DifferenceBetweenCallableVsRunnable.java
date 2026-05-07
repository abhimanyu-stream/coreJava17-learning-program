package com.java17.interview.prepartion;

import java.util.concurrent.*;

public class DifferenceBetweenCallableVsRunnable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable r = ()->{
            System.out.println("some task doing");
        };
        new Thread(r).start();


        Callable<Integer> c = ()->{
            return 100;
        } ;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> submit = executorService.submit(c);
        submit.get();

    }
}
/**
 * Difference between Callable vs Runnable
 * Feature	Runnable	Callable
 * Package	java.lang	java.util.concurrent
 * Return value	No	Yes
 * Exception handling	Cannot throw checked exception	Can throw checked exception
 * Introduced	Java 1.0	Java 5
 * Used with	Thread	ExecutorService
 *
 *
 * Key Interview Line
 * Runnable is for fire-and-forget tasks.
 * Callable is for tasks that return results.
 */