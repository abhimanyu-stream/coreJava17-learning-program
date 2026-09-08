# Checked Exception Propagation — Step-by-Step

> See also: `ExceptionpPropagationCheckedExceptionHandling.java` for the full working code.
> The diagram `img_1.png` shows the call stack visually.

---

## How Checked Exceptions Propagate

Checked exceptions do **not** propagate automatically.
Every method in the call chain that does not handle the exception must explicitly declare it using `throws`.

---

## Call Stack Flow

```
JVM
 └── main()         calls method1()    → declares throws
       └── method1()  calls method2()  → declares throws
             └── method2()  calls method3() → declares throws
                   └── method3()           → THROWS checked exception
```

---

## Step-by-Step Walkthrough

| Step | Action                                                                                   |
|------|------------------------------------------------------------------------------------------|
| 1    | JVM calls `main()`                                                                       |
| 2    | `main()` calls `method1()`                                                               |
| 3    | `method1()` calls `method2()`                                                            |
| 4    | `method2()` calls `method3()`                                                            |
| 5    | `method3()` throws a checked exception → propagates to `method2()` via `throws` keyword |
| 6    | `method2()` propagates it to `method1()` via `throws` keyword                           |
| 7    | `method1()` propagates it to `main()` via `throws` keyword                              |
| 8    | `main()` propagates it to JVM via `throws` keyword → **program terminated**             |

> **Key rule:** Checked exceptions are **not propagated automatically**.
> Each method must explicitly declare `throws` to pass the exception up the stack.

---

## Code Example

```java
import java.io.IOException;

public class CheckedExceptionPropagation {

    public static void main(String[] args) throws IOException {  // step 8
        method1();
    }

    static void method1() throws IOException {                   // step 7
        method2();
    }

    static void method2() throws IOException {                   // step 6
        method3();
    }

    static void method3() throws IOException {                   // step 5 — thrown here
        throw new IOException("Checked exception thrown in method3");
    }
}
```

---

## Unchecked vs Checked Propagation

| Aspect                    | Unchecked (RuntimeException)   | Checked (Exception)                     |
|---------------------------|--------------------------------|-----------------------------------------|
| Propagates automatically? | ✅ Yes                         | ❌ No — requires `throws` at each level |
| Compiler enforces?        | ❌ No                          | ✅ Yes                                  |
| Examples                  | `NullPointerException`         | `IOException`, `SQLException`           |

---

## Visual Call Stack Comparison

```
Unchecked — auto propagation        Checked — manual propagation
────────────────────────────        ─────────────────────────────────
method3() throws (auto)             method3() throws IOException
    ↑ automatic                         ↑ declared
method2() auto-propagated           method2() throws IOException
    ↑ automatic                         ↑ declared
method1() auto-propagated           method1() throws IOException
    ↑ automatic                         ↑ declared
main()    auto-propagated           main() throws IOException
    ↑                                   ↑
JVM → program terminated            JVM → program terminated
```
