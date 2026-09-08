package com.java17.interview.prepartion;

/**
 * Functional Interface with Default Methods
 *
 * <p>A {@code @FunctionalInterface} can have:</p>
 * <ul>
 *   <li>Exactly ONE abstract method  (the SAM — Single Abstract Method)</li>
 *   <li>Any number of {@code default} methods</li>
 *   <li>Any number of {@code static}  methods</li>
 * </ul>
 *
 * <h2>Why Default Methods in Interfaces? (Java 8+)</h2>
 *
 * <pre>
 * 1. Backward Compatibility
 *    Before Java 8, adding a new method to an interface forced ALL implementing
 *    classes to update. Default methods let you add new behaviour to an interface
 *    without breaking existing code.
 *    Example: List.forEach(), Collection.removeIf() added in Java 8
 *    without breaking millions of existing implementations.
 *
 * 2. Code Reusability
 *    Common helper/utility behaviour lives in the interface.
 *    Implementing classes inherit it for free and override only when needed.
 *
 * 3. Enhanced Functional Interfaces
 *    Comparator is @FunctionalInterface with one abstract method compare(),
 *    yet ships many default methods: thenComparing(), reversed(), etc.
 *    These allow method chaining without violating the functional nature.
 *
 * 4. Multiple Inheritance of Behaviour (limited)
 *    A class can implement multiple interfaces and inherit default behaviour
 *    from each. Conflict (same signature in two interfaces) must be resolved
 *    explicitly in the implementing class.
 * </pre>
 *
 * <h2>Rules</h2>
 * <pre>
 * ✅ One abstract method     → still a valid @FunctionalInterface
 * ✅ Many default methods    → allowed
 * ✅ Many static methods     → allowed
 * ✅ Override default method → allowed in implementing class
 * ❌ Two abstract methods    → NOT a functional interface
 * </pre>
 */
public class FunctionalInterfaceWithDefaultMethod {

    public static void main(String[] args) {

        // ─── 1. Implementing class overrides the default method ───────────────
        Greeting overridingGreeter = new OverridingGreeter();
        overridingGreeter.sayHello("Alice");    // Hello, Alice       (abstract — must implement)
        overridingGreeter.sayGoodbye("Alice");  // Goodbye, overridden Alice (default overridden)

        System.out.println();

        // ─── 2. Implementing class does NOT override the default method ───────
        // Uses the default implementation from the interface as-is.
        Greeting defaultGreeter = new DefaultGreeter();
        defaultGreeter.sayHello("Bob");         // Hello, Bob         (abstract — implemented)
        defaultGreeter.sayGoodbye("Bob");       // Goodbye, Bob       (default from interface)

        System.out.println();

        // ─── 3. Lambda — SAM only, default methods available on the interface ─
        // Lambda provides the abstract method body.
        // Default methods on the interface are still callable via the reference.
        Greeting lambdaGreeter = name -> System.out.println("Hey, " + name + "!");
        lambdaGreeter.sayHello("Carol");        // Hey, Carol!        (lambda body)
        lambdaGreeter.sayGoodbye("Carol");      // Goodbye, Carol     (default from interface)

        System.out.println();

        // ─── 4. Static method on the interface ────────────────────────────────
        // Static methods belong to the interface itself — NOT inherited by implementations.
        Greeting.printInfo();
    }
}

// ─── Functional Interface ─────────────────────────────────────────────────────
// @FunctionalInterface enforces the "exactly one abstract method" rule at compile time.
// Adding a second abstract method here would cause a compile error.
@FunctionalInterface
interface Greeting {

    // SAM — the one abstract method; must be implemented by any class or lambda
    void sayHello(String name);

    // Default method — shared behaviour; can be overridden or used as-is
    default void sayGoodbye(String name) {
        System.out.println("Goodbye, " + name);
    }

    // Static method — utility; called as Greeting.printInfo(), NOT via instances
    static void printInfo() {
        System.out.println("Greeting is a @FunctionalInterface with one abstract method: sayHello()");
    }
}

// ─── Overrides the default method ────────────────────────────────────────────
class OverridingGreeter implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);              // satisfies SAM
    }

    @Override
    public void sayGoodbye(String name) {
        System.out.println("Goodbye, overridden " + name); // custom goodbye
    }
}

// ─── Uses the default method as-is ───────────────────────────────────────────
class DefaultGreeter implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);              // satisfies SAM
    }
    // sayGoodbye() NOT overridden → uses Greeting.sayGoodbye() default
}
