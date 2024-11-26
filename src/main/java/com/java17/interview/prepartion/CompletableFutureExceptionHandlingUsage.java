package com.java17.interview.prepartion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExceptionHandlingUsage {

    public static void main(String[] args) {
        // Example user ID (negative ID will simulate an error scenario)
        //int userId = -1;

        // For Success scenario
        int userId = -1;

        // Step 1: Start an asynchronous task to fetch user details
        CompletableFuture<String> userDetailsFuture = CompletableFuture.supplyAsync(() -> {
            if (userId < 0) {
                throw new IllegalArgumentException("User ID cannot be negative");
            }
            return "User--" + userId;
        });

        // Step 2: Handle exception using `exceptionally`
        CompletableFuture<String> handledExceptionally = userDetailsFuture.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return "Default User"; // Fallback value
        });

        // Step 3: Handle exception and result using `handle`
        CompletableFuture<String> handledWithHandle = userDetailsFuture.handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Handling error with handle: " + ex.getMessage());
                return "Handled User";
            }
            return result;
        });

        // Step 4: Inspect the result or exception using `whenComplete`
        CompletableFuture<String> future = userDetailsFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("whenComplete: An error occurred - " + ex.getMessage());
            } else {
                System.out.println("whenComplete: Successfully fetched - " + result);
            }
        });

        try {
            // Print the outcomes
            System.out.println("Result with exceptionally: " + handledExceptionally.get());
            System.out.println("Result with handle: " + handledWithHandle.get());
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
/**
 * Explanation
 * exceptionally:
 *
 * exceptionally is called if an exception occurs. It provides a fallback value ("Default User") if the user ID is invalid.
 * handle:
 *
 * handle provides access to both the result and the exception. If an exception occurs, we handle it by returning a different value ("Handled User"). Otherwise, we return the successful result.
 * whenComplete:
 *
 * whenComplete allows inspecting the result or exception but doesn’t modify the outcome of the CompletableFuture. Here, we log the result or exception without affecting the final result.
 * Output
 * If you run the code with userId = -1, you’ll see:
 *

 * Exception occurred: User ID cannot be negative
 * Handling error with handle: User ID cannot be negative
 * whenComplete: An error occurred - User ID cannot be negative
 * Result with exceptionally: Default User
 * Result with handle: Handled User
 * If you use a valid userId, say userId = 10, you’ll get:
 *
 *
 * whenComplete: Successfully fetched - User10
 * Result with exceptionally: User10
 * Result with handle: User10
 * Summary of Exception Handling Techniques
 * Use exceptionally to handle exceptions and provide a default or fallback value.
 * Use handle for flexible handling, as it can process both result and exception.
 * Use whenComplete to inspect the result or exception without changing the outcome.
 * This setup allows robust error management in asynchronous pipelines, making CompletableFuture a strong choice for complex asynchronous workflows.
 *
 * */