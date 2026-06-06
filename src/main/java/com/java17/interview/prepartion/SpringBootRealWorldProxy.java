package com.java17.interview.prepartion;

public class SpringBootRealWorldProxy {

    /**
     * 1. Spring Boot real-world Proxy examples
     *
     * Spring uses Proxy pattern heavily.
     *
     * Example 1: @Transactional
     *
     * You write:
     *
     * @Service
     * public class PaymentService {
     *
     *     @Transactional
     *     public void processPayment() {
     *         System.out.println("Business logic");
     *     }
     * }
     *
     * Looks simple.
     *
     * But internally Spring creates:
     *
     * Controller
     *    |
     * Proxy (Transaction Proxy)
     *    |
     * PaymentService
     *
     * Actual hidden flow:
     *
     * proxy.startTransaction()
     * realService.processPayment()
     * proxy.commit()
     *
     * If exception:
     *
     * proxy.rollback()
     * Conceptual hidden code
     *
     * Spring does something like:
     *
     * class PaymentServiceProxy extends PaymentService {
     *
     *     public void processPayment() {
     *         startTransaction();
     *
     *         try {
     *             super.processPayment();
     *             commit();
     *         } catch(Exception e) {
     *             rollback();
     *         }
     *     }
     * }
     *
     * Proxy controls access to real object.
     *
     * Example 2: Spring Security (@PreAuthorize)
     *
     * You write:
     *
     * @PreAuthorize("hasRole('ADMIN')")
     * public void deleteStudent() {
     *     System.out.println("Deleted");
     * }
     *
     * Hidden flow:
     *
     * Controller
     *    |
     * Security Proxy
     *    |
     * Real Service
     *
     * Proxy checks:
     *
     * Is user ADMIN?
     * YES → continue
     * NO → deny
     * Example 3: Spring AOP Logging
     *
     * You write:
     *
     * @Around(...)
     * public Object logExecution(ProceedingJoinPoint pjp)
     *
     * Hidden:
     *
     * Proxy intercepts method
     * log before
     * real method
     * log after
     * Example 4: Hibernate Lazy Loading
     *
     * Entity:
     *
     * @Entity
     * class Student {
     *
     *     @OneToMany(fetch = FetchType.LAZY)
     *     List<Course> courses;
     * }
     *
     * When query runs:
     *
     * Student s = repo.findById(1).get();
     *
     * Hibernate does NOT fetch courses immediately.
     *
     * Instead:
     *
     * Student
     *   courses = Hibernate Proxy Collection
     *
     * Only when:
     *
     * s.getCourses()
     *
     * then actual DB query runs.
     *
     * That is Virtual Proxy (lazy loading).
     *
     * Visual
     * Student object
     *    |
     * Proxy List
     *    |
     * Real DB fetch later
     */


    /**
     * Logging and caching are classic Proxy Pattern examples because proxy sits in front of the real object and does extra work before/after delegating.
     *
     * 1. Logging Proxy Example
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
     * Java Code
     * // Step 1: Subject Interface
     * interface StudentService {
     *     String getStudentDetails(int id);
     * }
     *
     * /////////////////////////////////////////////////
     * // REAL OBJECT
     * /////////////////////////////////////////////////
     *
     * class RealStudentService implements StudentService {
     *
     *     public String getStudentDetails(int id) {
     *         return "Student Name: Rahul, ID: " + id;
     *     }
     * }
     *
     * /////////////////////////////////////////////////
     * // LOGGING PROXY
     * /////////////////////////////////////////////////
     *
     * class LoggingProxy implements StudentService {
     *
     *     private StudentService realService;
     *
     *     public LoggingProxy(StudentService realService) {
     *         this.realService = realService;
     *     }
     *
     *     public String getStudentDetails(int id) {
     *
     *         System.out.println("[LOG] Request received for student ID: " + id);
     *
     *         String result = realService.getStudentDetails(id);
     *
     *         System.out.println("[LOG] Response sent: " + result);
     *
     *         return result;
     *     }
     * }
     *
     * /////////////////////////////////////////////////
     * // CLIENT
     * /////////////////////////////////////////////////
     *
     * public class LoggingProxyDemo {
     *
     *     public static void main(String[] args) {
     *
     *         StudentService service =
     *                 new LoggingProxy(new RealStudentService());
     *
     *         String result = service.getStudentDetails(101);
     *
     *         System.out.println(result);
     *     }
     * }
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
     * 2. Caching Proxy Example
     * Problem
     *
     * Fetching student from DB is expensive.
     *
     * If same request comes again:
     *
     * return cached result instead of calling DB
     *
     * This is classic Proxy.
     *
     * Java Code
     * import java.util.HashMap;
     * import java.util.Map;
     *
     * // Step 1: Subject Interface
     * interface StudentService {
     *     String getStudentDetails(int id);
     * }
     *
     * /////////////////////////////////////////////////
     * // REAL OBJECT
     * /////////////////////////////////////////////////
     *
     * class RealStudentService implements StudentService {
     *
     *     public String getStudentDetails(int id) {
     *         System.out.println("Fetching from database...");
     *         return "Student Name: Rahul, ID: " + id;
     *     }
     * }
     *
     * /////////////////////////////////////////////////
     * // CACHING PROXY
     * /////////////////////////////////////////////////
     *
     * class CachingProxy implements StudentService {
     *
     *     private StudentService realService;
     *     private Map<Integer, String> cache = new HashMap<>();
     *
     *     public CachingProxy(StudentService realService) {
     *         this.realService = realService;
     *     }
     *
     *     public String getStudentDetails(int id) {
     *
     *         if (cache.containsKey(id)) {
     *             System.out.println("Returning from cache...");
     *             return cache.get(id);
     *         }
     *
     *         String result = realService.getStudentDetails(id);
     *
     *         cache.put(id, result);
     *
     *         return result;
     *     }
     * }
     *
     * /////////////////////////////////////////////////
     * // CLIENT
     * /////////////////////////////////////////////////
     *
     * public class CachingProxyDemo {
     *
     *     public static void main(String[] args) {
     *
     *         StudentService service =
     *                 new CachingProxy(new RealStudentService());
     *
     *         System.out.println(service.getStudentDetails(101));
     *         System.out.println("----------------");
     *         System.out.println(service.getStudentDetails(101));
     *     }
     * }
     * Output
     * Fetching from database...
     * Student Name: Rahul, ID: 101
     * ----------------
     * Returning from cache...
     * Student Name: Rahul, ID: 101
     * Flow
     * First call
     * Client
     *    |
     * CachingProxy
     *    |
     * cache miss
     *    |
     * RealStudentService (DB)
     *    |
     * store in cache
     * Second call
     * Client
     *    |
     * CachingProxy
     *    |
     * cache hit
     *    |
     * return cached data
     *
     * No DB call.
     *
     * Real Spring Boot examples
     * @Cacheable
     *
     * You write:
     *
     * @Service
     * class StudentService {
     *
     *     @Cacheable("students")
     *     public Student getStudent(int id) {
     *         System.out.println("DB hit");
     *         return repository.findById(id).get();
     *     }
     * }
     *
     * Hidden Spring behavior:
     *
     * Controller
     *    |
     * Cache Proxy
     *    |
     * check cache
     *    |
     * cache miss -> real service
     * cache hit -> skip real service
     *
     * Equivalent proxy logic:
     *
     * if(cache.has(id)) return cache.get(id);
     * else call real service
     * Logging + caching together
     *
     * Spring can stack proxies:
     *
     * Controller
     *    |
     * Logging Proxy
     *    |
     * Caching Proxy
     *    |
     * Transaction Proxy
     *    |
     * Real Service
     *
     * This is why Spring uses proxy heavily.
     *
     * Interview one-liner
     *
     * “Logging proxy adds observability around a real object, while caching proxy avoids expensive calls by intercepting requests and returning cached data before delegating.”
     *
     *
     *
     */
}
