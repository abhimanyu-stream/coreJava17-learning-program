package com.java17.interview.prepartion;

/**
 * Types of Polymorphism
 * Compile-Time Polymorphism: This type of polymorphism is resolved at compile time and involves method overloading.
 * In method overloading, multiple methods with the same name but different parameter lists exist in the same class.
 * Runtime Polymorphism: Also known as dynamic polymorphism, runtime polymorphism is resolved at runtime.
 * It involves method overriding, where a subclass provides a specific implementation of a method defined in its superclass.
 *
 */

public class RunTimeOrDynamicPolymorphism {
    public static void main(String[] args) {
        Shapez s1 = new Circlez();
        Shapez s2 = new Squarez();

        s1.draw(); // Calls draw() of Circle class
        s2.draw(); // Calls draw() of Square class
    }
}
class Shapez {
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circlez extends Shapez {
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}

class Squarez extends Shapez {
    @Override
    void draw() {
        System.out.println("Drawing a square");
    }
}

