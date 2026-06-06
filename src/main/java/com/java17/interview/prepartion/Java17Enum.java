package com.java17.interview.prepartion;

import java.util.*;

/**
 * 🏷️ JAVA 17 ENUM - Advanced Enum Features
 */
public class Java17Enum {
    
    // Basic Enum
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
        
        public boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }
    }
    
    // Enum with Constructor and Fields
    enum Planet {
        MERCURY(3.303e+23, 2.4397e6),
        VENUS(4.869e+24, 6.0518e6),
        EARTH(5.976e+24, 6.37814e6),
        MARS(6.421e+23, 3.3972e6);
        
        private final double mass;
        private final double radius;
        
        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
        }
        
        public double getMass() { return mass; }
        public double getRadius() { return radius; }
        
        public static final double G = 6.67300E-11;
        
        public double surfaceGravity() {
            return G * mass / (radius * radius);
        }
    }
    
    // Enum with Abstract Methods
    enum Operation {
        PLUS("+") {
            @Override
            public double apply(double x, double y) { return x + y; }
        },
        MINUS("-") {
            @Override
            public double apply(double x, double y) { return x - y; }
        },
        TIMES("*") {
            @Override
            public double apply(double x, double y) { return x * y; }
        },
        DIVIDE("/") {
            @Override
            public double apply(double x, double y) { 
                if (y == 0) throw new ArithmeticException("Division by zero");
                return x / y; 
            }
        };
        
        private final String symbol;
        
        Operation(String symbol) {
            this.symbol = symbol;
        }
        
        public abstract double apply(double x, double y);
        
        @Override
        public String toString() { return symbol; }
    }
    
    public static void main(String[] args) {
        System.out.println("🏷️ JAVA 17 ENUM DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        // Basic Enum
        System.out.println("1. Basic Enum:");
        for (Day day : Day.values()) {
            System.out.println(day + " - Weekend: " + day.isWeekend());
        }
        
        // Enum with Constructor
        System.out.println("\n2. Planet Enum:");
        for (Planet planet : Planet.values()) {
            System.out.printf("%s: Mass = %.2e kg, Gravity = %.2f m/s²%n",
                            planet, planet.getMass(), planet.surfaceGravity());
        }
        
        // Enum with Abstract Methods
        System.out.println("\n3. Operation Enum:");
        double x = 10, y = 3;
        for (Operation op : Operation.values()) {
            try {
                System.out.println(x + " " + op + " " + y + " = " + op.apply(x, y));
            } catch (Exception e) {
                System.out.println(x + " " + op + " " + y + " -> " + e.getMessage());
            }
        }
        
        // EnumSet and EnumMap
        System.out.println("\n4. Enum Collections:");
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("Weekdays: " + weekdays);
        
        EnumMap<Operation, String> opDescriptions = new EnumMap<>(Operation.class);
        opDescriptions.put(Operation.PLUS, "Addition");
        opDescriptions.put(Operation.MINUS, "Subtraction");
        System.out.println("Operation descriptions: " + opDescriptions);
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What is an enum?");
        System.out.println("A1: Special class representing a group of constants");
        
        System.out.println("\nQ2: Can enums have constructors?");
        System.out.println("A2: Yes, but they must be private");
        
        System.out.println("\nQ3: Can enums implement interfaces?");
        System.out.println("A3: Yes, but cannot extend classes");
        
        System.out.println("\nQ4: What are EnumSet and EnumMap?");
        System.out.println("A4: Specialized, efficient collections for enums");
    }
}