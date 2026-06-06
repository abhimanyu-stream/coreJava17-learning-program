package com.java17.interview.prepartion;

public class FindRecentlyJoinedEmployee {
    public static void main(String[] args) {
        /**
         * Employee recent = employees.stream()
         *         .max(Comparator.comparing(Employee::joinDate))
         *         .orElse(null);
         */

        /**
         * Convert Employee List to Map (Preserve Order)
         * Map<LocalDateTime, Employee> map =
         *         employees.stream()
         *                 .collect(Collectors.toMap(
         *                         Employee::joinDate,
         *                         Function.identity(),
         *                         (e1, e2) -> e1,
         *                         LinkedHashMap::new
         *                 ));
         */

        /**
         * Correct Code
         * public class Test {
         *     public static void main(String[] args) {
         *         try {
         *             String value = null;
         *             int number = Integer.parseInt(value);
         *         } catch (NullPointerException e) {
         *             System.out.println("NullPointerException occurred.");
         *         } catch (Exception e) {
         *             System.out.println("Exception occurred.");
         *         }
         *     }
         * }
         *
         * Explanation
         *
         * Exception is the parent of NullPointerException
         *
         * Catch blocks must go from most specific to most general
         *
         * Integer.parseInt(null) throws NumberFormatException
         */

        /**
         * Inheritance, Polymorphism, and Method Overriding
         * Question
         *
         * Why does the following code not compile? Fix it and explain method overriding with checked exceptions.
         *
         * class Person {
         *     public void display() throws IOException {
         *         System.out.println("Person.Display() called");
         *     }
         * }
         *
         * class Child extends Person {
         *     public void display() throws IOException {
         *         System.out.println("Child.Display() called");
         *     }
         * }
         *
         * public class Main {
         *     public static void main(String[] args) {
         *         Child obj = new Person();
         *         obj.display();
         *     }
         * }
         *
         * Answer
         *
         * The assignment is invalid. A parent object cannot be assigned to a child reference.
         *
         * Correct Code
         * class Person {
         *     public void display() throws IOException {
         *         System.out.println("Person.Display() called");
         *     }
         * }
         *
         * class Child extends Person {
         *     @Override
         *     public void display() throws IOException {
         *         System.out.println("Child.Display() called");
         *     }
         * }
         *
         * public class Main {
         *     public static void main(String[] args) throws IOException {
         *         Person obj = new Child(); // upcasting
         *         obj.display();            // runtime polymorphism
         *     }
         * }
         *
         * Explanation
         *
         * Upcasting (Person obj = new Child()) is allowed.
         *
         * Method call is resolved at runtime.
         *
         * Overridden methods cannot throw broader checked exceptions.
         */
    }
}
