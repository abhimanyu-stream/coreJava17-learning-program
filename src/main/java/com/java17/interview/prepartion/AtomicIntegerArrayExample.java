package com.java17.interview.prepartion;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayExample {
    //AtomicIntegerArray — Atomic Operations on int Arrays
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray scores = new AtomicIntegerArray(3); // [0, 0, 0]

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                scores.incrementAndGet(0); // atomic increment at index 0
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Scores[0]: " + scores.get(0)); // always 2000
    }
}
//Each array element has independent atomicity, avoiding locking the entire array.

/**
 * Atomic Arrays Example
 * AtomicIntegerArray array = new AtomicIntegerArray(5);
 * array.set(0, 10);
 * array.incrementAndGet(0); // atomic increment at index 0
 * System.out.println(array.get(0)); // 11
 *
 *
 * Each element operation is atomic.
 */