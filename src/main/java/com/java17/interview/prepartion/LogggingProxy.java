package com.java17.interview.prepartion;

public class LogggingProxy {
    /////////////////////////////////////////////////
// CLIENT
    /////////////////////////////////////////////////
    public static void main(String[] args) {
        /**
         * Logging Proxy Example
         * Problem
         *
         * Student service fetches details.
         *
         * We want:
         *
         * log request
         * call real service
         * log response
         *
         * Client should not change.
         *
         */

        StudentService service =
                new LoggingProxy(new RealStudentService());

        String result = service.getStudentDetails(101);

        System.out.println(result);

        /**
         * Output
         * [LOG] Request received for student ID: 101
         * [LOG] Response sent: Student Name: Rahul, ID: 101
         * Student Name: Rahul, ID: 101
         * Flow
         * Client
         *    |
         * LoggingProxy
         *    |---- log request
         *    |---- call RealStudentService
         *    |---- log response
         *
         */

        /**
         * Spring Boot real-world equivalent
         *
         * Spring AOP:
         *
         * @Around(...)
         *
         * internally behaves like:
         *
         * Controller
         *    |
         * Logging Proxy
         *    |
         * Real Service
         *
         * Used for:
         *
         * request logging
         * execution time
         * tracing
         *
         */
    }
}
// Step 1: Subject Interface
interface StudentService {
    String getStudentDetails(int id);
}

/////////////////////////////////////////////////
// REAL OBJECT
/////////////////////////////////////////////////

class RealStudentService implements StudentService {

    public String getStudentDetails(int id) {
        return "Student Name: Rahul, ID: " + id;
    }
}

/////////////////////////////////////////////////
// LOGGING PROXY
/////////////////////////////////////////////////

class LoggingProxy implements StudentService {

    private StudentService realService;

    public LoggingProxy(StudentService realService) {
        this.realService = realService;
    }

    public String getStudentDetails(int id) {

        System.out.println("[LOG] Request received for student ID: " + id);

        String result = realService.getStudentDetails(id);

        System.out.println("[LOG] Response sent: " + result);

        return result;
    }
}

