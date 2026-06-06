package com.java17.interview.prepartion;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * 🚀 JAVA 17 FEATURES - LTS Release Features
 * 
 * Java 17 has several new features, including:
 * - Sealed classes[when introduced record ?]
 * - A new feature that allows for fine-grained extensibility. Classes can be declared as sealed using the modifier sealed, and the classes that can extend a sealed class can be declared using the modifier permits.
 * - Pattern matching for switch
 * - A preview feature that supports type pattern matching in switch statements. This can improve writing if else chains for type checking.
 * - Strongly Encapsulate JDK internals
 * - A feature that aims to improve the encapsulation of internal APIs in the JDK. This can limit the use of internal APIs by third-party applications and libraries, which can improve the security and stability of Java applications.
 * - New MacOS rendering pipeline
 * - A new rendering pipeline that provides better performance and integration with the MacOS ecosystem.
 * - Restore always-strict floating-point semantics
 * - A feature that restores always-strict floating-point semantics to ease development of numerically-sensitive libraries.
 * - Deprecate the security manager for removal
 * - A feature that marks the security manager as deprecated for removal. There is no planned replacement for it.
 * - Deserialization filters
 * - A feature that allows the monitoring and inspection of all deserialization operations performed by the Platform's Serialization API.
 * 
 * // Restore Always-Strict Floating-Point Semantics
 * //Enhanced pseudo-Random Number Generators
 * //New macOS Rendering pipelines
 * //macOS/AArch64 Port
 * //Deprecate the Applet API for Removal
 * //Strongly Encapsulated JDK Internals
 * //Pattern matching for Switch(Preview)
 * //Removal RMI Activation
 * //Sealed Classes
 * //Removal Experimental AOT and JIT Compiler
 * //Deprecate the Security manager for Removal
 * //Foreign Functions & memory API(Incubator)
 * //Vector API(Second Incubator)
 * //Context-Specific Deserialization Filters
 */
public class Java17Feature {
    
    // 1. Sealed Classes - Restrict inheritance
    public sealed class Shape permits Circle, Rectangle, Triangle {
        protected String name;
        
        public Shape(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    public final class Circle extends Shape {
        private double radius;
        
        public Circle(double radius) {
            super("Circle");
            this.radius = radius;
        }
        
        public double getArea() {
            return Math.PI * radius * radius;
        }
    }
    
    public final class Rectangle extends Shape {
        private double width, height;
        
        public Rectangle(double width, double height) {
            super("Rectangle");
            this.width = width;
            this.height = height;
        }
        
        public double getArea() {
            return width * height;
        }
    }
    
    public non-sealed class Triangle extends Shape {
        private double base, height;
        
        public Triangle(double base, double height) {
            super("Triangle");
            this.base = base;
            this.height = height;
        }
        
        public double getArea() {
            return 0.5 * base * height;
        }
    }
    
    // 2. Pattern Matching for Switch (Traditional approach for Java 17)
    public static String getShapeInfo(Shape shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return "Circle with area: " + c.getArea();
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return "Rectangle with area: " + r.getArea();
        } else if (shape instanceof Triangle) {
            Triangle t = (Triangle) shape;
            return "Triangle with area: " + t.getArea();
        }
        return "Unknown shape";
    }
    
    // 3. Enhanced Random Number Generators
    public static void demonstrateRandomGenerators() {
        System.out.println("3. Enhanced Random Number Generators:");
        
        // List available algorithms
        RandomGeneratorFactory.all()
            .map(RandomGeneratorFactory::name)
            .sorted()
            .forEach(name -> System.out.println("  Available: " + name));
        
        // Use different generators
        RandomGenerator defaultRng = RandomGenerator.getDefault();
        RandomGenerator xoroshiro = RandomGeneratorFactory.of("Xoroshiro128PlusPlus").create();
        
        System.out.println("Default RNG: " + defaultRng.nextInt(100));
        System.out.println("Xoroshiro128PlusPlus: " + xoroshiro.nextInt(100));
    }
    
    // 4. Text Blocks (Finalized in Java 15, but important for Java 17)
    public static void demonstrateTextBlocks() {
        System.out.println("4. Text Blocks:");
        
        String json = """
            {
                "name": "Java 17",
                "type": "LTS",
                "features": [
                    "Sealed Classes",
                    "Pattern Matching",
                    "Enhanced RNG"
                ]
            }
            """;
        
        System.out.println(json);
        
        String sql = """
            SELECT e.name, e.salary, d.department_name
            FROM employees e
            JOIN departments d ON e.dept_id = d.id
            WHERE e.salary > 50000
            ORDER BY e.salary DESC
            """;
        
        System.out.println("SQL Query:");
        System.out.println(sql);
    }
    
    // 5. Records (Finalized in Java 16, but important for Java 17)
    public record Person(String name, int age, String email) {
        // Compact constructor
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be null or blank");
            }
        }
        
        // Additional methods
        public boolean isAdult() {
            return age >= 18;
        }
    }
    
    // 6. Helpful NullPointerExceptions (JEP 358)
    public static void demonstrateHelpfulNPE() {
        System.out.println("6. Helpful NullPointerExceptions:");
        
        try {
            Person person = null;
            @SuppressWarnings({"unused", "null"})
            String name = person.name(); // This will give detailed NPE message
        } catch (NullPointerException e) {
            System.out.println("Helpful NPE: " + e.getMessage());
        }
    }
    
    // 7. Switch Expressions (Finalized in Java 14)
    public static String getDayType(String day) {
        return switch (day.toLowerCase()) {
            case "monday", "tuesday", "wednesday", "thursday", "friday" -> "Weekday";
            case "saturday", "sunday" -> "Weekend";
            default -> "Invalid day";
        };
    }
    
    // 8. instanceof Pattern Matching (JEP 394)
    public static void demonstrateInstanceofPatternMatching() {
        System.out.println("8. instanceof Pattern Matching:");
        
        Object obj = "Hello Java 17";
        
        // Old way
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println("Old way length: " + str.length());
        }
        
        // New way with pattern matching
        if (obj instanceof String str) {
            System.out.println("New way length: " + str.length());
        }
        
        // More complex example
        processObject("Java 17");
        processObject(42);
        processObject(3.14);
    }
    
    private static void processObject(Object obj) {
        String result;
        if (obj == null) {
            result = "Null object";
        } else if (obj instanceof String) {
            String s = (String) obj;
            result = "String of length " + s.length();
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            result = "Integer with value " + i;
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            result = "Double with value " + d;
        } else {
            result = "Unknown type: " + obj.getClass().getSimpleName();
        }
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        System.out.println("🚀 JAVA 17 FEATURES DEMONSTRATION");
        System.out.println("=" .repeat(60));
        
        Java17Feature demo = new Java17Feature();
        
        // 1. Sealed Classes
        System.out.println("1. Sealed Classes:");
        Shape circle = demo.new Circle(5.0);
        Shape rectangle = demo.new Rectangle(4.0, 6.0);
        Shape triangle = demo.new Triangle(3.0, 4.0);
        
        System.out.println(getShapeInfo(circle));
        System.out.println(getShapeInfo(rectangle));
        System.out.println(getShapeInfo(triangle));
        
        // 2. Records
        System.out.println("\n2. Records:");
        Person person1 = new Person("Alice", 25, "alice@email.com");
        Person person2 = new Person("Bob", 17, "bob@email.com");
        
        System.out.println(person1);
        System.out.println(person1.name() + " is adult: " + person1.isAdult());
        System.out.println(person2.name() + " is adult: " + person2.isAdult());
        
        // 3. Enhanced Random Generators
        demonstrateRandomGenerators();
        
        // 4. Text Blocks
        demonstrateTextBlocks();
        
        // 5. Switch Expressions
        System.out.println("\n5. Switch Expressions:");
        System.out.println("Monday is: " + getDayType("Monday"));
        System.out.println("Saturday is: " + getDayType("Saturday"));
        
        // 6. Helpful NPE
        demonstrateHelpfulNPE();
        
        // 7. instanceof Pattern Matching
        demonstrateInstanceofPatternMatching();
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What are sealed classes?");
        System.out.println("A1: Classes that restrict which classes can extend them using 'permits'");
        
        System.out.println("\nQ2: Benefits of records?");
        System.out.println("A2: Immutable data carriers with auto-generated methods");
        
        System.out.println("\nQ3: What's new in switch expressions?");
        System.out.println("A3: Arrow syntax, yield keyword, exhaustiveness checking");
        
        System.out.println("\nQ4: instanceof pattern matching benefit?");
        System.out.println("A4: Eliminates explicit casting after type check");
        
        System.out.println("\nQ5: Java 17 LTS significance?");
        System.out.println("A5: Long Term Support version with 8+ years of support");
    }
}
