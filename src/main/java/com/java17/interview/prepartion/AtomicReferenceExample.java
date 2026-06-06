package com.java17.interview.prepartion;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {
    //AtomicReference<V> — Atomic Object Reference
    //
    //Used to safely swap or update shared object references.

    static class User {
        String name;
        User(String name) { this.name = name; }
        public String toString() { return name; }
    }

    public static void main(String[] args) {
        AtomicReference<User> userRef = new AtomicReference<>(new User("Alice"));

        User oldUser = userRef.get();
        User newUser = new User("Bob");

        // Update only if oldUser is still the current value
        if (userRef.compareAndSet(oldUser, newUser)) {
            System.out.println("User updated to " + userRef.get());
        } else {
            System.out.println("Update failed, someone changed it first!");
        }
    }
}
//Used for lock-free state transitions (e.g., replacing objects atomically).
/**
 * AtomicReference Example
 *
 * Used when you want atomicity over object references.
 *
 * AtomicReference<String> ref = new AtomicReference<>("A");
 *
 * ref.compareAndSet("A", "B"); // updates only if current value is "A"
 * System.out.println(ref.get()); // prints "B"
 *
 *
 * This is great for immutable objects or state machines.
 */