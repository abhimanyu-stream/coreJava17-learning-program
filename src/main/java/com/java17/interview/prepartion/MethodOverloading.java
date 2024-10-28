package com.java17.interview.prepartion;

public class MethodOverloading {

    public static void main(String[] args) {
        MathOperations math = new MathOperations();

        // Calling overloaded methods
        System.out.println("Sum of two integers: " + math.add(5, 10));         // Calls add(int, int)
        System.out.println("Sum of three integers: " + math.add(5, 10, 15));   // Calls add(int, int, int)
        System.out.println("Sum of two doubles: " + math.add(5.5, 10.5));      // Calls add(double, double)

    }
}
/**
 * In Java, method overloading and method overriding are two important concepts that allow polymorphism, which is the ability of a method to take on many forms. Let’s look at examples of both.

 1. Method Overloading
 Method overloading happens when two or more methods in the same class have the same name but different parameter lists (different number, types, or order of parameters).
 */
class MathOperations {
    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method to add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method to add two double values
    public double add(double a, double b) {
        return a + b;
    }

}
