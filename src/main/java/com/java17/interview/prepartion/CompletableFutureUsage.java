package com.java17.interview.prepartion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureUsage {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Step 1: Fetch user information asynchronously
        CompletableFuture<String> userInfoFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching user info...");
            delay(1); // Simulate delay
            return "User123"; // Example user ID
        });

        // Step 2: Fetch credit score asynchronously based on user ID
        CompletableFuture<Integer> creditScoreFuture = userInfoFuture.thenApplyAsync(userId -> {
            System.out.println("Fetching credit score for user: " + userId);
            delay(2); // Simulate delay
            return 700; // Example credit score
        });

        // Combine results and print
        CompletableFuture<Void> combinedFuture = creditScoreFuture.thenAccept(score -> {
            System.out.println("User's credit score is: " + score);
        });

        // Wait for all tasks to complete
        combinedFuture.get();
    }

    // Helper method to simulate a delay
    private static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}

/**
 * Explanation
 * supplyAsync: CompletableFuture.supplyAsync starts an asynchronous task that fetches user information. It returns a CompletableFuture<String> which contains the user ID.
 *
 * thenApplyAsync: After fetching the user ID, we use thenApplyAsync to start another asynchronous task to fetch the credit score based on the user ID. It takes the result from userInfoFuture and uses it to fetch the credit score.
 *
 * thenAccept: Finally, thenAccept is used to consume the result of creditScoreFuture. Here, we print the credit score after the asynchronous operations are complete.
 *
 * get(): Calling get() on combinedFuture ensures the main thread waits until the entire pipeline completes.
 *
 * */