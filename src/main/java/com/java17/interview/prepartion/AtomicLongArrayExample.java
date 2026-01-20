package com.java17.interview.prepartion;

import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicLongArrayExample {
    //AtomicLongArray — Atomic Operations on long Arrays

    public static void main(String[] args) {
        AtomicLongArray timestamps = new AtomicLongArray(2);
        timestamps.set(0, System.currentTimeMillis());
        timestamps.set(1, System.currentTimeMillis() + 1000);

        System.out.println("Before: " + timestamps);

        timestamps.addAndGet(1, 5000); // add 5 sec to element 1
        System.out.println("After: " + timestamps);
    }
}
/**
 * Great for concurrent metric buffers or rolling timestamp logs.
 *
 * 🧠 Summary Recap
 * Atomic Class	Stores	Common Use
 * AtomicInteger	int	thread-safe counter or index
 * AtomicLong	long	large or time-based counters
 * AtomicBoolean	boolean	one-time initialization flag
 * AtomicReference<V>	object reference	swap shared object safely
 * AtomicIntegerArray / AtomicLongArray	arrays	element-wise atomic updates
 *
 */