package com.java17.interview.prepartion;

public class MethodOverriding {
    public static void main(String[] args) {
        Animalt myAnimal = new Animalt();  // Animal reference, Animal object
        myAnimal.sound();                // Calls Animal's sound() method

        Animalt myDog = new Dogt();        // Animal reference, Dog object
        myDog.sound();                   // Calls Dog's overridden sound() method

    }
}
/**
 * Method Overriding
 * Method overriding occurs when a subclass provides a specific implementation of a method already defined in its superclass. The method in the subclass must have the same name, return type, and parameters as the method in the superclass.
 *
 * */
// Superclass
class Animalt {
    // Method to be overridden
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Subclass
class Dogt extends Animalt {
    // Overriding the sound() method
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
