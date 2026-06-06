package com.java17.interview.prepartion;

public class MethodOverriding {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();  // Animal reference, Animal object
        myAnimal.sound();                // Calls Animal's sound() method

        Animal myDog = new Dog();        // Animal reference, Dog object
        myDog.sound();                   // Calls Dog's overridden sound() method

    }
}
/**
 * Method Overriding
 * Method overriding occurs when a subclass provides a specific implementation of a method already defined in its superclass. The method in the subclass must have the same name, return type, and parameters as the method in the superclass.
 *
 * */
// Superclass
class Animal {
    // Method to be overridden
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Subclass
class Dog extends Animal {
    // Overriding the sound() method
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
