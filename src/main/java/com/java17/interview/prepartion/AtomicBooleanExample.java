package com.java17.interview.prepartion;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
    //AtomicBoolean — Thread-safe Flag or Switch
    //
    //Used for state flags, toggles, or one-time actions.

    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    public static void main(String[] args) {
        Runnable initTask = () -> {
            if (initialized.compareAndSet(false, true)) {
                System.out.println(Thread.currentThread().getName() + " performed initialization");
            } else {
                System.out.println(Thread.currentThread().getName() + " skipped initialization");
            }
        };

        Thread t1 = new Thread(initTask, "Thread-1");
        Thread t2 = new Thread(initTask, "Thread-2");

        t1.start();
        t2.start();
    }
}

