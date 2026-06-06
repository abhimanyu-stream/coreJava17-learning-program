package com.java17.interview.prepartion;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThreadComputableFutureAsync {

	public static void main(String[] args) throws ExecutionException, InterruptedException {



		Runnable r = ()-> System.out.println("runnable which do not return, its has consumer nature ");

		Thread t1 = new Thread();
		t1.start();
		t1.join();


		Callable<String> voidCallable = () -> {
			System.out.println("void Callable");
			return "void Callable";
		};

		// Convert Callable to Supplier for CompletableFuture
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			try {
				return voidCallable.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		// Optionally get the result
		String result = cf.get();
		System.out.println("Result from CompletableFuture: " + result);
	}

}
