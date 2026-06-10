package com.java17.interview.prepartion;

public class CompileTimePolymorphism_MethodOverloading {


    //Method overloading is compile-time polymorphism.
    
     public static void main(String[] args) {
        Calculator c = new Calculator();

        System.out.println(c.add(2, 3));
        System.out.println(c.add(2.5, 3.5));
    }
    
}
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}

