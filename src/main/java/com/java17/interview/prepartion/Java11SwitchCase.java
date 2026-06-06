package com.java17.interview.prepartion;

/**
 * 🔄 JAVA 11 SWITCH CASE - Traditional Switch with Java 11 Features
 */
public class Java11SwitchCase {
    
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    // Traditional switch statement (same as Java 8)
    public static String getDayType(Day day) {
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
    
    // String switch (available since Java 7)
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
            default:
                type = "Unknown Language";
                break;
        }
        return type;
    }
    
    // Nested switch example
    public static String getWorkSchedule(Day day, boolean isHoliday) {
        if (isHoliday) {
            return "Holiday - Closed";
        }
        
        String schedule;
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                schedule = "9 AM - 6 PM";
                break;
            case SATURDAY:
                schedule = "10 AM - 4 PM";
                break;
            case SUNDAY:
                schedule = "Closed";
                break;
            default:
                schedule = "Invalid day";
                break;
        }
        return schedule;
    }
    
    // Switch with calculations
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
            case '%':
                result = a % b;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 JAVA 11 SWITCH CASE DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        // Note: Java 11 doesn't introduce new switch features
        // Switch expressions were introduced in Java 12 (preview)
        
        System.out.println("1. Traditional Switch with Enums:");
        for (Day day : Day.values()) {
            System.out.println(day + " -> " + getDayType(day));
        }
        
        System.out.println("\n2. String Switch:");
        String[] languages = {"Java", "Python", "JavaScript", "Go"};
        for (String lang : languages) {
            System.out.println(lang + " -> " + getLanguageType(lang));
        }
        
        System.out.println("\n3. Work Schedule:");
        System.out.println("Monday (normal): " + getWorkSchedule(Day.MONDAY, false));
        System.out.println("Monday (holiday): " + getWorkSchedule(Day.MONDAY, true));
        System.out.println("Sunday (normal): " + getWorkSchedule(Day.SUNDAY, false));
        
        System.out.println("\n4. Calculator:");
        double a = 10, b = 3;
        char[] operators = {'+', '-', '*', '/', '%'};
        for (char op : operators) {
            try {
                double result = calculate(a, b, op);
                System.out.println(a + " " + op + " " + b + " = " + result);
            } catch (Exception e) {
                System.out.println(a + " " + op + " " + b + " -> " + e.getMessage());
            }
        }
        
        // Java 11 specific features (not switch-related)
        System.out.println("\n🚀 Java 11 Features (Non-Switch):");
        System.out.println("- Local-Variable Syntax for Lambda Parameters (var)");
        System.out.println("- HTTP Client API (standardized)");
        System.out.println("- String methods: isBlank(), lines(), strip(), repeat()");
        System.out.println("- Files methods: readString(), writeString()");
        System.out.println("- Collection.toArray(IntFunction)");
        
        // String methods demo
        String text = "  Hello Java 11  ";
        System.out.println("\nJava 11 String methods:");
        System.out.println("Original: '" + text + "'");
        System.out.println("strip(): '" + text.strip() + "'");
        System.out.println("isBlank(): " + "   ".isBlank());
        System.out.println("repeat(3): " + "Java ".repeat(3));
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What's new in Java 11 switch?");
        System.out.println("A1: Nothing new - switch expressions came in Java 12");
        
        System.out.println("\nQ2: Java 11 LTS significance?");
        System.out.println("A2: First LTS after Java 8, Oracle JDK licensing changes");
        
        System.out.println("\nQ3: Key Java 11 features?");
        System.out.println("A3: HTTP Client, String methods, var in lambdas, ZGC");
        
        System.out.println("\nQ4: Switch statement limitations?");
        System.out.println("A4: Fall-through behavior, verbose syntax, no return value");
    }
}
