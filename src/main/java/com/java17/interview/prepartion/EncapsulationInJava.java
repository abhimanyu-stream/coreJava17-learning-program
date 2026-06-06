package com.java17.interview.prepartion;

public class EncapsulationInJava {
    public static void main(String[] args) {

        StudentSymbiosys student = new StudentSymbiosys();

        // Setting values using setter methods
        student.setName("Alice");
        student.setAge(20);

        // Accessing values using getter methods
        System.out.println("Student Name: " + student.getName());
        System.out.println("Student Age: " + student.getAge());

        // Trying to set an invalid age
        student.setAge(-5);  // Prints "Invalid age"

    }

}

class StudentSymbiosys {
    // Private fields
    private String name;
    private int age;

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name with validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name");
        }
    }

    // Getter method for age
    public int getAge() {
        return age;
    }

    // Setter method for age with validation
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age");
        }
    }
}
/**
 * Encapsulation in Java is the concept of wrapping data (variables) and methods that operate on the data into a single unit, i.e., a class. It restricts direct access to certain components and can prevent the accidental modification of data. Encapsulation provides better control over data by hiding it and only exposing what is necessary through public methods.
 *
 * Key Points of Encapsulation
 * It allows the fields of a class to be made private.
 * It provides public methods (getters and setters) to access and modify the values of private fields.
 * This helps in protecting the data integrity and makes the code more flexible and easier to maintain.
 * Benefits of Encapsulation
 * Data Hiding: Internal representation of an object is hidden from the outside.
 * Increased Security: Data can only be modified through controlled methods, ensuring integrity.
 * Reusability and Flexibility: You can change the internal implementation without affecting other parts of the code.
 * Example of Encapsulation in Java
 * Let's say we have a class Student with name and age fields. We want to restrict direct access to these fields and control how they are accessed and modified.
 *
 *
 *
 *
 *
 * Explanation
 * The Student class has two private fields, name and age, which are only accessible within the class.
 * Public getter (getName and getAge) and setter (setName and setAge) methods provide controlled access to these private fields.
 * The setter methods include validation to ensure that only valid data is set, maintaining data integrity.
 * By encapsulating the data in the Student class, we protect the name and age fields from being modified in unexpected ways. Any external code that wants to access or modify these fields has to go through the provided methods, ensuring controlled access and better maintainability.
 *
 *
 * */