# Java 17 JVM & GC Deep Dive

> **Why Java 17?** Most tutorials focus on language features (records, sealed classes, text blocks). The real production value of Java 17 is the **JVM runtime** — GC maturity, container awareness, JIT improvements, and security. This guide covers exactly that.

---

## Table of Contents

1. [Java 8 → 11 → 17 JVM/GC at a Glance](#1-java-8--11--17-jvmgc-at-a-glance)
2. [GC Evolution: Java 8](#2-gc-evolution-java-8)
3. [GC Evolution: Java 11 — G1 Becomes Default](#3-gc-evolution-java-11--g1-becomes-default)
4. [GC Evolution: Java 17 — G1 Matured + New Collectors](#4-gc-evolution-java-17--g1-matured--new-collectors)
5. [ZGC — Ultra-Low Latency GC](#5-zgc--ultra-low-latency-gc)
6. [Shenandoah GC](#6-shenandoah-gc)
7. [G1 vs ZGC vs Shenandoah — Decision Guide](#7-g1-vs-zgc-vs-shenandoah--decision-guide)
8. [Container & Kubernetes Awareness](#8-container--kubernetes-awareness)
9. [JVM Memory Ergonomics](#9-jvm-memory-ergonomics)
10. [Compact Strings (Java 9+)](#10-compact-strings-java-9)
11. [JIT Compiler Improvements](#11-jit-compiler-improvements)
12. [Security & Platform Upgrades](#12-security--platform-upgrades)
13. [Removed / Deprecated in Java 17](#13-removed--deprecated-in-java-17)
14. [What to Learn for Senior Interviews](#14-what-to-learn-for-senior-interviews)
15. [Interview-Ready Answers](#15-interview-ready-answers)

---

## 1. Java 8 → 11 → 17 JVM/GC at a Glance

| Area                   | Java 8               | Java 11                | Java 17                        |
|------------------------|----------------------|------------------------|--------------------------------|
| Default GC             | Parallel GC          | G1 GC                  | G1 GC (improved)               |
| G1 GC                  | Available, not default | Default              | Default + significantly matured |
| ZGC                    | ❌                   | Experimental           | ✅ Production-ready             |
| Shenandoah             | ❌                   | Experimental           | ✅ Production-ready             |
| Container awareness    | Limited              | Much better            | Mature                         |
| Compact Strings        | ❌ (char[])          | ✅ (byte[])            | ✅ (byte[])                    |
| CMS GC                 | Available            | Deprecated             | ❌ Removed                     |
| JVM startup/perf       | Baseline             | Better                 | Better                         |
| Security baseline      | Older                | Improved               | Much stronger                  |
| Class Data Sharing     | Basic                | Improved               | Improved                       |

> **Key insight:** Java 17 is not primarily valuable because of `sealed`, `record`, or text blocks. It is valuable because it ships a **significantly more mature production JVM**.

---

## 2. GC Evolution: Java 8

### Heap Layout (Classic)

```
JVM Heap
 ┌──────────────────────┐
 │    Young Generation  │
 │  ┌───────┬─────────┐ │
 │  │ Eden  │Survivor │ │
 │  └───────┴─────────┘ │
 ├──────────────────────┤
 │    Old Generation    │
 └──────────────────────┘
```

### Common GC Choices in Java 8

```bash
java -XX:+UseParallelGC          # Throughput-focused
java -XX:+UseConcMarkSweepGC     # Low-pause (CMS)
java -XX:+UseG1GC                # Region-based
```

### The Problem with CMS

- CMS was popular for low-pause scenarios.
- It suffered from **fragmentation** and **tuning complexity**.
- **CMS was deprecated in Java 9 and removed in Java 14.**

> This is one of the most common migration triggers for teams still on Java 8.

---

## 3. GC Evolution: Java 11 — G1 Becomes Default

G1 became the **default GC** in Java 9 (confirmed in Java 11).

### G1 Heap: Region-Based Layout

```
Heap
┌────┬────┬────┬────┬────┬────┐
│ R1 │ R2 │ R3 │ R4 │ R5 │ R6 │
├────┼────┼────┼────┼────┼────┤
│ R7 │ R8 │ R9 │ R10│ R11│ R12│
└────┴────┴────┴────┴────┴────┘
```

Instead of a single contiguous old generation, G1 treats the heap as a set of equal-sized regions, collecting the highest-garbage regions first — hence "Garbage-First."

**Why this matters:**
- Better for large heaps
- More predictable pause times
- Works well for microservices and high-allocation workloads

---

## 4. GC Evolution: Java 17 — G1 Matured + New Collectors

Java 17 didn't redesign G1 from scratch — it **continued improving it** across many JDK releases.

### G1 Improvements in Java 17 vs Java 8

| Area                    | Java 8 G1        | Java 17 G1                 |
|-------------------------|------------------|-----------------------------|
| Pause-time predictability | Decent         | Significantly improved      |
| Remembered sets          | Basic            | Improved                    |
| Concurrent processing    | Limited          | Expanded                    |
| Humongous object handling | Basic           | More efficient              |
| Heap reclamation         | Standard         | More aggressive             |
| GC ergonomics            | Manual tuning    | Better auto-tuning          |

### Application Stack Benefit

```
Spring Boot App
     ↓
Java 17 JVM
     ↓
Matured G1 GC
     ↓
OS / Container
```

The same Spring Boot code runs on a **substantially more capable GC** without any code changes.

---

## 5. ZGC — Ultra-Low Latency GC

ZGC was introduced experimentally and became **production-ready in Java 17**.

### The Problem ZGC Solves

In a traditional GC cycle:

```
App threads: ████████████ [STOP] ████████████
GC:                        ████████████
```

Stop-the-world pauses (even a few hundred ms) can ruin p99/p999 latency.

### ZGC's Approach

```
App threads: ████████████████████████████████
GC threads:       ████████████████
```

ZGC performs almost all work **concurrently** with application threads. Pauses are designed to stay **under 1ms**, regardless of heap size.

### Enable ZGC

```bash
java -XX:+UseZGC -Xms16g -Xmx16g application.jar
```

### When to Use ZGC

| Scenario                               | ZGC a good candidate? |
|----------------------------------------|-----------------------|
| Very large heap (8GB+)                 | ✅ Yes                |
| Strict p99 latency SLO (< 50ms)        | ✅ Yes                |
| Trading / payment / gaming backend     | ✅ Yes                |
| Large in-memory cache / recommendation | ✅ Yes                |
| Typical CRUD microservice, < 4GB heap  | ❌ G1 is usually fine |

### Example: Payment API with Strict SLA

```
Payment API
    ↓
Request processing (10–15ms normal)
    ↓
GC pause (300ms with G1 under pressure)
    ↓
SLA violation

→ ZGC candidate: test with -XX:+UseZGC
```

---

## 6. Shenandoah GC

Shenandoah targets the same problem as ZGC — **very low pause times** — but uses a different engineering approach.

### Key Feature: Concurrent Compaction

Standard GC compaction (stop-the-world):

```
Before: [Obj][dead][Obj][dead][Obj]
         ↓ pause
After:  [Obj][Obj][Obj][         ]
```

Shenandoah performs compaction **while the application runs**, avoiding long stop-the-world phases.

### Enable Shenandoah

```bash
java -XX:+UseShenandoahGC application.jar
```

### G1 vs ZGC vs Shenandoah Summary

```
              JAVA GC COLLECTORS
                     │
       ┌─────────────┼─────────────┐
       │             │             │
      G1            ZGC       Shenandoah
       │             │             │
  General       Ultra-low     Ultra-low
  purpose        latency       latency
       │             │             │
  Throughput    Large heap    Large heap
  + latency     + latency    + concurrent
  balance                     compaction
```

| GC         | Best For                          | Trade-off                     |
|------------|-----------------------------------|-------------------------------|
| G1         | Most Spring Boot microservices    | Higher pauses under pressure  |
| ZGC        | Very large heap + strict latency  | Higher CPU overhead           |
| Shenandoah | Large heap + low pause            | Different CPU/memory profile  |

---

## 7. G1 vs ZGC vs Shenandoah — Decision Guide

### Decision Flowchart

```
Is the heap very large (>8GB) AND latency critical?
          │
     ┌────┴────┐
    NO         YES
     │          │
    G1       Benchmark ZGC and Shenandoah
              under real workload
```

### Practical Microservices Mapping

| Service                          | Recommended GC | Reason                        |
|----------------------------------|----------------|-------------------------------|
| API Gateway                      | G1             | Throughput + reasonable latency |
| User / Order / Profile Service   | G1             | Typical CRUD, moderate heap   |
| Search Service                   | G1 (usually)   | Unless large in-memory index  |
| Real-time Recommendation Engine  | ZGC / Shenandoah (benchmark) | Large heap + p99 sensitivity |
| Payment Authorization            | ZGC (candidate) | Strict latency SLA           |
| In-memory Analytics              | ZGC / Shenandoah | Large heap, latency-sensitive |

> **Always benchmark with your actual workload before switching from G1.**

---

## 8. Container & Kubernetes Awareness

### The Old Problem

Java 8 was designed for physical or virtual machines:

```
JVM → sees the full machine
```

But modern deployments look like:

```
Kubernetes Pod
    └── Container (CPU: 2, Memory: 2Gi limit)
            └── JVM
```

Java 8 JVM would often see the **node's total memory** and over-size its heap, leading to OOMKills.

### Java 11/17 Fix

Modern Java correctly reads container resource limits from cgroups:

```yaml
# Kubernetes resource spec
resources:
  requests:
    memory: 512Mi
  limits:
    memory: 2Gi
    cpu: "2"
```

The JVM now understands:
- It has **2 CPUs** (not the full node)
- It has **2 GB memory** (not the full node)

This is critical for Spring Boot on Kubernetes — sizing heap based on container limits, not host resources.

---

## 9. JVM Memory Ergonomics

Modern JVMs (especially Java 17) make much better **automatic decisions** about:

- Heap size (based on available memory)
- GC thread count (based on available CPUs)
- Parallelism
- Container limit awareness

### Old Approach (manual tuning everything)

```bash
-Xms4g -Xmx4g
-XX:NewRatio=2
-XX:SurvivorRatio=8
-XX:MaxTenuringThreshold=15
-XX:ParallelGCThreads=8
-XX:ConcGCThreads=4
```

### Modern Approach

```bash
java -XX:MaxRAMPercentage=75.0 -jar application.jar
```

Let JVM ergonomics decide, and tune **only when GC metrics show a real problem**.

---

## 10. Compact Strings (Java 9+)

### Before Java 9 (Java 8)

```
String → char[] → 2 bytes per character
```

Every character consumed 2 bytes, even for plain ASCII/Latin-1 text.

### Java 9+ (Compact Strings)

```
String → byte[]
    ├── LATIN-1 content → 1 byte/character
    └── Unicode content  → 2 bytes/character (UTF-16)
```

### Why This Matters

Enterprise applications create enormous numbers of strings:

```
"userId", "username", "product", "orderId",
"paymentStatus", "address", "category"...
```

For typical REST API / JSON / Kafka workloads, Compact Strings can **cut String memory usage by ~50%** for ASCII-dominant data.

This directly reduces:
- Heap pressure
- GC frequency
- GC pause duration

---

## 11. JIT Compiler Improvements

The JVM execution pipeline:

```
Java Source
    ↓
Bytecode (.class)
    ↓
JVM Interpreter
    ↓
JIT Compiler (C1 / C2)
    ↓
Optimized Machine Code
```

Between Java 8 and Java 17, the JIT compiler improved across many areas:

| Optimization Area      | Description                                     |
|------------------------|-------------------------------------------------|
| Inlining               | More aggressive method inlining                 |
| Escape analysis        | Objects that don't escape can be stack-allocated |
| Intrinsics             | More CPU-specific fast paths                    |
| Loop optimizations     | Better vectorization                            |
| Deoptimization         | More precise, less conservative                 |

### The Important Insight

```java
// Plain Java 8 code
for (int i = 0; i < 1_000_000; i++) {
    processOrder(i);
}
```

This code uses **no Java 17 language features**, yet it executes differently — potentially faster — on a Java 17 JVM due to JIT improvements.

> You benefit from JIT improvements simply by upgrading the JVM, without changing a line of application code.

---

## 12. Security & Platform Upgrades

| Release  | Year | LTS |
|----------|------|-----|
| Java 8   | 2014 | ✅  |
| Java 11  | 2018 | ✅  |
| Java 17  | 2021 | ✅  |

Java 17 includes years of security improvements over Java 8:

- Updated TLS/SSL cipher suites
- Stronger cryptographic defaults
- Updated root certificates
- Stricter module encapsulation (strong encapsulation of JDK internals)
- Bug fixes in class libraries and the JVM itself
- Better OS and hardware support

For enterprises, LTS security coverage is often the primary migration driver — not language features.

---

## 13. Removed / Deprecated in Java 17

Java evolution isn't just about adding features. Removing obsolete ones matters for security and maintainability.

| Component             | Status in Java 17      | Notes                            |
|-----------------------|------------------------|----------------------------------|
| CMS GC                | ❌ Removed             | Replaced by G1 / ZGC             |
| Nashorn JS Engine     | ❌ Removed             | Was deprecated in Java 11        |
| Applet API            | ❌ Removed             |                                  |
| Security Manager      | ⚠️ Deprecated for removal |                               |
| RMI Activation        | ❌ Removed             |                                  |
| Biased Locking        | ⚠️ Deprecated          | Usually net negative in modern JVMs |

---

## 14. What to Learn for Senior Interviews

```
Java 17 Knowledge Tree
        │
        ├── Language Features (Tier 1)
        │     ├── Records
        │     ├── Sealed Classes
        │     ├── Pattern Matching (instanceof)
        │     ├── Switch Expressions
        │     └── Text Blocks
        │
        ├── Java 8 Core (Still Required)
        │     ├── Lambda & Functional Interfaces
        │     ├── Stream API
        │     ├── Optional
        │     ├── CompletableFuture
        │     └── Date/Time API
        │
        └── JVM / Runtime (Differentiator for Senior Roles)
              ├── G1 GC internals
              ├── ZGC use cases
              ├── Shenandoah use cases
              ├── JIT & escape analysis
              ├── Compact Strings
              ├── Container awareness
              ├── JVM ergonomics
              └── GC metrics (JFR, GC logs, p99 latency)
```

---

## 15. Interview-Ready Answers

### Q: "Why Java 17? What's the advantage over Java 8?"

❌ **Weak answer:**
> "Java 17 has sealed classes, records, text blocks, and pattern matching."

✅ **Senior answer:**
> "Java 17 is valuable not only for language enhancements but because it ships a significantly more mature JVM. Compared to Java 8, it provides a matured G1 collector, production-ready low-latency collectors (ZGC and Shenandoah), proper container and Kubernetes awareness, improved JVM ergonomics, JIT improvements, Compact Strings, and a stronger security baseline. The removal of CMS is itself a migration trigger for many teams. For enterprise Spring Boot microservices, the runtime improvements often matter more than the language features."

---

### Q: "When would you use ZGC or Shenandoah instead of G1?"

❌ **Weak answer:**
> "ZGC is faster than G1."

✅ **Senior answer:**
> "G1 is the right general-purpose starting point — it balances throughput and predictable pause times well. I'd consider ZGC or Shenandoah specifically when there's a combination of a large heap and strict p99/p999 latency requirements — for example, a payment authorization service or a real-time recommendation engine with tens of GBs of heap. I wouldn't choose them because they're newer; I'd benchmark with actual GC logs, JFR, and p99 latency measurements under production-representative load before switching."

---

### Q: "What GC would you choose for a Spring Boot microservice?"

✅ **Answer:**
> "G1 is my default. I'd use -XX:MaxRAMPercentage to size the heap based on container limits rather than hardcoding -Xmx. I'd collect GC logs and JFR data in production. If p99 latency is a hard SLO and the heap is large, I'd benchmark ZGC as an alternative. The choice always follows measurement, not assumption."

---

## Quick Reference

```
           GC SELECTION MENTAL MODEL
                    │
       ┌────────────┼────────────┐
       │            │            │
      G1           ZGC       Shenandoah
       │            │            │
  General       Ultra-low     Ultra-low
  purpose        latency       latency
  (default)    + large heap  + large heap
                              + concurrent
                               compaction

RULE: Start with G1. Switch only when GC metrics
      under real load justify the change.
```

> **Note on Base64:** `java.util.Base64` was introduced in **Java 8**, not Java 17. Similarly, switch expressions became standard in Java 14 — they were still a preview feature in Java 17 only for pattern matching on switch.
