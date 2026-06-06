package com.java17.interview.prepartion;

/**
 * Demonstrates Java 17 Features:
 * 1. Records (finalized in Java 16)
 * 2. Sealed classes/interfaces (finalized in Java 17)
 * 3. Pattern matching for instanceof (finalized in Java 16)
 */
public class Java17Features {

    // ============================================
    // 1. RECORDS - Compact data carriers
    // ============================================
    
    // Simple record with automatic constructor, getters, equals, hashCode, toString
    record Person(String name, int age, String email) {
        // Compact constructor for validation
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
        }
        
        // Custom method in record
        public boolean isAdult() {
            return age >= 18;
        }
    }
    
    // Record with static fields and methods
    record Employee(String id, String name, double salary) {
        private static final double MIN_SALARY = 30000.0;
        
        public Employee {
            if (salary < MIN_SALARY) {
                throw new IllegalArgumentException("Salary below minimum");
            }
        }
        
        public static Employee createWithDefaultSalary(String id, String name) {
            return new Employee(id, name, MIN_SALARY);
        }
    }

    // ============================================
    // 2. SEALED CLASSES/INTERFACES
    // ============================================
    
    // Sealed interface - restricts which classes can implement it
    sealed interface Shape permits Circle, Rectangle, Triangle {
        double area();
        double perimeter();
    }
    
    // Final class implementing sealed interface
    final class Circle implements Shape {
        private final double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
        
        @Override
        public double perimeter() {
            return 2 * Math.PI * radius;
        }
        
        public double getRadius() {
            return radius;
        }
    }
    
    // Non-sealed class - allows further extension
    non-sealed class Rectangle implements Shape {
        private final double width;
        private final double height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public double area() {
            return width * height;
        }
        
        @Override
        public double perimeter() {
            return 2 * (width + height);
        }
        
        public double getWidth() {
            return width;
        }
        
        public double getHeight() {
            return height;
        }
    }
    
    // Sealed class extending another sealed type
    sealed class Triangle implements Shape permits EquilateralTriangle, IsoscelesTriangle {
        protected final double side1;
        protected final double side2;
        protected final double side3;
        
        public Triangle(double side1, double side2, double side3) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }
        
        @Override
        public double area() {
            double s = perimeter() / 2;
            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }
        
        @Override
        public double perimeter() {
            return side1 + side2 + side3;
        }
    }
    
    final class EquilateralTriangle extends Triangle {
        public EquilateralTriangle(double side) {
            super(side, side, side);
        }
    }
    
    final class IsoscelesTriangle extends Triangle {
        public IsoscelesTriangle(double equalSide, double base) {
            super(equalSide, equalSide, base);
        }
    }
    
    // Sealed class hierarchy for payment types
    sealed interface Payment permits CreditCardPayment, DebitCardPayment, CashPayment {
        double amount();
        String paymentMethod();
    }
    
    record CreditCardPayment(double amount, String cardNumber, String cvv) implements Payment {
        @Override
        public String paymentMethod() {
            return "Credit Card";
        }
    }
    
    record DebitCardPayment(double amount, String cardNumber, String pin) implements Payment {
        @Override
        public String paymentMethod() {
            return "Debit Card";
        }
    }
    
    record CashPayment(double amount, double receivedAmount) implements Payment {
        @Override
        public String paymentMethod() {
            return "Cash";
        }
        
        public double change() {
            return receivedAmount - amount;
        }
    }

    // ============================================
    // 3. PATTERN MATCHING FOR INSTANCEOF
    // ============================================
    
    // Old way (before Java 16)
    public static String describeShapeOldWay(Object obj) {
        if (obj instanceof Circle) {
            Circle circle = (Circle) obj;  // Explicit cast needed
            return "Circle with radius: " + circle.getRadius();
        } else if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) obj;  // Explicit cast needed
            return "Rectangle " + rectangle.getWidth() + "x" + rectangle.getHeight();
        }
        return "Unknown shape";
    }
    
    // New way with pattern matching (Java 16+)
    public static String describeShape(Object obj) {
        if (obj instanceof Circle c) {  // Pattern variable 'c' automatically created
            return "Circle with radius: " + c.getRadius() + ", area: " + c.area();
        } else if (obj instanceof Rectangle r) {  // Pattern variable 'r'
            return "Rectangle " + r.getWidth() + "x" + r.getHeight() + ", area: " + r.area();
        } else if (obj instanceof Triangle t) {
            return "Triangle with perimeter: " + t.perimeter() + ", area: " + t.area();
        }
        return "Unknown shape";
    }
    
    // Pattern matching with logical operators
    public static String analyzeShape(Shape shape) {
        if (shape instanceof Circle c && c.getRadius() > 10) {
            return "Large circle with radius: " + c.getRadius();
        } else if (shape instanceof Rectangle r && r.getWidth() == r.getHeight()) {
            return "Square with side: " + r.getWidth();
        } else if (shape instanceof Rectangle r && r.getWidth() > r.getHeight()) {
            return "Horizontal rectangle";
        }
        return "Other shape";
    }
    
    // Pattern matching for payment processing
    public static String processPayment(Payment payment) {
        if (payment instanceof CreditCardPayment cc) {
            return "Processing credit card payment of $" + cc.amount() + 
                   " ending in " + cc.cardNumber().substring(cc.cardNumber().length() - 4);
        } else if (payment instanceof DebitCardPayment dc) {
            return "Processing debit card payment of $" + dc.amount();
        } else if (payment instanceof CashPayment cash && cash.change() > 0) {
            return "Cash payment of $" + cash.amount() + ", change: $" + cash.change();
        } else if (payment instanceof CashPayment cash) {
            return "Exact cash payment of $" + cash.amount();
        }
        return "Unknown payment type";
    }
    
    // Complex pattern matching example
    public static String analyzeObject(Object obj) {
        return switch (obj) {
            case null -> "Object is null";
            case String s && s.length() > 10 -> "Long string: " + s.substring(0, 10) + "...";
            case String s -> "Short string: " + s;
            case Integer i && i > 0 -> "Positive integer: " + i;
            case Integer i -> "Non-positive integer: " + i;
            case Person p && p.isAdult() -> "Adult: " + p.name();
            case Person p -> "Minor: " + p.name();
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };
    }

    // ============================================
    // MAIN METHOD - DEMONSTRATION
    // ============================================
    
    public static void main(String[] args) {
        System.out.println("=== Java 17 Features Demo ===\n");
        
        // 1. Records Demo
        System.out.println("1. RECORDS:");
        Person person1 = new Person("John Doe", 25, "john@example.com");
        System.out.println("Person: " + person1);
        System.out.println("Is adult? " + person1.isAdult());
        System.out.println("Name: " + person1.name() + ", Age: " + person1.age());
        
        Employee emp1 = Employee.createWithDefaultSalary("E001", "Alice");
        Employee emp2 = new Employee("E002", "Bob", 50000);
        System.out.println("Employee 1: " + emp1);
        System.out.println("Employee 2: " + emp2);
        System.out.println();
        
        // 2. Sealed Classes Demo
        System.out.println("2. SEALED CLASSES:");
        Shape circle = new Java17Features().new Circle(5.0);
        Shape rectangle = new Java17Features().new Rectangle(4.0, 6.0);
        Shape triangle = new Java17Features().new EquilateralTriangle(5.0);
        
        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Triangle area: " + triangle.area());
        System.out.println();
        
        // 3. Pattern Matching Demo
        System.out.println("3. PATTERN MATCHING FOR INSTANCEOF:");
        System.out.println(describeShape(circle));
        System.out.println(describeShape(rectangle));
        System.out.println(describeShape(triangle));
        System.out.println();
        
        System.out.println("Shape Analysis:");
        System.out.println(analyzeShape(circle));
        System.out.println(analyzeShape(rectangle));
        System.out.println(analyzeShape(new Java17Features().new Rectangle(5.0, 5.0)));
        System.out.println();
        
        // Payment processing with sealed interfaces and pattern matching
        System.out.println("4. PAYMENT PROCESSING (Sealed + Pattern Matching):");
        Payment payment1 = new CreditCardPayment(100.50, "1234567890123456", "123");
        Payment payment2 = new DebitCardPayment(75.25, "9876543210987654", "4567");
        Payment payment3 = new CashPayment(50.00, 100.00);
        
        System.out.println(processPayment(payment1));
        System.out.println(processPayment(payment2));
        System.out.println(processPayment(payment3));
        System.out.println();
        
        // Complex pattern matching
        System.out.println("5. COMPLEX PATTERN MATCHING:");
        System.out.println(analyzeObject("Hello"));
        System.out.println(analyzeObject("This is a very long string for testing"));
        System.out.println(analyzeObject(42));
        System.out.println(analyzeObject(-5));
        System.out.println(analyzeObject(person1));
        System.out.println(analyzeObject(new Person("Jane", 15, "jane@example.com")));
        System.out.println(analyzeObject(null));
    }
}
