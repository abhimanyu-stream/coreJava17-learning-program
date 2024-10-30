package com.java17.interview.prepartion;

public class InJavaGarbageCollection {
}
/***
 * In Java, garbage collection is the process of automatically managing memory by reclaiming memory occupied by objects that are no longer in use. Here's how it works:
 * Basic Principle:
 * Reachability:
 * The garbage collector determines whether an object is still in use by checking its reachability. An object is reachable if it can be accessed directly or indirectly from a root object (e.g., a thread or a static variable).
 * Mark and Sweep:
 * The garbage collector uses a mark-and-sweep algorithm to identify and reclaim unreachable objects.
 * Steps Involved:
 * Marking:
 * The garbage collector starts from the root objects and traverses the object graph, marking all reachable objects.
 * Sweeping:
 * The garbage collector scans the heap memory, freeing the memory occupied by unmarked (unreachable) objects.
 * Generational Garbage Collection:
 * Java's garbage collector typically uses a generational garbage collection approach to improve efficiency.
 * The heap is divided into generations:
 * Young Generation: Objects are initially allocated in the young generation. This generation is further divided into the Eden space and two survivor spaces (S0 and S1).
 * Old Generation: Objects that survive multiple garbage collections in the young generation are promoted to the old generation.
 * Garbage Collection Algorithms:
 * Minor GC: Occurs in the young generation and collects short-lived objects.
 * Major GC: Occurs in the old generation and collects long-lived objects.
 * Full GC: Involves collecting garbage from both the young and old generations.
 * Common Garbage Collectors in Java:
 * Serial GC: A single-threaded collector suitable for small applications.
 * Parallel GC: Uses multiple threads for garbage collection in the young generation, improving performance for multi-core systems.
 * Concurrent Mark Sweep (CMS) GC: Aims to minimize pauses by performing garbage collection concurrently with the application threads.
 * G1 Garbage Collector: Divides the heap into regions and collects garbage from the regions with the most garbage first.
 * Z Garbage Collector (ZGC): A low-latency garbage collector designed for large heaps.
 * Benefits of Garbage Collection in Java:
 * Automatic Memory Management: Developers don't need to manually allocate and deallocate memory, reducing errors.
 * Improved Productivity: Developers can focus on application logic instead of memory management.
 * Reduced Memory Leaks: Garbage collection helps prevent memory leaks by reclaiming memory from unused objects.
 * */
