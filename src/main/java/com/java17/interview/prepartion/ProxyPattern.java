package com.java17.interview.prepartion;

public class ProxyPattern {

    /////////////////////////////////////////////////
// CLIENT
    /////////////////////////////////////////////////
    public static void main(String[] args) {

        /**
         * Proxy Pattern is easy to remember:
         *
         * Proxy controls access to a real object
         *
         * Real-world analogy:
         *
         * You → Security Guard (Proxy) → Principal (Real Object)
         *
         * The guard may:
         *
         * allow access
         * deny access
         * log access
         * cache results
         * lazy load expensive object
         * Interview-friendly Student Example
         *
         * Suppose:
         *
         * Only teachers can access student marks.
         *
         */
        // Student trying
        StudentRecord studentAccess =
                new StudentRecordProxy("Student", "Rahul");

        studentAccess.viewMarks();

        System.out.println("------------------");

        // Teacher trying
        StudentRecord teacherAccess =
                new StudentRecordProxy("Teacher", "Rahul");

        teacherAccess.viewMarks();

        /**
         * Output
         * Access Denied! Only teachers can view marks.
         * ------------------
         * Showing marks for Rahul
         * Flow
         * Student access
         * Client
         *    |
         * StudentRecordProxy
         *    |
         * Access check → denied
         * Teacher access
         * Client
         *    |
         * StudentRecordProxy
         *    |
         * Access check → allowed
         *    |
         * RealStudentRecord
         *    |
         * viewMarks()
         * Why this is Proxy Pattern
         *
         * Because client does NOT directly call:
         *
         * new RealStudentRecord(...)
         *
         * Instead:
         *
         * StudentRecordProxy
         *
         * stands in front and controls access.
         *
         * Interview explanation
         *
         * Say:
         *
         * “Proxy implements the same interface as the real object and acts as a substitute that controls access before delegating to the real object.”
         *
         */
    }
}
// Step 1: Subject Interface
interface StudentRecord {
    void viewMarks();
}

/////////////////////////////////////////////////
// REAL OBJECT
/////////////////////////////////////////////////

class RealStudentRecord implements StudentRecord {

    private String studentName;

    public RealStudentRecord(String studentName) {
        this.studentName = studentName;
    }

    public void viewMarks() {
        System.out.println("Showing marks for " + studentName);
    }
}

/////////////////////////////////////////////////
// PROXY
/////////////////////////////////////////////////

class StudentRecordProxy implements StudentRecord {

    private String userRole;
    private RealStudentRecord realRecord;
    private String studentName;

    public StudentRecordProxy(String userRole, String studentName) {
        this.userRole = userRole;
        this.studentName = studentName;
    }

    public void viewMarks() {

        if (userRole.equalsIgnoreCase("Teacher")) {

            // lazy creation of real object
            if (realRecord == null) {
                realRecord = new RealStudentRecord(studentName);
            }

            realRecord.viewMarks();

        } else {
            System.out.println("Access Denied! Only teachers can view marks.");
        }
    }
}



/***
 * Types of Proxy (good bonus in interview)
 * Type	Use
 * Protection Proxy	Access control
 * Virtual Proxy	Lazy loading
 * Remote Proxy	Remote service call
 * Caching Proxy	Cache results
 * Spring Boot real-world examples
 *
 * Proxy pattern is everywhere:
 *
 * Spring AOP
 * Controller → Proxy → real service
 *
 * Adds:
 *
 * transactions
 * logging
 * security
 * Hibernate Lazy Loading
 * User entity proxy → actual DB fetch later
 * Easy memory trick
 * Decorator adds behavior
 * Proxy controls access
 */