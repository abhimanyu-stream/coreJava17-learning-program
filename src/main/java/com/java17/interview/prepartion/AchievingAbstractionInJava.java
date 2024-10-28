package com.java17.interview.prepartion;

public class AchievingAbstractionInJava {


    public static void main(String[] args) {


        Shape myShape = new Circle();  // We use the subclass
        myShape.draw();               // Calls the overridden draw method
        myShape.info();               // Calls the concrete method in Shape




        Animalz myDog = new Dogz();  // Reference to interface type
        myDog.sound();             // Calls the implemented sound method
        myDog.sleep();             // Calls the default sleep method from the interface


    }


}
// Abstract class
abstract class Shape {
    // Its jobs are What a method will do

    // Abstract method (no implementation)
    abstract void draw();

    // Concrete method
    public void info() {
        System.out.println("This is a shape");
    }
}

// Subclass of Shape
class Circle extends Shape {

    // Its jobs are How a method do its task/how it has been implemented
    // Implementing the abstract method
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}
/**
 * In Java, abstraction is the concept of hiding the internal details of an object and only showing the essential features to the outside world. It allows a programmer to focus on what an object does rather than how it does it, which makes code more modular, maintainable, and easier to understand.
 *
 * Achieving Abstraction in Java
 * Abstraction can be achieved in Java in two main ways:
 *
 * Abstract Classes
 * Interfaces
 * Let's look at each one with examples.
 *
 * 1. Abstract Classes
 * An abstract class in Java is a class that cannot be instantiated directly. It can have abstract methods (methods without a body) as well as concrete methods (methods with a body). Subclasses of an abstract class must provide implementations for all abstract methods.
 *
 *
 * In this example:
 *
 * Shape is an abstract class that defines an abstract method draw().
 * Circle is a subclass of Shape and provides an implementation for the draw() method.
 * We achieve abstraction by defining the generic concept (draw method) in Shape and allowing Circle to provide specific functionality.
 *
 *
 *
 *
 * 2. Interfaces
 * An interface in Java is a reference type, similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types. Interfaces cannot have constructors, and all methods in an interface are abstract by default (except static and default methods). A class that implements an interface must implement all of its methods.
 *
 *
 * In this example:
 *
 * Animal is an interface with an abstract method sound() and a default method sleep().
 * The Dog class implements the Animal interface and provides an implementation for sound().
 * We achieve abstraction by defining what Animal does (sound and sleep behavior) without specifying how they are implemented.
 * Key Points on Abstraction
 * Abstract classes allow partial abstraction, as they can have both abstract and concrete methods.
 * Interfaces provide full abstraction (prior to Java 8) because all methods were abstract by default, but Java 8 added default and static methods to interfaces, allowing interfaces to provide some functionality.
 * Benefits of Abstraction
 * Improved Code Organization: Abstraction separates the implementation from the behavior, making it easier to understand and maintain.
 * Flexibility: Abstract classes and interfaces allow you to use polymorphism, which gives more flexibility in the code.
 * Security: By hiding details, abstraction prevents external code from accessing the internal complexity of an object directly.
 *
 * */


// Interface
interface Animalz {
    // Abstract method
    void sound();

    // Default method
    default void sleep() {
        System.out.println("The animal is sleeping");
    }
}

// Implementing the interface
class Dogz implements Animalz {
    // Implementing the abstract method
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

