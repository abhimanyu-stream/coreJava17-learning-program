package com.java17.interview.prepartion;

/**
 * 🚀 JAVA 12 SWITCH EXPRESSIONS - Preview Feature Introduction
 */
public class Java12SwitchCase {
    
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    // Traditional switch (still supported)
    public static String getDayTypeTraditional(Day day) {
        String result;
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                result = "Weekday";
                break;
            case SATURDAY:
            case SUNDAY:
                result = "Weekend";
                break;
            default:
                result = "Unknown";
                break;
        }
        return result;
    }
    
    // Java 12: Switch Expression with arrow syntax (Preview)
    public static String getDayTypeModern(Day day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
    }
    
    // Calculator with switch expressions
    public static double calculate(double a, double b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }
    
    public static void main(String[] args) {
        System.out.println("🚀 JAVA 12 SWITCH EXPRESSIONS DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        System.out.println("Note: Switch expressions were PREVIEW in Java 12");
        
        // Traditional vs Modern Switch
        Day day = Day.FRIDAY;
        System.out.println("Traditional: " + getDayTypeTraditional(day));
        System.out.println("Modern: " + getDayTypeModern(day));
        
        // Calculator
        System.out.println("\nCalculator:");
        double a = 15, b = 4;
        String[] operators = {"+", "-", "*", "/"};
        for (String op : operators) {
            try {
                System.out.println(a + " " + op + " " + b + " = " + calculate(a, b, op));
            } catch (Exception e) {
                System.out.println(a + " " + op + " " + b + " -> " + e.getMessage());
            }
        }
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What's new in Java 12 switch?");
        System.out.println("A1: Switch expressions with -> syntax (Preview)");
        
        System.out.println("\nQ2: When did switch expressions become standard?");
        System.out.println("A2: Java 14 (Preview in 12 & 13)");
    }
}