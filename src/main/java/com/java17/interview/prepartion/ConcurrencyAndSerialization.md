# Java Concurrency: Thread States, Executors, and Serialization

## Part 1: Thread States

Threads in Java are not just "running" or "not running." They move through a small, well-defined state machine. The enum that defines these states is `java.lang.Thread.State`.

There are exactly **six constants**. Let's walk through them like we're watching a thread live its dramatic little life.

---

### 1. NEW

**This is the "idea phase."**

You created the thread object:

```java
Thread t = new Thread(() -> {
    System.out.println("Hello");
});
```

At this point, `t` exists in memory, but the OS has no clue about it.
You have not called `start()` yet.

**State:** NEW

It's like buying a gym membership but never going.

---

### 2. RUNNABLE

Now you call:

```java
t.start();
```

The thread moves to **RUNNABLE**.

**Important subtlety:**

RUNNABLE in Java includes both:

- Actually running on CPU
- Waiting in the OS scheduler queue for CPU time

Java does not distinguish between "ready" and "running."
If it's eligible to run, it's RUNNABLE.

Think of it as standing in line for coffee or actively ordering — either way, you're in the system.

---

### 3. BLOCKED

This happens when a thread is waiting to acquire a monitor lock (i.e., synchronized).

**Example:**

```java
synchronized(lock) {
    // critical section
}
```

If another thread already owns that lock, this thread goes to **BLOCKED**.

**Key idea:**

- BLOCKED means waiting for a monitor lock
- It does NOT mean sleeping
- It does NOT mean waiting on `wait()`

It's strictly about lock contention.

This is where many performance problems live.

---

### 4. WAITING

This means the thread is waiting indefinitely for another thread to perform a particular action.

**Common triggers:**

- `object.wait();`
- `thread.join();`
- `LockSupport.park();`

Unlike BLOCKED, this is intentional waiting — not fighting for a lock.

The thread will stay here until another thread:

- calls `notify()` / `notifyAll()`
- or the joined thread finishes

This is **cooperative waiting**.

---

### 5. TIMED_WAITING

Same idea as WAITING, but with a timeout.

**Examples:**

- `Thread.sleep(1000);`
- `object.wait(5000);`
- `thread.join(2000);`

The thread either:

- wakes up when time expires
- or wakes earlier if notified/interrupted

This state is often used for backoff strategies, polling loops, rate limiting, etc.

---

### 6. TERMINATED

The thread's `run()` method has completed.

It cannot be restarted.

```java
t.start();
t.start(); // throws IllegalThreadStateException
```

Once TERMINATED, it's done forever.

Threads are not reusable. They are single-use biological organisms.

---

## Quick Mental Model

Lifecycle flow usually looks like:

```
NEW → RUNNABLE → (BLOCKED / WAITING / TIMED_WAITING) → RUNNABLE → TERMINATED
```

But real systems are chaotic. Threads can bounce between RUNNABLE and WAITING thousands of times per second.

---

## Important Distinctions Most Developers Miss

### BLOCKED vs WAITING

| Aspect | BLOCKED | WAITING |
|---|---|---|
| Cause | waiting for synchronized lock | waiting because you explicitly told it to |
| Intention | Fighting for lock | Cooperative waiting |
| Shows in dumps | Lock contention | Coordination issue |

---

## How to Check Thread State

```java
Thread.State state = t.getState();
System.out.println(state);
```

This is useful for diagnostics — but don't build logic based on thread state.
Thread state is a snapshot, not a guarantee.

---

## Under the Hood Reality

The JVM maps these Java-level states onto OS-level thread states.

At the OS level there are more granular states, but Java intentionally abstracts them into these six.

This simplification prevents you from writing scheduling-dependent code — which is good. The scheduler is not your friend. It is a chaotic neutral entity.

---

## Philosophical Note

**Concurrency is less about threads and more about coordination.**

- Thread states are symptoms.
- The real game is memory visibility, ordering, and contention.

---

## Thread State Diagram

```
         NEW
          |
          v
      RUNNABLE
       /  |  \
      /   |   \
   BLOCKED |  WAITING
     |     |     |
     |  TIMED_WAITING
     |     |
     \     /
      \   /
       RUNNABLE
          |
          v
      TERMINATED
```

---

# Part 2: execute() vs submit()

Both belong to `ExecutorService`. Both schedule tasks. But they return different power.

**Core idea:**

- `execute()` → fire-and-forget
- `submit()` → fire-and-track

---

## execute()

Defined in `Executor` interface.

```java
ExecutorService pool = Executors.newFixedThreadPool(2);

pool.execute(() -> {
    System.out.println("Running task");
});
```

### Characteristics:

- Takes a `Runnable`
- Returns `void`
- Cannot get result
- Cannot directly detect exceptions
- If task throws exception → goes to thread's `UncaughtExceptionHandler`

### Use when:

- You don't care about result
- You don't need to track completion
- You want minimal overhead

Think of it as launching a rocket without a telemetry system.

---

## submit()

Defined in `ExecutorService`.

```java
ExecutorService pool = Executors.newFixedThreadPool(2);

Future<String> future = pool.submit(() -> {
    return "Hello";
});

String result = future.get();
```

### Characteristics:

- Accepts `Runnable` OR `Callable`
- Returns `Future`
- You can:
  - `get()` result
  - `cancel()`
  - check `isDone()`
- Exceptions are captured inside `Future`

### Example with exception:

```java
Future<?> f = pool.submit(() -> {
    throw new RuntimeException("Boom");
});

try {
    f.get();
} catch (ExecutionException e) {
    System.out.println("Caught: " + e.getCause());
}
```

With `execute()`, that exception might just print to stderr and vanish into the void.

---

## Major Differences

| Aspect | execute() | submit() |
|---|---|---|
| Returns | void | Future |
| Result retrieval | Impossible | get() |
| Exception handling | Thread handler | ExecutionException |
| Overhead | Lighter | Heavier |
| Accepts | Runnable | Runnable OR Callable |

In real production systems: **submit() is usually preferred** because observability matters.

---

# Part 3: Semaphores and Mutex

## What is a Semaphore?

A semaphore controls access to a limited number of permits.

Think of it as:
**"Only N threads may enter this region simultaneously."**

In Java:

```java
Semaphore semaphore = new Semaphore(3); // 3 permits

semaphore.acquire();
try {
    // critical section
} finally {
    semaphore.release();
}
```

If 3 threads enter, the 4th will BLOCK until a permit is released.

### Use case:

- Limit concurrent DB connections
- Limit API calls
- Throttle resource usage

### Key features:

- Controls concurrency level.
- Can allow multiple threads at once.
- Uses `acquire()` and `release()`.
- Can be fair (FIFO) or non-fair.
- Built on AQS internally.

---

## Binary Semaphore

If you create:

```java
Semaphore s = new Semaphore(1);
```

Now only one permit exists.

This behaves like a **mutex**.

---

## What is a Mutex?

**Mutex = Mutual Exclusion lock.**

Only ONE thread at a time.

In Java, typical mutex forms are:

- `synchronized`
- `ReentrantLock`
- Binary Semaphore

### Example using ReentrantLock:

```java
ReentrantLock lock = new ReentrantLock();

lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

### Mutex properties:

- Exclusive ownership
- Only owner can release
- Provides mutual exclusion

---

## Semaphore vs Mutex

| Aspect | Semaphore | Mutex |
|---|---|---|
| Threads allowed | Multiple (N permits) | One |
| Ownership | Not enforced | Enforced |
| Use case | Resource pools | Critical sections |
| Fairness | Can be FIFO | Depends on implementation |

**Subtle difference:**
- Semaphore is about **limiting access count**.
- Mutex is about **protecting shared state**.

---

## Practical Example: Rate Limiting with Semaphore

```java
Semaphore limiter = new Semaphore(5);

public void handleRequest() {
    try {
        limiter.acquire();
        // process request
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    } finally {
        limiter.release();
    }
}
```

Only 5 concurrent requests allowed.

---

## Deep Insight

Concurrency tools answer different questions:

| Tool | Question |
|---|---|
| execute vs submit | Do I need tracking? |
| Semaphore | How many threads may proceed? |
| Mutex | Who gets exclusive access? |
| Volatile | Who sees updates? |
| CAS | Can I avoid blocking? |
| ForkJoin | How do I balance CPU work? |

The real engineering decision isn't which API to memorize.
It's: **what type of contention pattern am I dealing with?**

---

# Part 4: Future and Callable

## What is Callable?

`Callable<V>` is like an **upgraded Runnable**.

### Runnable:

- Has `run()`
- Returns nothing
- Cannot throw checked exceptions

### Callable:

- Has `call()`
- Returns a value of type `V`
- Can throw checked exceptions

### Definition:

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

### Example:

```java
class SquareDoubleCallable implements Callable<Double> {

    private final double number;

    SquareDoubleCallable(double number) {
        this.number = number;
    }

    @Override
    public Double call() {
        return number * number;
    }
}
```

This is a task that computes something and returns a result.

**Callable answers:**
"What do you want me to compute?"

---

## What is Future?

`Future<V>` represents the result of an asynchronous computation.

Think of it as:
**"I owe you a value. Come back later."**

### Core methods:

- `get()`
- `get(timeout, unit)`
- `cancel()`
- `isDone()`
- `isCancelled()`

### Example:

```java
ExecutorService executor = Executors.newFixedThreadPool(2);

Future<Double> future =
        executor.submit(new SquareDoubleCallable(2.2));

Double result = future.get(); // blocks until done
```

**Important:**
`get()` blocks until computation finishes.

**Future answers:**
"Is it done yet? If so, give me the result."

---

## How Callable and Future Are Related

When you submit a `Callable` to `ExecutorService`:

```java
Future<Double> futureDouble =
    executor.submit(new SquareDoubleCallable(2.2));
```

**What happens internally?**

```
Callable is wrapped in a FutureTask
       ↓
Task runs on worker thread
       ↓
Result stored internally
       ↓
Future returned immediately to caller
```

So:

- **Callable** = the computation
- **Future** = the handle to its eventual result

They are tightly coupled through `ExecutorService`.

---

## Why Not Use Runnable?

### Runnable:

```java
executor.submit(() -> {
    System.out.println("Hello");
});
```

No result.

### Callable:

```java
executor.submit(() -> {
    return 42;
});
```

Returns `Future<Integer>`.

**So if you need:**

- Result
- Exception propagation
- Cancellation

**Use Callable.**

---

## Exception Handling Difference

`Callable` allows checked exceptions:

```java
Callable<Integer> task = () -> {
    if (true) throw new Exception("Failure");
    return 10;
};
```

Then:

```java
try {
    future.get();
} catch (ExecutionException e) {
    System.out.println(e.getCause());
}
```

Exceptions are wrapped in `ExecutionException`.

This is powerful because:
**You don't lose failures silently.**

---

## Life Cycle Relationship

```
submit(callable) → returns Future
       ↓
Future.get() → blocks until call() completes
       ↓
call() result → stored in Future
       ↓
cancel() → attempts to interrupt execution
```

So **Callable produces.**
**Future consumes.**

---

## Under the Hood

`ExecutorService` wraps `Callable` in `FutureTask<V>`.

**FutureTask:**

- Implements `Runnable`
- Implements `Future`

That's the glue.

**Conceptually:**

```
Callable → wrapped → FutureTask → executed by thread pool → Future returned
```

It's a bridge between computation and coordination.

---

## Practical Example with Multiple Tasks

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

List<Callable<Integer>> tasks = List.of(
    () -> 10,
    () -> 20,
    () -> 30
);

List<Future<Integer>> futures = executor.invokeAll(tasks);

for (Future<Integer> f : futures) {
    System.out.println(f.get());
}
```

`invokeAll` waits for all tasks to complete.

---

## Callable vs Runnable Comparison

| Feature | Callable | Runnable |
|---|---|---|
| Return type | V (any type) | void |
| Method | call() | run() |
| Checked exceptions | Yes | No |
| Result retrieval | Via Future | Not possible |
| Use with | ExecutorService | Executor/Thread |

---

# Part 5: serialVersionUID

Now we step into one of Java's more quietly dangerous corners: serialization.

---

## What is serialVersionUID?

When a class implements `Serializable`, the JVM assigns it a version identifier called `serialVersionUID`.

It's a `long` value used during deserialization to verify class compatibility.

### Example:

```java
import java.io.Serializable;

class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
}
```

### When serializing:

- Object data is written to a stream
- Class name is recorded
- serialVersionUID is recorded

### When deserializing:

- JVM loads the class
- Compares stored UID with current class UID
- If mismatch → `InvalidClassException`

It's a version stamp for the class structure.

---

## What Happens If You Don't Define It?

If you omit it:

```java
class User implements Serializable {
    private String name;
}
```

The JVM generates one automatically.

### How?

It hashes:

- Class name
- Fields
- Methods
- Interfaces
- Modifiers
- etc.

### That means:

If you change anything structural:

- Add field
- Remove field
- Change method signature
- Modify access modifier

The generated UID may change.

Then when you deserialize older data:

```
java.io.InvalidClassException:
local class incompatible:
stream classdesc serialVersionUID = X,
local class serialVersionUID = Y
```

**Game over.**

---

## Why This Happens

- Serialization is binary format.
- The stream assumes the class layout matches.

If the layout changes, the JVM refuses to deserialize because:
**It cannot safely map fields.**

This is defensive programming at runtime.

---

## Example of Failure

**Version 1:**

```java
class User implements Serializable {
    private String name;
}
```

You serialize an object.

**Later, you change class:**

```java
class User implements Serializable {
    private String name;
    private int age;
}
```

Now deserializing old data may fail if the auto-generated UID changed.

That's the risk of not defining `serialVersionUID`.

---

## Impact of Not Defining serialVersionUID

- Unpredictable compatibility failures
- `InvalidClassException`
- Inability to read old serialized data
- Deployment version mismatches breaking distributed systems
- Hard-to-debug production issues

It's especially dangerous in:

- RMI
- HTTP session clustering
- Caching serialized objects
- Distributed systems

---

## How to Avoid InvalidClassException

### 1️⃣ Always Define serialVersionUID Explicitly

```java
private static final long serialVersionUID = 1L;
```

Now you control versioning.

If you make compatible changes:
Keep same UID.

If you make incompatible changes:
Change UID manually.

**You become the version authority.**

---

### 2️⃣ Understand Compatible vs Incompatible Changes

#### Compatible (usually safe):

- Adding new fields
- Adding new methods
- Changing method body

#### Incompatible:

- Removing fields
- Changing field type
- Changing class hierarchy
- Removing Serializable

Even with explicit UID, incompatible structural changes can cause subtle bugs. JVM will deserialize but state may be weird.

---

### 3️⃣ Advanced Control: Custom Serialization

You can define:

```java
private void writeObject(ObjectOutputStream out)
private void readObject(ObjectInputStream in)
```

This lets you control version migration manually.

But now you're writing binary protocol code. Respect the dragons.

---

## Philosophical Note

Serialization is basically freezing an object graph in time.

**serialVersionUID says:**
"This frozen shape corresponds to this exact class identity."

If the class evolves, the frozen shape may no longer match reality.

The UID is the handshake agreement between past and present.

---

## Modern Reality Check

Java native serialization is:

- Slow
- Fragile
- Security-sensitive
- Often discouraged

Many systems now prefer:

- JSON (Jackson)
- Protobuf
- Avro
- Kryo

**Why?**

Because **explicit schemas > implicit class layout hashing**.

Native serialization couples binary format to internal class structure. That's brittle architecture.

---

## Quick Reference Table

| Aspect | Impact |
|---|---|
| Defining serialVersionUID | Full control over versioning |
| Not defining serialVersionUID | Auto-generated, fragile |
| Structural changes | May invalidate old data |
| Exception on mismatch | InvalidClassException |
| Recovery | Requires UID update or data migration |

---

## Short Answer Summary

**What is serialVersionUID?**
→ A version identifier for Serializable classes used during deserialization compatibility checks.

**Impact of not defining it?**
→ JVM auto-generates one based on class structure.
→ Any structural change may alter it.
→ Causes `InvalidClassException` when deserializing old objects.

**How to avoid issues?**
→ Explicitly declare `private static final long serialVersionUID`
→ Manage version changes intentionally.

---

## Final Thought

Serialization is about object identity across time.

Concurrency is about object visibility across threads.

Distributed systems are about object consistency across machines.

And all three are really about one thing:

**Maintaining truth when reality is fragmented.**
