package com.java17.interview.prepartion;

public class CustomFunctionalInterface {
    public static void main(String[] args) {



        FindingSquareRoot findingSquareRoot = n -> Math.sqrt(n);

        double result = findingSquareRoot.findSquareRooot(25);

        System.out.println(result);

        findingSquareRoot.showMessage();

        FindingSquareRoot.print();

    }
}

@FunctionalInterface
interface  FindingSquareRoot{

    /**
     * A @FunctionalInterface can have:
     *
     * Exactly one abstract method
     * Any number of default methods
     * Any number of static methods
     * It can also contain methods from Object class (toString, equals, etc.)
     *
     * Your interface is valid.
     *
     */

    // Only ONE abstract method allowed
    double findSquareRooot(double d);

    // Any number of default methods
    default void showMessage() {
        System.out.println("Default Method 1");
    }

    default void display() {
        System.out.println("Default Method 2");
    }

    // Any number of static methods
    static void print() {
        System.out.println("Static Method 1");
    }

    static void test() {
        System.out.println("Static Method 2");
    }
}