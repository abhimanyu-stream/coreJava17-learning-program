package com.java17.interview.prepartion;

public class DiamondProblemSolvingApproach {

    public static void main(String[] args) {

         C c = new C();
         c.show();


    }


}
interface Aaa {
    default void show() {
        System.out.println("A's default method");
    }
}

interface Bbb {
    default void show() {
        System.out.println("B's default method");
    }
}

// Resolving Diamond Problem
class C implements Aaa, Bbb {
    @Override
    public void show() {
        Aaa.super.show();  // Explicitly choosing A's method
        Aaa.super.show();  // Calls Aaa's method
        Bbb.super.show();  // Calls Bbb's method
    }
}