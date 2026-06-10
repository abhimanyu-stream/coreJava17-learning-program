package com.java17.interview.prepartion;

public class Composition_Has_A {

    public static void main(String[] args) {
        Car car = new Car();
        car.startCar();
    }
    
}
/**
 * 
 * Inheritance vs Composition
Feature	              Inheritance	Composition
Relationship	        IS-A	     HAS-A
Example	          Dog IS-A Animal	Car HAS-A Engine
Coupling	          Tight	        Loose
Reusability	     Through extending	Through object combination
Preferred?	      Sometimes	       Often preferred
 */


/**
 * Why composition preferred?

Bad inheritance:

Bird
 └── Penguin
      └── fly() ???  (wrong)

Better:

Bird HAS-A FlyingBehavior
Penguin can use NoFlyBehavior

This is flexible.

Best interview one-liner

Inheritance models IS-A relationship, composition models HAS-A relationship. Composition is preferred because it gives loose coupling.


 */

class Engine {
    void start() {
        System.out.println("Engine starts");
    }
}

class Car {
    private Engine engine = new Engine();

    void startCar() {
        engine.start();
        System.out.println("Car started");
    }
}
