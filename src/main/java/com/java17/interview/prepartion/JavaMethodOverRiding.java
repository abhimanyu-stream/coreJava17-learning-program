package com.java17.interview.prepartion;

/**
 * 🔄 JAVA METHOD OVERRIDING - Runtime Polymorphism
 */
public class JavaMethodOverRiding {
    
    // Base class
    static class Animal {
        protected String name;
        
        public Animal(String name) {
            this.name = name;
        }
        
        public void makeSound() {
            System.out.println(name + " makes a generic animal sound");
        }
        
        public void eat() {
            System.out.println(name + " is eating");
        }
        
        public void sleep() {
            System.out.println(name + " is sleeping");
        }
        
        // Method that cannot be overridden
        public final void breathe() {
            System.out.println(name + " is breathing");
        }
        
        public String getInfo() {
            return "Animal: " + name;
        }
    }
    
    // Derived class - Dog
    static class Dog extends Animal {
        private String breed;
        
        public Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " barks: Woof! Woof!");
        }
        
        @Override
        public void eat() {
            System.out.println(name + " is eating dog food");
        }
        
        @Override
        public String getInfo() {
            return "Dog: " + name + " (" + breed + ")";
        }
        
        // Dog-specific method
        public void wagTail() {
            System.out.println(name + " is wagging tail");
        }
    }
    
    // Derived class - Cat
    static class Cat extends Animal {
        private boolean isIndoor;
        
        public Cat(String name, boolean isIndoor) {
            super(name);
            this.isIndoor = isIndoor;
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " meows: Meow! Meow!");
        }
        
        @Override
        public void eat() {
            System.out.println(name + " is eating cat food");
        }
        
        @Override
        public String getInfo() {
            return "Cat: " + name + " (Indoor: " + isIndoor + ")";
        }
        
        // Cat-specific method
        public void purr() {
            System.out.println(name + " is purring");
        }
    }
    
    // Abstract class example
    static abstract class Vehicle {
        protected String brand;
        protected int year;
        
        public Vehicle(String brand, int year) {
            this.brand = brand;
            this.year = year;
        }
        
        // Abstract method - must be overridden
        public abstract void start();
        public abstract void stop();
        
        // Concrete method - can be overridden
        public void displayInfo() {
            System.out.println(year + " " + brand);
        }
    }
    
    static class Car extends Vehicle {
        private int doors;
        
        public Car(String brand, int year, int doors) {
            super(brand, year);
            this.doors = doors;
        }
        
        @Override
        public void start() {
            System.out.println("Car engine started with key");
        }
        
        @Override
        public void stop() {
            System.out.println("Car engine stopped");
        }
        
        @Override
        public void displayInfo() {
            super.displayInfo(); // Call parent method
            System.out.println("Doors: " + doors);
        }
    }
    
    static class Motorcycle extends Vehicle {
        private boolean hasSidecar;
        
        public Motorcycle(String brand, int year, boolean hasSidecar) {
            super(brand, year);
            this.hasSidecar = hasSidecar;
        }
        
        @Override
        public void start() {
            System.out.println("Motorcycle started with kick/button");
        }
        
        @Override
        public void stop() {
            System.out.println("Motorcycle engine stopped");
        }
        
        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println("Has sidecar: " + hasSidecar);
        }
    }
    
    // Interface example
    interface Drawable {
        void draw();
        
        // Default method (Java 8+)
        default void display() {
            System.out.println("Displaying drawable object");
        }
    }
    
    static class Circle implements Drawable {
        private double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public void draw() {
            System.out.println("Drawing a circle with radius: " + radius);
        }
        
        @Override
        public void display() {
            System.out.println("Circle display - Area: " + (Math.PI * radius * radius));
        }
    }
    
    static class Rectangle implements Drawable {
        private double width, height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public void draw() {
            System.out.println("Drawing a rectangle: " + width + "x" + height);
        }
        
        // Uses default display method from interface
    }
    
    // Demonstration methods
    public static void demonstratePolymorphism() {
        System.out.println("\n🔄 Runtime Polymorphism Demonstration:");
        
        Animal[] animals = {
            new Dog("Buddy", "Golden Retriever"),
            new Cat("Whiskers", true),
            new Dog("Rex", "German Shepherd"),
            new Cat("Shadow", false)
        };
        
        for (Animal animal : animals) {
            System.out.println("\n" + animal.getInfo());
            animal.makeSound();
            animal.eat();
            animal.sleep();
            animal.breathe(); // Final method - same for all
            
            // Type checking and casting
            if (animal instanceof Dog) {
                ((Dog) animal).wagTail();
            } else if (animal instanceof Cat) {
                ((Cat) animal).purr();
            }
        }
    }
    
    public static void demonstrateAbstractClass() {
        System.out.println("\n🚗 Abstract Class Demonstration:");
        
        Vehicle[] vehicles = {
            new Car("Toyota", 2023, 4),
            new Motorcycle("Harley", 2022, false),
            new Car("BMW", 2024, 2)
        };
        
        for (Vehicle vehicle : vehicles) {
            System.out.println("\nVehicle Information:");
            vehicle.displayInfo();
            vehicle.start();
            vehicle.stop();
        }
    }
    
    public static void demonstrateInterface() {
        System.out.println("\n🎨 Interface Implementation:");
        
        Drawable[] shapes = {
            new Circle(5.0),
            new Rectangle(10.0, 6.0),
            new Circle(3.0)
        };
        
        for (Drawable shape : shapes) {
            shape.draw();
            shape.display();
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 JAVA METHOD OVERRIDING DEMONSTRATION");
        System.out.println("=".repeat(60));
        
        // Basic overriding example
        System.out.println("1. Basic Method Overriding:");
        Animal genericAnimal = new Animal("Generic");
        Dog myDog = new Dog("Buddy", "Labrador");
        Cat myCat = new Cat("Whiskers", true);
        
        genericAnimal.makeSound();
        myDog.makeSound();
        myCat.makeSound();
        
        // Polymorphism demonstration
        demonstratePolymorphism();
        
        // Abstract class demonstration
        demonstrateAbstractClass();
        
        // Interface demonstration
        demonstrateInterface();
        
        // Method resolution at runtime
        System.out.println("\n🎯 Runtime Method Resolution:");
        Animal animal1 = new Dog("Runtime Dog", "Beagle");
        Animal animal2 = new Cat("Runtime Cat", false);
        
        // Method called is determined at runtime based on actual object type
        animal1.makeSound(); // Calls Dog's makeSound()
        animal2.makeSound(); // Calls Cat's makeSound()
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What is method overriding?");
        System.out.println("A1: Redefining parent class method in child class with same signature");
        
        System.out.println("\nQ2: When is overriding resolved?");
        System.out.println("A2: At runtime (dynamic binding)");
        
        System.out.println("\nQ3: Rules for method overriding?");
        System.out.println("A3: 1) Same method signature");
        System.out.println("    2) IS-A relationship (inheritance)");
        System.out.println("    3) Cannot override final/static/private methods");
        System.out.println("    4) Access modifier cannot be more restrictive");
        
        System.out.println("\nQ4: @Override annotation purpose?");
        System.out.println("A4: Compile-time check to ensure method is actually overridden");
        
        System.out.println("\nQ5: Overriding vs Overloading?");
        System.out.println("A5: Overriding: Runtime, inheritance, same signature");
        System.out.println("    Overloading: Compile-time, same class, different parameters");
        
        System.out.println("\nQ6: Can constructors be overridden?");
        System.out.println("A6: No, constructors cannot be overridden (but can be overloaded)");
        
        System.out.println("\nQ7: What is dynamic method dispatch?");
        System.out.println("A7: JVM determines which method to call at runtime based on object type");
    }
}