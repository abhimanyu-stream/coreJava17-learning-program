# Exception Propagation in Java

## What is Exception Propagation?

When a method throws an exception and does **not** handle it, the exception travels up the call stack to the previous caller — and continues until it is either caught or reaches the JVM.

```
Call Stack (top = most recent call)

  method3()   ← exception thrown here
  method2()   ← propagates up
  method1()   ← propagates up
  main()      ← propagates up
  JVM         ← program terminated if still uncaught
```

> **Rule:** The exception starts at the **top** of the stack and works **down** until caught.
> If it reaches the bottom (main/JVM) uncaught, the program terminates.

---

## Unchecked Exception Propagation (Automatic)

Unchecked exceptions (`RuntimeException` and subclasses) propagate **automatically** — no `throws` declaration needed.

### Example

```java
public class ExceptionPropagationUnchecked {

    public static void main(String[] args) {       // step 6 → propagates to JVM
        method1();
    }

    static void method1() {                        // step 5 → propagated automatically
        method2();
    }

    static void method2() {                        // step 4 → propagated automatically (was step 6 in original notes, corrected)
        method3();
    }

    static void method3() {                        // step 3 → exception thrown here
        int result = 10 / 0;                       // ArithmeticException (unchecked)
    }
}
```

### Step-by-Step Propagation

| Step | What Happens                                                               |
|------|----------------------------------------------------------------------------|
| 1    | JVM calls `main()`                                                         |
| 2    | `main()` calls `method1()`                                                 |
| 3    | `method1()` calls `method2()`                                              |
| 4    | `method2()` calls `method3()`                                              |
| 5    | `method3()` throws `ArithmeticException` — propagates **automatically** to `method2()` |
| 6    | `method2()` does not handle it — propagates **automatically** to `method1()` |
| 7    | `method1()` does not handle it — propagates **automatically** to `main()`  |
| 8    | `main()` does not handle it — propagates to JVM → **program terminated**   |

> Unchecked exceptions are **propagated automatically** — no `throws` keyword required at each method.

---

## Checked Exception Propagation (Manual — requires `throws`)

Checked exceptions (`Exception` subclasses that are NOT `RuntimeException`) do **not** propagate automatically.
Each method in the call chain must explicitly declare `throws` to pass the exception up.

### Example

```java
import java.io.IOException;

public class ExceptionPropagationChecked {

    public static void main(String[] args) throws IOException {   // must declare
        method1();
    }

    static void method1() throws IOException {                    // must declare
        method2();
    }

    static void method2() throws IOException {                    // must declare
        method3();
    }

    static void method3() throws IOException {                    // must declare
        throw new IOException("File not found");
    }
}
```

### Step-by-Step Propagation

| Step | What Happens                                                                       |
|------|------------------------------------------------------------------------------------|
| 1    | JVM calls `main()`                                                                 |
| 2    | `main()` calls `method1()`                                                         |
| 3    | `method1()` calls `method2()`                                                      |
| 4    | `method2()` calls `method3()`                                                      |
| 5    | `method3()` throws `IOException` — propagates to `method2()` via `throws` keyword |
| 6    | `method2()` propagates to `method1()` via `throws` keyword                        |
| 7    | `method1()` propagates to `main()` via `throws` keyword                           |
| 8    | `main()` propagates to JVM via `throws` keyword → **program terminated**           |

> Checked exceptions require **explicit `throws` declaration** at every method that does not handle them.

---

## Unchecked vs Checked Propagation — Comparison

| Aspect                         | Unchecked Exception             | Checked Exception                    |
|--------------------------------|---------------------------------|--------------------------------------|
| Propagation                    | Automatic                       | Manual — requires `throws`           |
| Compiler enforcement           | ❌ No                           | ✅ Yes                               |
| `throws` declaration needed?   | ❌ No                           | ✅ Yes at each unhandled method      |
| Examples                       | `ArithmeticException`, `NPE`    | `IOException`, `SQLException`        |

---

## Visual Call Stack

```
Unchecked                          Checked
──────────────────────────────     ──────────────────────────────────
method3()  throws (auto)           method3() throws IOException
    ↑                                  ↑
method2()  propagated (auto)       method2() throws IOException (declared)
    ↑                                  ↑
method1()  propagated (auto)       method1() throws IOException (declared)
    ↑                                  ↑
main()     propagated (auto)       main() throws IOException (declared)
    ↑                                  ↑
JVM → program terminates           JVM → program terminates
```
