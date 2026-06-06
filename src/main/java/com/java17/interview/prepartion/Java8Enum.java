package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 🏷️ JAVA 8 ENUM - Enum Features with Java 8 Enhancements
 */
public class Java8Enum {
    
    // Basic Enum
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
        
        public boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }
        
        // Java 8: Using streams with enums
        public static List<Day> getWeekdays() {
            return Arrays.stream(values())
                    .filter(day -> !day.isWeekend())
                    .collect(Collectors.toList());
        }
        
        public static List<Day> getWeekends() {
            return Arrays.stream(values())
                    .filter(Day::isWeekend)
                    .collect(Collectors.toList());
        }
    }
    
    // Enum with Constructor and Methods
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
        
        // Java 8: Stream operations on enum values
        public static Planet getHeaviestPlanet() {
            return Arrays.stream(values())
                    .max(Comparator.comparingDouble(Planet::getMass))
                    .orElse(null);
        }
        
        public static double getAverageMass() {
            return Arrays.stream(values())
                    .mapToDouble(Planet::getMass)
                    .average()
                    .orElse(0.0);
        }
    }
    
    // Enum with Lambda-friendly methods
    enum Status {
        ACTIVE("Currently active", true),
        INACTIVE("Temporarily disabled", false),
        PENDING("Waiting for approval", false),
        SUSPENDED("Suspended due to violation", false);
        
        private final String description;
        private final boolean operational;
        
        Status(String description, boolean operational) {
            this.description = description;
            this.operational = operational;
        }
        
        public String getDescription() { return description; }
        public boolean isOperational() { return operational; }
        
        // Java 8: Functional interface methods
        public static List<Status> getOperationalStatuses() {
            return Arrays.stream(values())
                    .filter(Status::isOperational)
                    .collect(Collectors.toList());
        }
        
        public static Map<Boolean, List<Status>> groupByOperational() {
            return Arrays.stream(values())
                    .collect(Collectors.partitioningBy(Status::isOperational));
        }
    }
    
    // Enum implementing functional interface
    enum MathOperation {
        PLUS("+", (a, b) -> a + b),
        MINUS("-", (a, b) -> a - b),
        MULTIPLY("*", (a, b) -> a * b),
        DIVIDE("/", (a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });
        
        private final String symbol;
        private final java.util.function.BinaryOperator<Double> operation;
        
        MathOperation(String symbol, java.util.function.BinaryOperator<Double> operation) {
            this.symbol = symbol;
            this.operation = operation;
        }
        
        public String getSymbol() { return symbol; }
        
        public double apply(double a, double b) {
            return operation.apply(a, b);
        }
        
        // Java 8: Stream-based calculations
        public static Map<String, Double> performAllOperations(double a, double b) {
            return Arrays.stream(values())
                    .collect(Collectors.toMap(
                            MathOperation::getSymbol,
                            op -> {
                                try {
                                    return op.apply(a, b);
                                } catch (ArithmeticException e) {
                                    return Double.NaN;
                                }
                            }
                    ));
        }
    }
    
    // Enum with Optional usage (Java 8)
    enum Priority {
        LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);
        
        private final int level;
        
        Priority(int level) {
            this.level = level;
        }
        
        public int getLevel() { return level; }
        
        // Java 8: Optional-based methods
        public static Optional<Priority> fromLevel(int level) {
            return Arrays.stream(values())
                    .filter(p -> p.level == level)
                    .findFirst();
        }
        
        public static Optional<Priority> getHighestPriority() {
            return Arrays.stream(values())
                    .max(Comparator.comparingInt(Priority::getLevel));
        }
        
        public boolean isHigherThan(Priority other) {
            return this.level > other.level;
        }
    }
    
    // Enum with Stream collectors
    enum Department {
        ENGINEERING("ENG", Arrays.asList("Java", "Python", "JavaScript")),
        MARKETING("MKT", Arrays.asList("Analytics", "Content", "Social Media")),
        SALES("SLS", Arrays.asList("B2B", "B2C", "Enterprise")),
        HR("HR", Arrays.asList("Recruitment", "Training", "Benefits"));
        
        private final String code;
        private final List<String> skills;
        
        Department(String code, List<String> skills) {
            this.code = code;
            this.skills = new ArrayList<>(skills);
        }
        
        public String getCode() { return code; }
        public List<String> getSkills() { return new ArrayList<>(skills); }
        
        // Java 8: Advanced stream operations
        public static Map<String, List<String>> getAllSkillsByDepartment() {
            return Arrays.stream(values())
                    .collect(Collectors.toMap(
                            Department::name,
                            Department::getSkills
                    ));
        }
        
        public static List<String> getAllUniqueSkills() {
            return Arrays.stream(values())
                    .flatMap(dept -> dept.getSkills().stream())
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }
        
        public static long getTotalSkillCount() {
            return Arrays.stream(values())
                    .mapToLong(dept -> dept.getSkills().size())
                    .sum();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🏷️ JAVA 8 ENUM DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        // 1. Basic Enum with Java 8 Streams
        System.out.println("1. Day Enum with Streams:");
        System.out.println("All days: " + Arrays.toString(Day.values()));
        System.out.println("Weekdays: " + Day.getWeekdays());
        System.out.println("Weekends: " + Day.getWeekends());
        
        // 2. Planet Enum with Stream Operations
        System.out.println("\n2. Planet Enum with Stream Operations:");
        System.out.println("Heaviest planet: " + Planet.getHeaviestPlanet());
        System.out.printf("Average mass: %.2e kg%n", Planet.getAverageMass());
        
        // 3. Status Enum with Partitioning
        System.out.println("\n3. Status Enum with Partitioning:");
        System.out.println("Operational statuses: " + Status.getOperationalStatuses());
        Map<Boolean, List<Status>> statusGroups = Status.groupByOperational();
        System.out.println("Grouped by operational: " + statusGroups);
        
        // 4. MathOperation Enum with Functional Interface
        System.out.println("\n4. MathOperation Enum:");
        double a = 10, b = 3;
        Map<String, Double> results = MathOperation.performAllOperations(a, b);
        results.forEach((symbol, result) -> 
            System.out.println(a + " " + symbol + " " + b + " = " + result));
        
        // 5. Priority Enum with Optional
        System.out.println("\n5. Priority Enum with Optional:");
        Optional<Priority> priorityOpt = Priority.fromLevel(3);
        priorityOpt.ifPresent(p -> System.out.println("Priority level 3: " + p));
        
        Optional<Priority> highest = Priority.getHighestPriority();
        highest.ifPresent(p -> System.out.println("Highest priority: " + p));
        
        // 6. Department Enum with Advanced Streams
        System.out.println("\n6. Department Enum with Advanced Streams:");
        System.out.println("All skills by department:");
        Department.getAllSkillsByDepartment().forEach((dept, skills) -> 
            System.out.println("  " + dept + ": " + skills));
        
        System.out.println("All unique skills: " + Department.getAllUniqueSkills());
        System.out.println("Total skill count: " + Department.getTotalSkillCount());
        
        // 7. EnumSet and EnumMap with Java 8
        System.out.println("\n7. EnumSet and EnumMap with Java 8:");
        
        EnumSet<Day> workDays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("Work days: " + workDays);
        
        EnumMap<Priority, String> priorityActions = new EnumMap<>(Priority.class);
        Arrays.stream(Priority.values()).forEach(p -> 
            priorityActions.put(p, "Handle " + p.name().toLowerCase() + " priority tasks"));
        
        System.out.println("Priority actions:");
        priorityActions.forEach((priority, action) -> 
            System.out.println("  " + priority + ": " + action));
        
        // 8. Enum with Method References
        System.out.println("\n8. Enum with Method References:");
        List<String> planetNames = Arrays.stream(Planet.values())
                .map(Planet::name)
                .collect(Collectors.toList());
        System.out.println("Planet names: " + planetNames);
        
        DoubleSummaryStatistics massStats = Arrays.stream(Planet.values())
                .mapToDouble(Planet::getMass)
                .summaryStatistics();
        System.out.printf("Mass statistics: min=%.2e, max=%.2e, avg=%.2e%n",
                massStats.getMin(), massStats.getMax(), massStats.getAverage());
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: How do Java 8 streams enhance enum usage?");
        System.out.println("A1: Enable functional operations like filter, map, collect on enum values");
        
        System.out.println("\nQ2: Can enums implement functional interfaces?");
        System.out.println("A2: Yes, enums can implement and use functional interfaces");
        
        System.out.println("\nQ3: EnumSet vs HashSet for enums?");
        System.out.println("A3: EnumSet is more efficient - uses bit vectors internally");
        
        System.out.println("\nQ4: Optional with enums in Java 8?");
        System.out.println("A4: Useful for safe enum lookups and avoiding null returns");
        
        System.out.println("\nQ5: Stream collectors with enums?");
        System.out.println("A5: groupingBy, partitioningBy, toMap work well with enum properties");
    }
}
