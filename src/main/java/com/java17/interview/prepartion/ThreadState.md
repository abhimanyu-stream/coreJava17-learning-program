# Java Thread States — Complete Guide

> Java threads have a well-defined lifecycle managed by the JVM.
> Understanding thread states is essential for debugging concurrency issues, reading thread dumps, and answering senior-level interview questions.

---

## Table of Contents

1. [Thread State Overview](#1-thread-state-overview)
2. [All Six Thread States](#2-all-six-thread-states)
3. [Thread Lifecycle Diagram](#3-thread-lifecycle-diagram)
4. [State Transitions — What Causes Each Move](#4-state-transitions--what-causes-each-move)
5. [NEW](#5-new)
6. [RUNNABLE](#6-runnable)
7. [BLOCKED](#7-blocked)
8. [WAITING](#8-waiting)
9. [TIMED_WAITING](#9-timed_waiting)
10. [TERMINATED](#10-terminated)
11. [BLOCKED vs WAITING vs TIMED_WAITING](#11-blocked-vs-waiting-vs-timed_waiting)
12. [Reading a Thread Dump](#12-reading-a-thread-dump)
13. [Common Concurrency Problems by State](#13-common-concurrency-problems-by-state)
14. [Interview Quick Reference](#14-interview-quick-reference)

---

## 1. Thread State Overview

Java defines thread states in the `java.lang.Thread.State` enum:

```java
public enum State {
    NEW,           // ← new Thread(...)               — object created, OS thread not yet started
    RUNNABLE,      // ← t.start()                     — running on CPU or ready, waiting for CPU slice
    BLOCKED,       // ← synchronized(lock) { }        — waiting to acquire a monitor lock held by another thread
    WAITING,       // ← wait()  join()  park()        — waiting indefinitely for explicit notification
    TIMED_WAITING, // ← sleep(ms)  wait(ms)  join(ms) — waiting with a timeout
    TERMINATED     // ← run() returns / throws        — execution finished, cannot be restarted
}
```

### Exact method → state mapping

| Method Call                        | State it causes       | Notes                                                          |
|------------------------------------|-----------------------|----------------------------------------------------------------|
| `new Thread(...)`                  | `NEW`                 | OS thread not created yet                                      |
| `t.start()`                        | `RUNNABLE`            | JVM hands thread to OS scheduler                               |
| `synchronized(obj) { }`           | `BLOCKED`             | Only if another thread holds `obj`'s monitor                  |
| `obj.wait()`                       | `WAITING`             | Releases the monitor; needs `notify()` / `notifyAll()` to wake |
| `t.join()`                         | `WAITING`             | Caller waits until `t` terminates                              |
| `LockSupport.park()`               | `WAITING`             | Woken by `LockSupport.unpark(t)` or interrupt                 |
| `Thread.sleep(800)`                | `TIMED_WAITING`       | Does **not** release any held lock                             |
| `obj.wait(800)`                    | `TIMED_WAITING`       | Releases the monitor; wakes on timeout OR `notify()`          |
| `t.join(77)`                       | `TIMED_WAITING`       | Caller waits at most 77 ms for `t` to finish                  |
| `LockSupport.parkNanos(n)`         | `TIMED_WAITING`       | Nanosecond-precision timed park                                |
| `LockSupport.parkUntil(deadline)`  | `TIMED_WAITING`       | Parks until absolute epoch-ms deadline                         |
| `run()` completes / throws         | `TERMINATED`          | Final state — cannot call `start()` again                      |

```
NEW ──────── t.start() ──────────────────────────────────► RUNNABLE
                                                               │
                         ┌─────────────────────────────────────┤
                         │                 │                   │
                  synchronized         wait()              sleep(ms)
                  (lock held           join()              wait(ms)
                  by other)            park()              join(ms)
                         │                 │             parkNanos(n)
                         ▼                 ▼                   ▼
                      BLOCKED          WAITING          TIMED_WAITING
                         │                 │                   │
                  lock released       notify() /          timeout /
                                      notifyAll() /       notify() /
                                      interrupt()         interrupt()
                         │                 │                   │
                         └─────────────────┴───────────────────┘
                                           │
                                        RUNNABLE
                                           │
                                    run() finishes
                                           │
                                       TERMINATED
```

You can read a thread's current state at runtime:

```java
Thread t = new Thread(() -> System.out.println("Hello"));
System.out.println(t.getState()); // NEW
t.start();
System.out.println(t.getState()); // RUNNABLE (likely)
```

---

## 2. All Six Thread States

| State           | Short Description                                                      |
|-----------------|------------------------------------------------------------------------|
| `NEW`           | Thread created but `start()` not yet called                           |
| `RUNNABLE`      | Running on CPU, or ready and waiting for CPU time from the OS         |
| `BLOCKED`       | Waiting to acquire a monitor lock held by another thread              |
| `WAITING`       | Waiting indefinitely for another thread to perform a specific action  |
| `TIMED_WAITING` | Waiting for a specific duration or until notified                     |
| `TERMINATED`    | `run()` method has completed (normally or via exception)              |

---

## 3. Thread Lifecycle Diagram

```
  ┌─────────────────────────────────────────────────────────────────────┐
  │                                                                     │
  │   new Thread()          t.start()                                  │
  │   ───────────►  NEW  ──────────────►  RUNNABLE  ◄─────────────┐   │
  │                                          │                     │   │
  │                              ┌───────────┼───────────┐         │   │
  │                              │           │           │         │   │
  │                              ▼           ▼           ▼         │   │
  │                           BLOCKED    WAITING   TIMED_WAITING   │   │
  │                              │           │           │         │   │
  │                              │  lock     │ notify()  │ timeout │   │
  │                              │  acquired │ notifyAll │ elapsed │   │
  │                              └───────────┴───────────┘         │   │
  │                                          │                     │   │
  │                                          └─────────────────────┘   │
  │                                                                     │
  │                          run() completes                           │
  │                   RUNNABLE  ──────────────►  TERMINATED            │
  │                                                                     │
  └─────────────────────────────────────────────────────────────────────┘
```

---

## 4. State Transitions — What Causes Each Move

| From              | To              | Triggered By                                                         |
|-------------------|-----------------|----------------------------------------------------------------------|
| `NEW`             | `RUNNABLE`      | `thread.start()`                                                     |
| `RUNNABLE`        | `BLOCKED`       | Trying to enter a `synchronized` block/method locked by another thread |
| `RUNNABLE`        | `WAITING`       | `object.wait()`, `thread.join()`, `LockSupport.park()`              |
| `RUNNABLE`        | `TIMED_WAITING` | `Thread.sleep(ms)`, `object.wait(ms)`, `thread.join(ms)`, `LockSupport.parkNanos()` |
| `RUNNABLE`        | `TERMINATED`    | `run()` completes normally or throws an uncaught exception           |
| `BLOCKED`         | `RUNNABLE`      | Lock is released by the owning thread                                |
| `WAITING`         | `RUNNABLE`      | `object.notify()` / `notifyAll()`, `thread.interrupt()`             |
| `TIMED_WAITING`   | `RUNNABLE`      | Timeout expires, `notify()` / `notifyAll()`, `thread.interrupt()`   |

---

## 5. NEW

A thread is in `NEW` state after it is created but before `start()` is called.

```java
Thread t = new Thread(() -> System.out.println("Running"));
System.out.println(t.getState()); // NEW

t.start();
// Now state moves to RUNNABLE
```

**Key points:**
- The OS thread has NOT been created yet.
- Calling `start()` twice on the same thread throws `IllegalThreadStateException`.

---

## 6. RUNNABLE

A thread is `RUNNABLE` when it is either:
- **actively executing** on a CPU core, or
- **ready to run** and waiting for the OS scheduler to assign a CPU.

```java
Thread t = new Thread(() -> {
    // Heavy computation — thread is RUNNABLE
    long sum = 0;
    for (long i = 0; i < 1_000_000_000L; i++) sum += i;
    System.out.println(sum);
});
t.start();
System.out.println(t.getState()); // RUNNABLE
```

**Key points:**
- The JVM does not distinguish between "running on CPU" and "waiting for CPU".
- Both map to `RUNNABLE` from the JVM's perspective.
- I/O operations (reading from socket, file) may also show as `RUNNABLE` in thread dumps even though the thread is blocked at the OS level.

---

## 7. BLOCKED

A thread is `BLOCKED` when it is trying to enter a `synchronized` block or method, but **another thread currently holds that monitor lock**.

```java
Object lock = new Object();

Thread t1 = new Thread(() -> {
    synchronized (lock) {
        // Holds lock for 5 seconds
        try { Thread.sleep(5000); } catch (InterruptedException e) {}
    }
});

Thread t2 = new Thread(() -> {
    synchronized (lock) {   // t2 is BLOCKED here — waiting for t1 to release the lock
        System.out.println("t2 acquired lock");
    }
});

t1.start();
Thread.sleep(100); // ensure t1 gets the lock first
t2.start();
Thread.sleep(100);
System.out.println(t2.getState()); // BLOCKED
```

**Key points:**
- `BLOCKED` is specific to `synchronized` monitor locks.
- `java.util.concurrent` locks (e.g. `ReentrantLock`) put threads in `WAITING` or `TIMED_WAITING`, not `BLOCKED`.
- In thread dumps, `BLOCKED` threads show the lock they are waiting for and the thread that owns it.

---

## 8. WAITING

A thread is in `WAITING` state when it is **waiting indefinitely** for another thread to perform an action.

### Methods that cause WAITING

| Method                   | Waiting for                                           |
|--------------------------|-------------------------------------------------------|
| `object.wait()`          | `object.notify()` or `object.notifyAll()`            |
| `thread.join()`          | The target thread to terminate                        |
| `LockSupport.park()`     | `LockSupport.unpark(thread)` or interrupt            |

```java
Object lock = new Object();

Thread waiter = new Thread(() -> {
    synchronized (lock) {
        try {
            lock.wait();  // → WAITING state
            System.out.println("Notified!");
        } catch (InterruptedException e) {}
    }
});

waiter.start();
Thread.sleep(100);
System.out.println(waiter.getState()); // WAITING

synchronized (lock) {
    lock.notify(); // waiter moves back to RUNNABLE
}
```

**Key points:**
- `wait()` must be called inside a `synchronized` block on the same object — otherwise `IllegalMonitorStateException`.
- Always use `wait()` inside a `while` loop to guard against spurious wakeups:

```java
synchronized (lock) {
    while (!conditionMet) {   // ✅ while, not if
        lock.wait();
    }
}
```

---

## 9. TIMED_WAITING

A thread is in `TIMED_WAITING` when it is waiting **for a specific duration** or until notified — whichever comes first.

### Methods that cause TIMED_WAITING

| Method                      | Returns when                                              |
|-----------------------------|-----------------------------------------------------------|
| `Thread.sleep(ms)`          | Duration expires or thread is interrupted                |
| `object.wait(ms)`           | Duration expires, `notify()`, or interrupt               |
| `thread.join(ms)`           | Duration expires or target thread finishes               |
| `LockSupport.parkNanos(ns)` | Duration expires or `unpark()`                           |
| `LockSupport.parkUntil(ms)` | Deadline passes or `unpark()`                            |

```java
Thread t = new Thread(() -> {
    try {
        Thread.sleep(5000); // → TIMED_WAITING for 5 seconds
    } catch (InterruptedException e) {}
});

t.start();
Thread.sleep(100);
System.out.println(t.getState()); // TIMED_WAITING
```

**Key points:**
- `Thread.sleep()` does NOT release any monitor locks the thread holds — important distinction from `wait()`.
- `object.wait(ms)` DOES release the monitor lock while waiting.

---

## 10. TERMINATED

A thread reaches `TERMINATED` state when its `run()` method finishes — either normally or by throwing an uncaught exception.

```java
Thread t = new Thread(() -> System.out.println("Done"));
t.start();
t.join(); // wait for t to finish
System.out.println(t.getState()); // TERMINATED
```

**Key points:**
- A `TERMINATED` thread cannot be restarted. Calling `start()` again throws `IllegalThreadStateException`.
- The `Thread` object still exists in memory and its state can be queried.

---

## 11. BLOCKED vs WAITING vs TIMED_WAITING

This is the most common interview confusion point.

| Aspect                  | BLOCKED                            | WAITING                          | TIMED_WAITING                      |
|-------------------------|------------------------------------|----------------------------------|------------------------------------|
| **Lock type**           | `synchronized` monitor only        | Any (`wait`, `join`, `park`)     | Any (`sleep`, `wait(ms)`, `join(ms)`) |
| **Duration**            | Indefinite                         | Indefinite                       | Has a timeout                      |
| **Who wakes it up?**    | OS — when lock is released         | Another thread calling `notify()`| Timeout expires OR `notify()`      |
| **Releases lock?**      | N/A (hasn't acquired it yet)       | Yes (`wait()` releases lock)     | Yes if `wait(ms)`; No if `sleep()` |
| **JUC locks**           | ❌ ReentrantLock uses WAITING      | ✅                               | ✅                                 |

### Quick mental model

```
Waiting for a synchronized lock?         → BLOCKED
Waiting indefinitely for notification?   → WAITING
Waiting with a timeout?                  → TIMED_WAITING
```

---

## 12. Reading a Thread Dump

Thread dumps show the state of every thread. Key things to look for:

### BLOCKED thread in a dump

```
"http-nio-8080-exec-3" #25 daemon prio=5
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.example.OrderService.processOrder(OrderService.java:42)
        - waiting to lock <0x000000076b3e2f30> (a java.lang.Object)
        - locked by "http-nio-8080-exec-1" #23
```

→ Thread 3 is waiting for a lock held by Thread 1. Possible deadlock or lock contention.

### WAITING thread in a dump

```
"worker-thread-1" #30 prio=5
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        at com.example.TaskQueue.take(TaskQueue.java:55)
        - waiting on <0x000000076b3e2f90>
```

→ Thread is waiting for a `notify()`. Normal if it's a worker waiting for tasks.

### TIMED_WAITING thread in a dump

```
"scheduling-thread" #12 daemon prio=5
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at com.example.Scheduler.run(Scheduler.java:33)
```

→ Thread is sleeping — normal for scheduled/polling threads.

---

## 13. Common Concurrency Problems by State

| Problem         | Thread State in Dump   | Symptom                                          |
|-----------------|------------------------|--------------------------------------------------|
| **Deadlock**    | All involved threads `BLOCKED` | Application hangs; no progress             |
| **Livelock**    | Threads `RUNNABLE`     | CPU busy but no useful work done                 |
| **Starvation**  | Thread stuck in `BLOCKED` / `WAITING` | Low-priority thread never gets CPU/lock |
| **Lock contention** | Many threads `BLOCKED` on same lock | High latency, low throughput             |
| **Missed notify** | Thread stuck in `WAITING` | `notify()` called before `wait()`, so thread waits forever |

### Deadlock example

```java
Object lockA = new Object();
Object lockB = new Object();

// Thread 1 acquires A, then tries to acquire B
Thread t1 = new Thread(() -> {
    synchronized (lockA) {
        synchronized (lockB) { /* ... */ }
    }
});

// Thread 2 acquires B, then tries to acquire A — DEADLOCK
Thread t2 = new Thread(() -> {
    synchronized (lockB) {
        synchronized (lockA) { /* ... */ }
    }
});
```

```
t1: BLOCKED — waiting for lockB (held by t2)
t2: BLOCKED — waiting for lockA (held by t1)
→ Neither can proceed
```

**Detection:** JVM thread dump shows circular lock dependency. Use `jstack <pid>` or JFR.

---

## 14. Interview Quick Reference

### State summary

| State           | Cause                                      | Woken by                              |
|-----------------|--------------------------------------------|---------------------------------------|
| `NEW`           | `new Thread()`                             | `thread.start()`                      |
| `RUNNABLE`      | `start()` called                           | OS scheduler                          |
| `BLOCKED`       | Waiting for `synchronized` lock            | Lock released by owning thread        |
| `WAITING`       | `wait()`, `join()`, `park()`               | `notify()`, `join` complete, `unpark` |
| `TIMED_WAITING` | `sleep(ms)`, `wait(ms)`, `join(ms)`        | Timeout OR `notify()` / `unpark()`    |
| `TERMINATED`    | `run()` returns or throws                  | N/A                                   |

### Key distinctions to memorise

```
sleep() vs wait()
─────────────────
sleep()  → TIMED_WAITING, does NOT release lock, no need for synchronized block
wait()   → WAITING,       DOES release lock,     MUST be inside synchronized block

BLOCKED vs WAITING
───────────────────
BLOCKED  → waiting for synchronized monitor lock
WAITING  → waiting for explicit notification (notify/unpark/join complete)

notify() vs notifyAll()
────────────────────────
notify()     → wakes ONE waiting thread (unpredictable which one)
notifyAll()  → wakes ALL waiting threads (they then compete for the lock)
```

### Senior-level interview answer

> "Java threads have six states defined in `Thread.State`. `NEW` is before `start()`. `RUNNABLE` covers both executing and ready-to-run. `BLOCKED` is specific to contention on a `synchronized` monitor — JUC locks use `WAITING` instead. `WAITING` and `TIMED_WAITING` differ only in whether there is a timeout. `TERMINATED` is final and irreversible. In production, I use thread dumps (`jstack`) and JFR to identify threads stuck in `BLOCKED` (lock contention or deadlock) or unexpectedly stuck in `WAITING` (missed notification). The most common mistake is confusing `BLOCKED` with `WAITING` — they have different causes and different solutions."
