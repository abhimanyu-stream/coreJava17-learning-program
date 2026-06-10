package com.java17.interview.prepartion;

public class Inheritance_Is_A {

     public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // inherited
        d.bark();
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


class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}

