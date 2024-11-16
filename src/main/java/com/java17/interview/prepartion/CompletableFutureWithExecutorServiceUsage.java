package com.java17.interview.prepartion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureWithExecutorServiceUsage {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Step 1: Create a custom ExecutorService
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    try {
        // Step 2: Fetch product details asynchronously with ExecutorService
        CompletableFuture<String> productDetailsFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching product details... [" + Thread.currentThread().getName() + "]");
            delay(1); // Simulate delay
            return "Product123"; // Example product ID
        }, executorService);

        // Step 3: Fetch product pricing asynchronously with ExecutorService, after product ID is retrieved
        CompletableFuture<Double> productPriceFuture = productDetailsFuture.thenApplyAsync(productId -> {
            System.out.println("Fetching product price for: " + productId + " [" + Thread.currentThread().getName() + "]");
            delay(2); // Simulate delay
            return 199.99; // Example price
        }, executorService);

        // Step 4: Combine results and print the output
        CompletableFuture<Void> combinedFuture = productPriceFuture.thenAccept(price -> {
            System.out.println("Product price is: $" + price);
        });

        // Wait for all tasks to complete
        combinedFuture.get();
    } finally {
        // Step 5: Shut down the ExecutorService
        executorService.shutdown();
    }
}

        // Helper method to simulate delay
        private static void delay(int seconds) {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }




}
