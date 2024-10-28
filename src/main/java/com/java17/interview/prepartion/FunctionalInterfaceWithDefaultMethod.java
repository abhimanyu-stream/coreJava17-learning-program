package com.java17.interview.prepartion;

public class FunctionalInterfaceWithDefaultMethod {

    public static void main(String[] args) {
        Greeting greeter = new Greeter();
        greeter.sayHello("Alice");       // Prints "Hello, Alice"
        greeter.sayGoodbye("Alice");     // Prints "Goodbye, Alice" if not overridden in subclass
        //
        //Goodbye, overridden Alice, if  overridden in subclass
    }
}
@FunctionalInterface
interface Greeting {
    void sayHello(String name);

    // Default method
    default void sayGoodbye(String name) {
        System.out.println("Goodbye, " + name);
    }
}

class Greeter implements Greeting {
    public void sayHello(String name){
        System.out.println("Hello "+ name);
    }
    public void sayGoodbye(String name) {
        System.out.println("Goodbye, overridden " + name);
    }


}
/***
 * In Java 8, the introduction of default methods in interfaces was a significant enhancement, particularly for functional interfaces. Here are the key benefits of using default methods:

 1. Backward Compatibility
 Before Java 8, adding a new method to an existing interface required updating all classes that implemented the interface. Default methods allow new methods to be added to interfaces without forcing existing implementing classes to modify their code. The method can be provided directly within the interface.
 For example, the Java Collections API added new methods to interfaces like List and Collection (e.g., forEach, removeIf) using default methods without breaking existing implementations.
 2. Code Reusability and Flexibility
 Default methods allow common, reusable functionality to be provided at the interface level. Implementing classes can use the default method if they do not need custom behavior, reducing redundancy.
 This helps in defining utility functions or helper methods in an interface, which implementing classes can inherit or override as needed.
 3. Enhanced Functional Interfaces
 Functional interfaces (interfaces with a single abstract method) benefit significantly from default methods because they allow you to include multiple utility methods alongside the main abstract method without impacting the functional nature of the interface.
 For instance, the Comparator interface in Java 8 is a functional interface with a single abstract method compare(), but it includes multiple useful default methods like thenComparing() and reversed() that aid in chaining and modifying comparisons.
 4. Support for Multiple Inheritance of Behavior
 With default methods, Java supports a limited form of multiple inheritance, allowing a class to inherit behavior from multiple interfaces. However, it must resolve conflicts if two interfaces provide default methods with the same signature.
 This is especially useful in implementing mixin functionality, where a class can inherit behavior from various sources without needing to follow a strict class hierarchy.
 */