package com.java17.interview.prepartion;

/**
 * Java Garbage Collection — How It Works
 *
 * <h2>Basic Principle: Reachability</h2>
 * <p>
 * The GC determines whether an object is still in use by checking if it is
 * <b>reachable</b> — accessible directly or indirectly from a GC root
 * (e.g., a running thread, static variable, or local variable on the stack).
 * Objects that are no longer reachable are eligible for collection.
 * </p>
 *
 * <h2>Mark and Sweep Algorithm</h2>
 * <pre>
 * Step 1 — Mark:
 *   GC starts from GC roots and traverses the object graph.
 *   All reachable objects are marked as "live".
 *
 * Step 2 — Sweep:
 *   GC scans the heap and reclaims memory occupied by unmarked (unreachable) objects.
 *
 * Step 3 — Compact (in some collectors):
 *   Live objects are moved together to eliminate fragmentation.
 * </pre>
 *
 * <h2>Generational Garbage Collection</h2>
 * <p>
 * Java's GC divides the heap into <b>generations</b> based on object age:
 * </p>
 * <pre>
 * Heap
 * ┌─────────────────────────────────────┐
 * │         Young Generation            │
 * │  ┌────────────┬──────┬──────┐       │
 * │  │   Eden     │ S0   │ S1   │       │  ← New objects allocated here
 * │  └────────────┴──────┴──────┘       │
 * ├─────────────────────────────────────┤
 * │         Old Generation              │  ← Long-lived objects promoted here
 * └─────────────────────────────────────┘
 * </pre>
 * <ul>
 *   <li><b>Eden Space:</b> New objects are created here.</li>
 *   <li><b>Survivor Spaces (S0, S1):</b> Objects that survive one Minor GC move here.</li>
 *   <li><b>Old Generation:</b> Objects that survive multiple GC cycles are promoted here.</li>
 * </ul>
 *
 * <h2>Types of GC Events</h2>
 * <pre>
 * Minor GC  — Collects only the Young Generation. Frequent, fast.
 * Major GC  — Collects the Old Generation. Less frequent, slower.
 * Full GC   — Collects Young + Old Generation. Stop-the-world. Most expensive.
 * </pre>
 *
 * <h2>Available Garbage Collectors in Java</h2>
 * <pre>
 * Collector     | Default In  | Best For                          | Pause Time
 * ──────────────|─────────────|───────────────────────────────────|────────────
 * Serial GC     | (small apps)| Single-threaded, tiny heaps       | High
 * Parallel GC   | Java 8      | High throughput batch processing  | Moderate
 * CMS           | (removed 14)| Low-pause apps (deprecated Java 9)| Low
 * G1 GC         | Java 9+     | General-purpose, large heaps      | Predictable
 * ZGC           | Java 15+    | Ultra-low latency, huge heaps     | < 1 ms
 * Shenandoah    | Java 15+    | Low-pause, concurrent compaction  | < 1 ms
 * </pre>
 * <p>See {@code Java17JVM.md} and {@code Java17_JVM_GC_Performance_Tuning.md} for deep-dive.</p>
 *
 * <h2>Enabling GC Logging (Java 17)</h2>
 * <pre>
 * -Xlog:gc*:file=gc.log:time,uptime,level,tags:filecount=5,filesize=100M
 * </pre>
 *
 * <h2>Benefits of Automatic Garbage Collection</h2>
 * <ul>
 *   <li>Developers do not manually allocate/free memory — reduces memory leaks.</li>
 *   <li>Prevents dangling pointer errors.</li>
 *   <li>Frees developers to focus on business logic.</li>
 * </ul>
 *
 * <h2>Common GC Tuning Flags</h2>
 * <pre>
 * -Xms4g                          Initial heap size
 * -Xmx4g                          Max heap size (set equal to -Xms to avoid resizing)
 * -XX:MaxRAMPercentage=75.0        For containerised apps (auto-sizes based on container limits)
 * -XX:+UseG1GC                     Use G1 (default in Java 11+)
 * -XX:+UseZGC                      Use ZGC (for low-latency)
 * -XX:MaxGCPauseMillis=100         Target max GC pause (hint to G1)
 * -XX:+HeapDumpOnOutOfMemoryError  Dump heap on OOM for analysis
 * </pre>
 */
public class InJavaGarbageCollection {
    // Documentation class — see Java17JVM.md and Java17_JVM_GC_Performance_Tuning.md
    // for GC selection strategy, ZGC/Shenandoah use cases, and production tuning.
}
