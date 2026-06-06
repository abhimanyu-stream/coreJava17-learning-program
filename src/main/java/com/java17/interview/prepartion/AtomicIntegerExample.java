package com.java17.interview.prepartion;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    //AtomicInteger — Atomic Counter / Index
    //
    //Used for counters or generating unique IDs without locks.
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet(); // atomic increment
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.get()); // always 2000
    }
}
/**
 * CAS (Compare-And-Swap) — The Core Idea
 *
 * All atomic operations use CAS internally:
 *
 * Read current value
 *
 * Compare with expected value
 *
 * If still same → update
 *
 * Else → retry
 *
 * This avoids locks but ensures thread safety.
 *
 * boolean success = atomicInt.compareAndSet(5, 10);
 *
 *
 * If current value == 5, it sets to 10 and returns true.
 *
 *
 */
/**
 * Common Methods (AtomicInteger Example)
 * Method	Description
 * get()	returns current value
 * set(int newValue)	sets new value
 * incrementAndGet()	increments then returns updated value
 * getAndIncrement()	returns old value then increments
 * compareAndSet(expected, newValue)	atomic CAS operation
 * addAndGet(delta)	adds delta atomically
 * updateAndGet(IntUnaryOperator op)	custom atomic operation
 */

/**
 * Common Atomic Types
 * Class	Type Stored	Typical Use
 * AtomicInteger	int	counters, indexes
 * AtomicLong	long	high-precision counters
 * AtomicBoolean	boolean	flags, toggle states
 * AtomicReference<V>	object reference	shared objects
 * AtomicIntegerArray, AtomicLongArray	arrays	atomic updates on array elements
 */

/**
 * When to Use Atomic Types
 *
 * ✅ Use when:
 *
 * You need lightweight thread-safety
 *
 * Lock contention is high and synchronized is too costly
 *
 * You’re implementing counters, flags, or concurrent state machines
 *
 * ❌ Avoid when:
 *
 * You need compound atomicity (e.g., update multiple variables together) → use locks instead
 *
 * Updates are rare and contention is low → synchronization is fine
 *
 */

/**
 * Summary
 * Feature	Atomic Classes	Locks / Synchronization
 * Thread-safety	✅	✅
 * Blocking	❌ (Non-blocking)	✅
 * Performance under contention	Better	Worse
 * Scope	Single variable	Multiple variables possible
 */