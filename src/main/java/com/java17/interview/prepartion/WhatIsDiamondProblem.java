package com.java17.interview.prepartion;

public class WhatIsDiamondProblem {

    public static void main(String[] args) {

        SubClass s = new SubClass();
        s.display();

    }
}

class SuperClass{

    void display(){
        System.out.println("SuperClass methodA");

    }
}

interface InterfaceA {
    default void display() {
        System.out.println("InterfaceA Display");
    }
}

interface InterfaceB {
    default void display() {
        System.out.println("InterfaceB Display");
    }
}


class SubClass extends SuperClass implements InterfaceA,InterfaceB{

    public SubClass(){
        super();
        System.out.println("SubClass constructor");
        super.display();
        //super();Call to 'super()' must be first statement in constructor body

    }


    @Override
    public void display() {
        System.out.println("SubClass display");
        super.display();
        InterfaceA.super.display();
        InterfaceB.super.display();
    }
}