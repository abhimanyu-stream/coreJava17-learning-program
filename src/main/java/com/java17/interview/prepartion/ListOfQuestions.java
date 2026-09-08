package com.java17.interview.prepartion;

/**
 * Interview Preparation — List of Commonly Asked Coding Questions
 *
 * <h2>Java Coding Questions (Most Frequent)</h2>
 * <pre>
 * Category              Problem
 * ─────────────────────────────────────────────────────────────────────
 * Arrays                Find second-largest number
 * Arrays                Find duplicate elements
 * Arrays                Missing number in array
 * Arrays                Two Sum — find indexes
 * Arrays                Merge two sorted arrays
 * Strings               Reverse a string / reverse words
 * Strings               First non-repeated character
 * Strings               Anagram check
 * Strings               Palindrome check
 * Strings               Frequency of characters
 * Stream API            Sort employees by salary
 * Stream API            Group employees by department
 * Stream API            Find highest salary per department
 * Stream API            Flatten nested collections using flatMap
 * Concurrency           Producer-Consumer pattern
 * Concurrency           Thread-safe counter
 * Async                 CompletableFuture payment processing
 * Design                LRU cache
 * Design                Rate limiter
 * REST                  Implement a simple REST API endpoint
 * </pre>
 *
 * <h2>Java + Python Comparison Questions</h2>
 * <p>Useful if your profile contains both Java and Python experience.</p>
 * <pre>
 * Q: How would you implement asynchronous processing in Java and Python?
 *
 * Java:
 *   CompletableFuture
 *       .supplyAsync(() -> paymentService.processPayment())
 *       .thenApply(result -> validate(result))
 *       .exceptionally(ex -> handleError(ex));
 *
 * Python:
 *   async def process_payment():
 *       result = await payment_service.process_payment()
 *       return validate(result)
 *
 * Q: Java CompletableFuture vs Python asyncio?
 * Q: Java ExecutorService vs Python ThreadPoolExecutor?
 * Q: Java Spring Boot vs Python FastAPI?
 * Q: Java Kafka consumer vs Python Kafka consumer?
 * </pre>
 *
 * <h2>Python Core Topics (for Full-Stack / Polyglot Roles)</h2>
 * <pre>
 * Topic                   Notes
 * ──────────────────────────────────────────────────────────────
 * List / Tuple / Set / Dict   Core data structures
 * Mutable vs Immutable        list is mutable; tuple is immutable
 * *args / **kwargs            Variable positional and keyword arguments
 * Decorators                  @functools.wraps, AOP-like pattern
 * Generators                  yield — lazy evaluation
 * Iterators                   __iter__ / __next__ protocol
 * Context Managers            with statement / __enter__ / __exit__
 * Exception Handling          try / except / finally / raise
 * Async / Await               asyncio event loop
 * Threading vs Multiprocessing GIL impacts threading; multiprocessing bypasses GIL
 * GIL                         Global Interpreter Lock — limits CPU parallelism
 * </pre>
 *
 * <h2>Python Backend (FastAPI / SQLAlchemy)</h2>
 * <pre>
 * FastAPI, Pydantic, Dependency Injection, Middleware, JWT Auth,
 * Async endpoints, asyncio, SQLAlchemy, PostgreSQL/MySQL,
 * Redis, Kafka, REST API, Background tasks
 * </pre>
 *
 * <p>See individual Java files in this package for implementations of all the above patterns.</p>
 */
public class ListOfQuestions {
    // Reference class — implementations spread across individual files in this package
}
