package com.java17.interview.prepartion;

public class AbstractFactoryPattern {

    public static void main(String[] args) {
        // Choose school type
        SchoolFactory factory = new GovernmentSchoolFactory();

        Studant student = factory.createStudent();
        Teachar teacher = factory.createTeacher();

        student.study();
        teacher.teach();


        /**
         * Abstract Factory
         *
         * Composition / family-based
         *
         * Factory creates multiple related objects
         *
         * Many products.
         */
    }


    /**
     * Problem statement
     *
     * Suppose school system supports two types of schools:
     *
     * Government School
     *
     * Creates:
     *
     * Student
     * Teacher
     * Private School
     *
     * Creates:
     *
     * Student
     * Teacher
     *
     * Rule:
     *
     * Government student must go with Government teacher
     * Private student must go with Private teacher
     *
     * This is a family of related objects → perfect for Abstract Factory.
     *
     */

    /**
     * Output
     *
     * If using:
     *
     * SchoolFactory factory = new GovernmentSchoolFactory();
     *
     * Output:
     *
     * Government school student studying
     * Government school teacher teaching
     *
     * If:
     *
     * SchoolFactory factory = new PrivateSchoolFactory();
     *
     * Output:
     *
     * Private school student studying
     * Private school teacher teaching
     * Flow
     * Government
     * Client
     *    |
     * GovernmentSchoolFactory
     *    |---- createStudent() → GovernmentStudent
     *    |---- createTeacher() → GovernmentTeacher
     * Private
     * Client
     *    |
     * PrivateSchoolFactory
     *    |---- createStudent() → PrivateStudent
     *    |---- createTeacher() → PrivateTeacher
     * Why this is Abstract Factory
     *
     * Because one factory creates related products as a family
     *
     * GovernmentSchoolFactory
     *    creates:
     *       GovernmentStudent
     *       GovernmentTeacher
     *
     * not just one object.
     *
     * Compare with Factory Method
     *
     * Factory Method:
     *
     * NotificationFactory
     *    → createNotification()
     *
     * one method → one product
     *
     * Abstract Factory:
     *
     * SchoolFactory
     *    → createStudent()
     *    → createTeacher()
     *
     * multiple methods → related products
     *
     * Interview explanation (say this)
     *
     * “In Abstract Factory, the client chooses a factory first, and that factory creates a family of related objects. Here GovernmentSchoolFactory creates GovernmentStudent and GovernmentTeacher together.”
     *
     * Bonus: real-world enterprise examples
     *
     * Same idea in software:
     *
     * AWSFactory
     *    createStorage()
     *    createQueue()
     *
     * AzureFactory
     *    createStorage()
     *    createQueue()
     *
     * or
     *
     * MySQLFactory
     *    createConnection()
     *    createQueryBuilder()
     *
     * That is why Abstract Factory is used in large systems.
     *
     *
     */

}
// PRODUCT 1
interface Studant {
    void study();
}

// PRODUCT 2
interface Teachar {
    void teach();
}

/////////////////////////////////////////////////
// GOVERNMENT SCHOOL FAMILY
/////////////////////////////////////////////////

class GovernmentStudent implements Studant {
    public void study() {
        System.out.println("Government school student studying");
    }
}

class GovernmentTeacher implements Teachar {
    public void teach() {
        System.out.println("Government school teacher teaching");
    }
}

/////////////////////////////////////////////////
// PRIVATE SCHOOL FAMILY
/////////////////////////////////////////////////

class PrivateStudent implements Studant {
    public void study() {
        System.out.println("Private school student studying");
    }
}

class PrivateTeacher implements Teachar {
    public void teach() {
        System.out.println("Private school teacher teaching");
    }
}

/////////////////////////////////////////////////
// ABSTRACT FACTORY
/////////////////////////////////////////////////

interface SchoolFactory {
    Studant createStudent();
    Teachar createTeacher();
}

/////////////////////////////////////////////////
// CONCRETE FACTORIES
/////////////////////////////////////////////////

class GovernmentSchoolFactory implements SchoolFactory {

    public Studant createStudent() {
        return new GovernmentStudent();
    }

    public Teachar createTeacher() {
        return new GovernmentTeacher();
    }
}

class PrivateSchoolFactory implements SchoolFactory {

    public Studant createStudent() {
        return new PrivateStudent();
    }

    public Teachar createTeacher() {
        return new PrivateTeacher();
    }
}

/////////////////////////////////////////////////
// CLIENT
/////////////////////////////////////////////////