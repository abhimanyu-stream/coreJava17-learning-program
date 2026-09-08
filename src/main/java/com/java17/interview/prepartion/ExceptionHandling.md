# Java Exception Handling — SecurityException

## SecurityException

`SecurityException` is an **unchecked** (runtime) exception in Java.

```
Throwable
  └── Exception
        └── RuntimeException        ← unchecked
              └── SecurityException ← unchecked
```

### Key Facts

| Property              | Value                                      |
|-----------------------|--------------------------------------------|
| Package               | `java.lang`                                |
| Parent class          | `RuntimeException`                         |
| Checked / Unchecked   | **Unchecked** (runtime)                    |
| Must be declared?     | ❌ No `throws` clause required             |
| Must be caught?       | ❌ Not mandatory                           |

### When it Occurs

Thrown when the JVM's security manager detects a **security policy violation**, such as:

- Accessing a restricted resource (file, network, class)
- Attempting a prohibited reflection operation
- Violating a sandbox policy

### Example

```java
// SecurityException thrown as an unchecked exception
// — no try-catch required, but allowed
abstract class ITSecurity {
    public abstract void itsec();
}

class CyberSecurity extends ITSecurity {
    @Override
    public void itsec() {
        System.out.println("Cyber Security not enabled");
        throw new SecurityException("Access denied");  // unchecked — no 'throws' declaration needed
    }
}
```

### Why it is Unchecked

The compiler does **not** require you to:
- Declare it with `throws SecurityException`
- Wrap the call in a `try-catch`

This is consistent with other `RuntimeException` subclasses — they signal programming or policy errors,
not recoverable I/O or external conditions.

### Handling it (when needed)

```java
try {
    cyberSecurity.itsec();
} catch (SecurityException e) {
    System.out.println("Security violation: " + e.getMessage());
}
```

---

## Checked vs Unchecked — Quick Reminder

| Type      | Parent Class       | Compiler enforces? | Example                     |
|-----------|--------------------|--------------------|-----------------------------|
| Checked   | `Exception`        | ✅ Yes             | `IOException`, `SQLException` |
| Unchecked | `RuntimeException` | ❌ No              | `SecurityException`, `NullPointerException` |
| Error     | `Error`            | ❌ No              | `OutOfMemoryError`, `StackOverflowError` |
