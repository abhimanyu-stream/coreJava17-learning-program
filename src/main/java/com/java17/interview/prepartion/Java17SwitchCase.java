package com.java17.interview.prepartion;

/**
 * 🚀 JAVA 17 SWITCH EXPRESSIONS - Modern Switch Features
 * 
 * Java 17 includes enhanced switch expressions introduced in Java 14
 * with pattern matching preview features.
 * 
 * Features:
 * - Switch expressions (return values)
 * - Arrow syntax (->)
 * - yield keyword
 * - Pattern matching (preview)
 * - No fall-through by default
 */
public class Java17SwitchCase {
    
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }
    
    /**
     * Switch Expression with arrow syntax
     */
    public static String getDayTypeModern(Day day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
    }
    
    /**
     * Switch Expression with yield keyword
     */
    public static String getSeasonDescription(Season season) {
        return switch (season) {
            case SPRING -> {
                String description = "Flowers bloom and weather gets warmer";
                yield "Spring: " + description;
            }
            case SUMMER -> {
                String description = "Hot weather, perfect for vacations";
                yield "Summer: " + description;
            }
            case AUTUMN -> {
                String description = "Leaves change color and fall";
                yield "Autumn: " + description;
            }
            case WINTER -> {
                String description = "Cold weather, snow and ice";
                yield "Winter: " + description;
            }
        };
    }
    
    /**
     * Switch Expression for calculations
     */
    public static double calculateModern(double a, double b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            case "%" -> a % b;
            case "^", "**" -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }
    
    /**
     * Switch Expression with multiple values
     */
    public static int getDaysInMonth(int month, boolean isLeapYear) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear ? 29 : 28;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }
    
    /**
     * Switch Expression for HTTP status categories
     */
    public static String getHttpStatusCategory(int statusCode) {
        return switch (statusCode / 100) {
            case 1 -> "Informational";
            case 2 -> "Success";
            case 3 -> "Redirection";
            case 4 -> "Client Error";
            case 5 -> "Server Error";
            default -> "Unknown Status";
        };
    }
    
    /**
     * Switch Expression with complex logic
     */
    public static String getGradeWithComments(int score) {
        return switch (score / 10) {
            case 10, 9 -> {
                String grade = "A";
                String comment = "Excellent work!";
                yield grade + " - " + comment;
            }
            case 8 -> {
                String grade = "B";
                String comment = "Good job!";
                yield grade + " - " + comment;
            }
            case 7 -> {
                String grade = "C";
                String comment = "Average performance";
                yield grade + " - " + comment;
            }
            case 6 -> {
                String grade = "D";
                String comment = "Needs improvement";
                yield grade + " - " + comment;
            }
            default -> {
                if (score < 0 || score > 100) {
                    yield "Invalid score";
                }
                yield "F - Failed";
            }
        };
    }
    
    /**
     * Switch Expression for file type detection
     */
    public static String getFileType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        return switch (extension) {
            case "jpg", "jpeg", "png", "gif", "bmp" -> "Image";
            case "mp4", "avi", "mkv", "mov", "wmv" -> "Video";
            case "mp3", "wav", "flac", "aac", "ogg" -> "Audio";
            case "pdf", "doc", "docx", "txt", "rtf" -> "Document";
            case "java", "py", "js", "cpp", "c", "html", "css" -> "Code";
            case "zip", "rar", "7z", "tar", "gz" -> "Archive";
            default -> "Unknown";
        };
    }
    
    /**
     * Switch Expression for priority levels
     */
    public static String getPriorityAction(String priority) {
        return switch (priority.toUpperCase()) {
            case "CRITICAL", "HIGH" -> "Immediate action required";
            case "MEDIUM" -> "Schedule within 24 hours";
            case "LOW" -> "Handle when convenient";
            case "NONE" -> "No action needed";
            default -> "Invalid priority level";
        };
    }
    
    /**
     * Traditional switch statement (still valid in Java 17)
     */
    public static String getTraditionalDayType(Day day) {
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
    
    /**
     * Comparison: Traditional vs Modern Switch
     */
    public static void compareTraditionalVsModern() {
        Day day = Day.FRIDAY;
        
        // Traditional approach
        String traditional = getTraditionalDayType(day);
        
        // Modern approach
        String modern = getDayTypeModern(day);
        
        System.out.println("Traditional result: " + traditional);
        System.out.println("Modern result: " + modern);
        System.out.println("Results match: " + traditional.equals(modern));
    }
    
    public static void main(String[] args) {
        System.out.println("🚀 JAVA 17 SWITCH EXPRESSIONS DEMONSTRATION");
        System.out.println("=" .repeat(60));
        
        // 1. Modern Switch Expressions
        System.out.println("1. Modern Switch Expressions:");
        for (Day day : Day.values()) {
            System.out.println(day + " -> " + getDayTypeModern(day));
        }
        
        // 2. Switch with yield keyword
        System.out.println("\n2. Switch with yield keyword:");
        for (Season season : Season.values()) {
            System.out.println(getSeasonDescription(season));
        }
        
        // 3. Calculator with modern switch
        System.out.println("\n3. Modern Calculator:");
        double a = 15, b = 4;
        String[] operators = {"+", "-", "*", "/", "%", "^"};
        for (String op : operators) {
            try {
                double result = calculateModern(a, b, op);
                System.out.println(a + " " + op + " " + b + " = " + result);
            } catch (Exception e) {
                System.out.println(a + " " + op + " " + b + " -> " + e.getMessage());
            }
        }
        
        // 4. Days in month
        System.out.println("\n4. Days in Month (Modern):");
        int[] months = {1, 2, 4, 12};
        for (int month : months) {
            System.out.println("Month " + month + ": " + getDaysInMonth(month, false) + " days");
        }
        
        // 5. HTTP Status Categories
        System.out.println("\n5. HTTP Status Categories:");
        int[] statusCodes = {200, 301, 404, 500, 101};
        for (int code : statusCodes) {
            System.out.println(code + " -> " + getHttpStatusCategory(code));
        }
        
        // 6. Grade with Comments
        System.out.println("\n6. Grade Evaluation:");
        int[] scores = {95, 85, 75, 65, 45, 105};
        for (int score : scores) {
            System.out.println("Score " + score + " -> " + getGradeWithComments(score));
        }
        
        // 7. File Type Detection
        System.out.println("\n7. File Type Detection:");
        String[] files = {"photo.jpg", "video.mp4", "song.mp3", "document.pdf", "script.java", "archive.zip"};
        for (String file : files) {
            System.out.println(file + " -> " + getFileType(file));
        }
        
        // 8. Priority Actions
        System.out.println("\n8. Priority Actions:");
        String[] priorities = {"CRITICAL", "HIGH", "MEDIUM", "LOW", "NONE", "INVALID"};
        for (String priority : priorities) {
            System.out.println(priority + " -> " + getPriorityAction(priority));
        }
        
        // 9. Traditional vs Modern Comparison
        System.out.println("\n9. Traditional vs Modern Comparison:");
        compareTraditionalVsModern();
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What's new in Java 17 switch expressions?");
        System.out.println("A1: Arrow syntax (->), yield keyword, no fall-through, return values");
        
        System.out.println("\nQ2: Difference between -> and : in switch?");
        System.out.println("A2: -> is expression syntax (no break needed), : is statement syntax");
        
        System.out.println("\nQ3: When to use yield keyword?");
        System.out.println("A3: In switch expressions with block statements that need to return values");
        
        System.out.println("\nQ4: Can we mix -> and : syntax?");
        System.out.println("A4: No, must use consistent syntax throughout the switch");
        
        System.out.println("\nQ5: Benefits of switch expressions?");
        System.out.println("A5: 1) More concise code");
        System.out.println("    2) No fall-through bugs");
        System.out.println("    3) Exhaustiveness checking");
        System.out.println("    4) Can return values directly");
        
        System.out.println("\nQ6: What is exhaustiveness in switch expressions?");
        System.out.println("A6: Compiler ensures all possible values are handled (no missing cases)");
    }
}