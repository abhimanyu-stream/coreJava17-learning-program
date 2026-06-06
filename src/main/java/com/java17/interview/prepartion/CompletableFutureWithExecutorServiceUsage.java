package com.java17.interview.prepartion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureWithExecutorServiceUsage {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        CompletableFuture.supplyAsync(() -> {
                    return "Hello";
                })
                .thenApply(result -> {
                    return result + " World";
                })
                .thenAccept(System.out::println);

        ExecutorService executorService = Executors.newFixedThreadPool(2);//1
        //Executors.newCachedThreadPool()//2
        //Executors.newScheduledThreadPool()//3
        //Executors.newSingleThreadExecutor()//4
        //Executors.newSingleThreadScheduledExecutor()//5
        //Executors.newWorkStealingPool()//6

        try {
            CompletableFuture<String> productDetailsFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println("Fetching product details... [" + Thread.currentThread().getName() + "]");
                delay(1);
                return "Product123";
            }, executorService);

            CompletableFuture<Double> productPriceFuture = productDetailsFuture.thenApplyAsync(productId -> {
                System.out.println("Fetching product price for: " + productId + " [" + Thread.currentThread().getName() + "]");
                delay(2);

                // Uncomment to test exception
                // if (true) throw new RuntimeException("Pricing Service Failed!");

                return 199.99;
            }, executorService);

            // ✅ Using handle() to provide fallback on failure
            CompletableFuture<Double> safePriceFuture = productPriceFuture.handle((price, ex) -> {
                if (ex != null) {
                    System.out.println("Error occurred: " + ex.getMessage());
                    return 0.0; // Fallback price
                }
                return price;
            });

            CompletableFuture<Void> combinedFuture = safePriceFuture.thenAccept(price ->
                    System.out.println("Product price is: $" + price)
            );

            combinedFuture.get();

        } finally {
            executorService.shutdown();
        }
    }

    private static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
/**
 * Important Methods
 * Method	                 Purpose
 * thenApply()	          Transform result
 * thenAccept()  	      Consume result
 * thenRun()	          Run next task
 * thenCompose()	      FlatMap async calls
 * thenCombine()	     Combine two futures
 * exceptionally()	     Handle exception
 * Real-World Example
 * CompletableFuture.supplyAsync(() -> getUser())
 *     .thenCompose(user -> getOrders(user))
 *     .thenApply(orders -> calculatePrice(orders))
 *     .exceptionally(ex -> {
 *         return 0;
 *     });
 * Benefits
 * Non-blocking
 * Parallel execution
 * Better than nested callbacks
 * Better resource utilization
 * Interview Difference
 * Feature	Future	CompletableFuture
 * Blocking	Yes	No
 * Chaining	No	Yes
 * Callback support	No	Yes
 * Combination support	No	Yes
 */