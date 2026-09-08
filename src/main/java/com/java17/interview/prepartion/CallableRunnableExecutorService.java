package com.java17.interview.prepartion;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRunnableExecutorService {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		 // Create thread pool with 2 worker threads
        ExecutorService executor =
                Executors.newFixedThreadPool(2);


        // =========================================================
        // 1. RUNNABLE
        // =========================================================
        // Runnable:
        // - does NOT return a result
        // - run() returns void

        // java.lang.Runnable
        Runnable runnableTask = () -> {

            System.out.println(
                    "Runnable executed by: "
                            + Thread.currentThread().getName());

        };

        Future<?> runnableFuture =
                executor.submit(runnableTask);


        // =========================================================
        // 2. CALLABLE
        // =========================================================
        // Callable:
        // - returns a result
        // - call() returns a value
        // - can throw checked exceptions

        Callable<Integer> callableTask = () -> {

            System.out.println(
                    "Callable executed by: "
                            + Thread.currentThread().getName());

            return 1000;
        };

        Future<Integer> callableFuture =
                executor.submit(callableTask);


        // =========================================================
        // GET RESULTS
        // =========================================================

        // Runnable doesn't return a business result
        runnableFuture.get();

        // Callable returns Integer
        Integer result =
                callableFuture.get();

        System.out.println(
                "Callable Result: "
                        + result);


        // =========================================================
        // SHUTDOWN
        // =========================================================

        executor.shutdown();
    }
	

}

/**
 * 
 * What happens internally?

Think of it as:

                    ExecutorService
                          │
                          │
                 submit(task)
                          │
              ┌───────────┴───────────┐
              │                       │
              ▼                       ▼
         Runnable                 Callable<Integer>
              │                       │
              ▼                       ▼
        worker thread             worker thread
              │                       │
              │                       │
           run()                    call()
              │                       │
              ▼                       ▼
          void result              1000
                                      │
                                      ▼
                              Future<Integer>
                                      │
                                      ▼
                                  future.get()
                                      │
                                      ▼
                                    1000
Key difference
Feature	Runnable	Callable
Method	run()	call()
Return value	void	Yes
Return type	void	Generic V
Exception	Cannot throw checked exception directly	Can throw checked exception
ExecutorService.submit()	Future<?>	Future<V>
Future.get()	Usually used to wait for completion	Used to get returned result

For example:

Runnable task = () -> {
    System.out.println("Processing...");
};

No result:

Future<?> future = executor.submit(task);
future.get();

Whereas:

Callable<Integer> task = () -> {
    return 1000;
};

Result:

Future<Integer> future = executor.submit(task);

Integer result = future.get();

So the most important interview statement is:

Runnable represents a task that executes without returning a result, while Callable represents a task that executes and returns a result, potentially throwing a checked exception.

One more important point: future.get() is blocking. If the Callable hasn't finished, the calling thread waits until the worker thread completes.

 */
