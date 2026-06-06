package com.java17.interview.prepartion;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongExample {
    //AtomicLong — Long Counter (High Precision / Large Range)
    //
    //Used in metrics, timestamps, accumulators, etc.
    private static final AtomicLong totalBytesProcessed = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable downloadTask = () -> {
            for (int i = 0; i < 5; i++) {
                totalBytesProcessed.addAndGet(500L); // add 500 bytes atomically
            }
        };

        Thread t1 = new Thread(downloadTask);
        Thread t2 = new Thread(downloadTask);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Total Bytes: " + totalBytesProcessed.get()); // 5000
    }
}

