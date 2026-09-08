# Java Exception Hierarchy — Checked & Unchecked

> The diagrams `img_3.png` through `img_9.png` in this folder show the visual hierarchy.
> This document explains it in text form for quick reference and interview revision.

---

## Complete Exception Hierarchy

```
java.lang.Throwable
    │
    ├── java.lang.Error                        (unchecked — serious JVM errors)
    │     ├── OutOfMemoryError
    │     ├── StackOverflowError
    │     ├── ExceptionInInitializerError
    │     ├── NoClassDefFoundError
    │     └── VirtualMachineError
    │
    └── java.lang.Exception
          │
          ├── RuntimeException                 (unchecked — programming errors)
          │     ├── NullPointerException
          │     ├── ArrayIndexOutOfBoundsException
          │     ├── StringIndexOutOfBoundsException
          │     ├── IndexOutOfBoundsException
          │     ├── ClassCastException
          │     ├── IllegalArgumentException
          │     ├── NumberFormatException      (extends IllegalArgumentException)
          │     ├── IllegalStateException
          │     ├── ArithmeticException
          │     ├── UnsupportedOperationException
          │     ├── ConcurrentModificationException
          │     └── SecurityException
          │
          └── (Checked Exceptions)             (checked — must handle or declare)
                ├── IOException
                │     └── FileNotFoundException
                │     └── EOFException
                ├── SQLException
                ├── ClassNotFoundException
                ├── InterruptedException
                ├── CloneNotSupportedException
                └── InvalidClassException
```

---

## Checked Exceptions — Parent and Subclasses

| Parent Class    | Subclasses                                             | When Thrown                                             |
|-----------------|--------------------------------------------------------|---------------------------------------------------------|
| `IOException`   | `FileNotFoundException`, `EOFException`                | File/stream operations fail                             |
| `Exception`     | `SQLException`, `ClassNotFoundException`               | DB errors, missing class at runtime                     |
| `Exception`     | `InterruptedException`                                 | Thread interrupted while waiting/sleeping               |
| `Exception`     | `InvalidClassException`                                | Serialization version mismatch (`serialVersionUID`)     |

### Key rule for Checked Exceptions

The compiler **requires** you to either:
- Handle with `try-catch`, **or**
- Declare with `throws` in the method signature

```java
// Must handle or declare — IOException is checked
public void readFile(String path) throws IOException {
    FileReader reader = new FileReader(path);   // throws FileNotFoundException (checked)
}
```

---

## Unchecked Exceptions (RuntimeException subclasses)

| Exception                          | Common Cause                                              |
|------------------------------------|-----------------------------------------------------------|
| `NullPointerException`             | Calling method on a `null` reference                      |
| `ArrayIndexOutOfBoundsException`   | Accessing array index outside 0..length-1                 |
| `StringIndexOutOfBoundsException`  | Accessing char index outside string bounds                |
| `ClassCastException`               | Invalid type cast at runtime                              |
| `NumberFormatException`            | Parsing invalid string as number (`Integer.parseInt("abc")`) |
| `ArithmeticException`              | Divide by zero (`10 / 0`)                                 |
| `IllegalArgumentException`         | Invalid argument passed to method                         |
| `IllegalStateException`            | Method called at wrong time / object in wrong state       |
| `UnsupportedOperationException`    | Operation not supported (e.g., `List.of().add(...)`)      |
| `ConcurrentModificationException`  | Collection modified while iterating                       |
| `SecurityException`                | Security policy violation                                 |

---

## Errors (NOT meant to be caught)

| Error                          | Cause                                                   |
|--------------------------------|---------------------------------------------------------|
| `OutOfMemoryError`             | JVM heap exhausted                                      |
| `StackOverflowError`           | Infinite recursion exhausts the call stack              |
| `ExceptionInInitializerError`  | Static initializer block throws an exception            |
| `NoClassDefFoundError`         | Class was present at compile time but missing at runtime |

> Errors indicate **JVM-level failures** — generally unrecoverable and should not be caught in application code.

---

## Checked vs Unchecked — Decision Rule

```
Is it a RuntimeException or subclass?
    YES → Unchecked — compiler does NOT enforce handling
    NO  → Checked   — compiler ENFORCES handling or declaration

Is it an Error?
    YES → Unchecked — do not catch in normal application code
```

---

## Interview Quick Points

| Question                                    | Answer                                                              |
|---------------------------------------------|---------------------------------------------------------------------|
| What is the root of all exceptions?         | `java.lang.Throwable`                                               |
| What is the root of all checked exceptions? | `java.lang.Exception` (excluding `RuntimeException` subtree)       |
| What is the root of unchecked exceptions?   | `java.lang.RuntimeException`                                        |
| Can you catch `Error`?                      | Technically yes, but you should not — JVM state is unrecoverable   |
| Is `NullPointerException` checked?          | ❌ No — it is unchecked (`RuntimeException` subclass)               |
| Is `IOException` checked?                  | ✅ Yes                                                              |
| Is `InterruptedException` checked?         | ✅ Yes                                                              |
