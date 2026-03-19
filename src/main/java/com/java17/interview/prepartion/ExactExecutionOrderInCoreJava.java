package com.java17.interview.prepartion;

public class ExactExecutionOrderInCoreJava {
    public static void main(String[] args) {

        System.out.println("---- Program Started ----");
        Child obj = new Child();
        obj.synchronizedMethod();

        Parent parent = new Child();
        Parent.print("m");

    }
}
class Parent {

    // Static variable
    static String parentStaticVar = print("1. Parent static variable initialized");

    // Static block
    static {
        print("2. Parent static block executed");
    }

    // Instance variable
    String parentInstanceVar = print("5. Parent instance variable initialized");

    // Instance initializer block
    {
        print("6. Parent instance initializer block executed");
    }

    // Constructor
    Parent() {
        print("7. Parent constructor executed");
    }

    static String print(String message) {
        System.out.println(message);
        return message;
    }
}

class Child extends Parent {

    // Static variable
    static String childStaticVar = print("3. Child static variable initialized");

    // Static block
    static {
        print("4. Child static block executed");
    }

    // Instance variable
    String childInstanceVar = print("8. Child instance variable initialized");

    // Instance initializer block
    {
        print("9. Child instance initializer block executed");
    }

    // Constructor
    Child() {
        print("10. Child constructor executed");
    }

    // Synchronized method
    synchronized void synchronizedMethod() {
        System.out.println("11. Inside synchronized method of Child class");
    }


}
/***
 *
 * Now let’s decode what happens when you run it.
 *
 * Step 1: JVM loads Parent class first
 * Static variable → static block
 *
 * Step 2: JVM loads Child class
 * Static variable → static block
 *
 * Step 3: new Child() starts object creation
 *
 * Object creation always goes:
 *
 * Parent instance variable
 * Parent instance block
 * Parent constructor
 *
 * Then:
 *
 * Child instance variable
 * Child instance block
 * Child constructor
 *
 * Finally:
 *
 * Synchronized method executes (lock acquired on obj monitor).
 *
 * Important insight:
 * synchronized does NOT affect loading order. It just places a monitor lock on the object (or class for static synchronized methods). Initialization remains strictly deterministic.
 *
 * If you run the program, the output order will be:
 *
 * 1
 * 2
 * 3
 * 4
 * ---- Program Started ----
 * 5
 * 6
 * 7
 * 8
 * 9
 * 10
 * 11
 *
 * Now here’s something subtle and fascinating.
 *
 * If you never create new Child() and only access a static variable like:
 *
 * System.out.println(Child.childStaticVar);
 *
 * Only static initialization will run. Instance logic will never execute.
 *
 * That’s the difference between:
 * Class loading
 * vs
 * Object instantiation
 *
 * Two different phases. Two different universes.
 *
 * And here’s a deeper truth:
 * Java initialization order is designed to prevent partially constructed objects. That’s why parent constructor runs before child constructor. The language forces object integrity.
 *
 */