package com.java17.interview.prepartion;

/**
 * 🔄 JAVA 8 SWITCH CASE - Traditional Switch Statement
 * 
 * Java 8 introduced enhanced switch statements with string support
 * and improved performance optimizations.
 * 
 * Features:
 * - String literals in switch
 * - Enum support
 * - Multiple case labels
 * - Fall-through behavior
 */
public class Java8SwitchCase {
    
    // Enum for demonstration
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }
    
    /**
     * Traditional switch with integers
     */
    public static String getNumberName(int number) {
        String result;
        switch (number) {
            case 1:
                result = "One";
                break;
            case 2:
                result = "Two";
                break;
            case 3:
                result = "Three";
                break;
            case 4:
                result = "Four";
                break;
            case 5:
                result = "Five";
                break;
            default:
                result = "Unknown";
                break;
        }
        return result;
    }
    
    /**
     * Switch with String literals (Java 7+)
     */
    public static String getLanguageType(String language) {
        String type;
        switch (language.toLowerCase()) {
            case "java":
            case "kotlin":
            case "scala":
                type = "JVM Language";
                break;
            case "javascript":
            case "typescript":
                type = "Web Language";
                break;
            case "python":
            case "ruby":
                type = "Scripting Language";
                break;
            case "c":
            case "c++":
                type = "System Language";
                break;
            default:
                type = "Unknown Language";
                break;
        }
        return type;
    }
    
    /**
     * Switch with Enum
     */
    public static String getDayType(Day day) {
        String dayType;
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                dayType = "Weekday";
                break;
            case SATURDAY:
            case SUNDAY:
                dayType = "Weekend";
                break;
            default:
                dayType = "Invalid Day";
                break;
        }
        return dayType;
    }
    
    /**
     * Calculator operations using switch
     */
    public static double calculate(double a, double b, char operator) {
        double result;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 JAVA 8 SWITCH CASE DEMONSTRATION");
        System.out.println("=".repeat(60));
        
        // 1. Integer Switch
        System.out.println("1. Integer Switch:");
        for (int i = 1; i <= 6; i++) {
            System.out.println(i + " -> " + getNumberName(i));
        }
        
        // 2. String Switch
        System.out.println("\n2. String Switch:");
        String[] languages = {"Java", "Python", "JavaScript", "C++", "Go"};
        for (String lang : languages) {
            System.out.println(lang + " -> " + getLanguageType(lang));
        }
        
        // 3. Enum Switch
        System.out.println("\n3. Enum Switch:");
        for (Day day : Day.values()) {
            System.out.println(day + " -> " + getDayType(day));
        }
        
        // 4. Calculator
        System.out.println("\n4. Calculator Operations:");
        double a = 10, b = 3;
        char[] operators = {'+', '-', '*', '/'};
        for (char op : operators) {
            try {
                double result = calculate(a, b, op);
                System.out.println(a + " " + op + " " + b + " = " + result);
            } catch (Exception e) {
                System.out.println(a + " " + op + " " + b + " -> " + e.getMessage());
            }
        }
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What's new in Java 8 switch statement?");
        System.out.println("A1: String literals support (actually Java 7+), performance optimizations");
        
        System.out.println("\nQ2: Can we use null in switch?");
        System.out.println("A2: No, switch throws NullPointerException for null values");
        
        System.out.println("\nQ3: What happens if we forget break statement?");
        System.out.println("A3: Fall-through behavior - execution continues to next case");
        
        System.out.println("\nQ4: What types can be used in switch?");
        System.out.println("A4: byte, short, int, char, String, enum, and their wrapper classes");
    }
}