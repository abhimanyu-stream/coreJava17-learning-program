package com.java17.interview.prepartion;

/**
 * Most Frequently Occurring Exceptions and Errors in Java
 *
 * <h2>Checked Exceptions (Compile-Time)</h2>
 * <p>Must be handled with try-catch or declared with throws.</p>
 * <pre>
 * Exception                    When it Occurs
 * ─────────────────────────────────────────────────────────────────────────
 * IOException                  General I/O failure (file, stream, socket)
 * FileNotFoundException        File does not exist at specified path
 * EOFException                 Unexpected end of input stream while reading
 * SQLException                 Database access error or SQL syntax issue
 * InterruptedException         Thread interrupted while sleeping/waiting
 * ClassNotFoundException       Class not found on classpath at runtime
 * InvalidClassException        Serialization UID mismatch during deserialization
 * </pre>
 *
 * <h2>Unchecked Exceptions (Runtime)</h2>
 * <p>Subclasses of RuntimeException — compiler does NOT enforce handling.</p>
 * <pre>
 * Exception                         When it Occurs
 * ─────────────────────────────────────────────────────────────────────────
 * NullPointerException              Accessing method/field on a null reference
 * NumberFormatException             Parsing invalid string: Integer.parseInt("abc")
 * IndexOutOfBoundsException         Generic out-of-bounds access
 * ArrayIndexOutOfBoundsException    Array access with index < 0 or >= length
 * StringIndexOutOfBoundsException   String charAt() with invalid index
 * ArithmeticException               Integer divide by zero: 10 / 0
 * ClassCastException                Invalid cast: (String) new Integer(1)
 * IllegalArgumentException          Invalid argument passed to a method
 * IllegalStateException             Method called at wrong state/order
 * UnsupportedOperationException     Operation not supported (e.g., add on immutable list)
 * ConcurrentModificationException   Collection modified while iterating with iterator
 * SecurityException                 Security policy violation
 * </pre>
 *
 * <h2>Errors (JVM-Level — do not catch in application code)</h2>
 * <pre>
 * Error                          When it Occurs
 * ─────────────────────────────────────────────────────────────────────────
 * OutOfMemoryError               JVM heap is exhausted
 * StackOverflowError             Infinite or very deep recursion
 * ExceptionInInitializerError    Static initializer block throws an exception
 * NoClassDefFoundError           Class present at compile time, missing at runtime
 * </pre>
 *
 * <h2>Quick Fix Guide</h2>
 * <pre>
 * NullPointerException      → null check, Optional.ofNullable(), @NotNull
 * NumberFormatException     → validate input before parsing
 * ArrayIndexOutOfBounds     → check index < array.length
 * ConcurrentModification    → use Iterator.remove() or CopyOnWriteArrayList
 * ClassCastException        → use instanceof before casting
 * StackOverflowError        → check for missing recursion base case
 * OutOfMemoryError          → profile heap, reduce object retention, increase -Xmx
 * InvalidClassException     → define serialVersionUID explicitly
 * </pre>
 */
public class MostFrequentlyOccurringExceptionAndErrorsInJava {
    // Documentation class — see individual exception files in this package
    // and ExceptionHirarachyCheckedExceptionParentAndSubClass.md for the full hierarchy
}
