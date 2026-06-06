package com.java17.interview.prepartion;

/**
 * 🔄 JAVA METHOD OVERLOADING - Compile-time Polymorphism
 */
public class JavaMethodOverLoading {
    
    // Overloading by Number of Parameters
    public static int add(int a, int b) {
        System.out.println("add(int, int) called");
        return a + b;
    }
    
    public static int add(int a, int b, int c) {
        System.out.println("add(int, int, int) called");
        return a + b + c;
    }
    
    // Overloading by Type of Parameters
    public static double add(double a, double b) {
        System.out.println("add(double, double) called");
        return a + b;
    }
    
    public static String add(String a, String b) {
        System.out.println("add(String, String) called");
        return a + b;
    }
    
    // Overloading by Order of Parameters
    public static void display(int number, String text) {
        System.out.println("display(int, String): " + number + " - " + text);
    }
    
    public static void display(String text, int number) {
        System.out.println("display(String, int): " + text + " - " + number);
    }
    
    // Constructor Overloading
    static class Person {
        private String name;
        private int age;
        
        public Person() {
            this("Unknown", 0);
        }
        
        public Person(String name) {
            this(name, 0);
        }
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 JAVA METHOD OVERLOADING DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        // Number of Parameters
        System.out.println("1. Overloading by Number of Parameters:");
        System.out.println("Result: " + add(5, 3));
        System.out.println("Result: " + add(5, 3, 2));
        
        // Type of Parameters
        System.out.println("\n2. Overloading by Type of Parameters:");
        System.out.println("Result: " + add(5.5, 3.2));
        System.out.println("Result: " + add("Hello", "World"));
        
        // Order of Parameters
        System.out.println("\n3. Overloading by Order of Parameters:");
        display(42, "Answer");
        display("Question", 42);
        
        // Constructor Overloading
        System.out.println("\n4. Constructor Overloading:");
        Person p1 = new Person();
        Person p2 = new Person("Alice");
        Person p3 = new Person("Bob", 25);
        
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What is method overloading?");
        System.out.println("A1: Multiple methods with same name but different parameters");
        
        System.out.println("\nQ2: When is overloading resolved?");
        System.out.println("A2: At compile time (static binding)");
        
        System.out.println("\nQ3: Can return type differentiate overloaded methods?");
        System.out.println("A3: No, parameters must be different");
        
        System.out.println("\nQ4: Overloading vs Overriding?");
        System.out.println("A4: Overloading: Same class, different parameters");
        System.out.println("    Overriding: Inheritance, same signature");
    }
}