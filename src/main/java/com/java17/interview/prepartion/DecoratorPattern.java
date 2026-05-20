package com.java17.interview.prepartion;

public class DecoratorPattern {
    /////////////////////////////////////////////////
// CLIENT
    /////////////////////////////////////////////////
    public static void main(String[] args) {

        Studentz student = new BasicStudent();

        System.out.println("Regular Student:");
        student.showFeatures();

        System.out.println("----------------");

        Studentz alumni = new AlumniDecorator(student);

        System.out.println("Alumni Student:");
        alumni.showFeatures();

        /**
         * Output
         * Regular Student:
         * Access to library
         *
         * ----------------
         *
         * Alumni Student:
         * Access to library
         * Access to alumni portal
         * Access to networking events
         * Flow
         * Without decorator
         * Client → BasicStudent
         * With decorator
         * Client → AlumniDecorator → BasicStudent
         *
         * Decorator calls original behavior:
         *
         * library
         *
         * then adds:
         *
         * alumni portal
         * networking
         * Proxy vs Decorator side-by-side
         * 	Proxy	Decorator
         * Goal	Control access	Add behavior
         * Real object call	maybe allow/deny	always delegates + extends
         * Spring example	@Transactional	request wrappers, filters
         * Interview phrase	“gatekeeper”	“feature enhancer”
         * Easy memory trick
         * Proxy = Security Guard
         * Decorator = Add accessories
         *
         * Example:
         *
         * Student
         * Proxy → check if can enter
         * Decorator → add alumni benefits
         * Spring Boot Decorator real-world examples
         * HttpServletRequestWrapper
         *
         * Adds behavior to request.
         *
         * BufferedInputStream
         *
         * Adds buffering to InputStream.
         *
         * Spring filters
         * Request → LoggingDecorator → CompressionDecorator → Real Request
         *
         * These are decorator-style wrappers.
         *
         */

    }
}

// Step 1: Component Interface
interface Studentz {
    void showFeatures();
}

/////////////////////////////////////////////////
// CONCRETE COMPONENT
/////////////////////////////////////////////////

class BasicStudent implements Studentz {

    public void showFeatures() {
        System.out.println("Access to library");
    }
}

/////////////////////////////////////////////////
// DECORATOR BASE
/////////////////////////////////////////////////

abstract class StudentDecorator implements Studentz {

    protected Studentz student;

    public StudentDecorator(Studentz student) {
        this.student = student;
    }

    public void showFeatures() {
        student.showFeatures();
    }
}

/////////////////////////////////////////////////
// CONCRETE DECORATOR
/////////////////////////////////////////////////

class AlumniDecorator extends StudentDecorator {

    public AlumniDecorator(Studentz student) {
        super(student);
    }

    public void showFeatures() {
        student.showFeatures(); // original behavior

        addAlumniBenefits();
    }

    private void addAlumniBenefits() {
        System.out.println("Access to alumni portal");
        System.out.println("Access to networking events");
    }
}

