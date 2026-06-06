package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;

public class CachingProxy {
    /////////////////////////////////////////////////
// CLIENT
    /////////////////////////////////////////////////
    public static void main(String[] args) {
        /**
         * Caching Proxy Example
         * Problem
         *
         * Fetching student from DB is expensive.
         *
         * If same request comes again:
         *
         * return cached result instead of calling DB
         *
         * This is classic Proxy.
         */

        StudentzService service =
                new CachinggProxy(new RealStudentzService());

        System.out.println(service.getStudentDetails(101));
        System.out.println("----------------");
        System.out.println(service.getStudentDetails(101));
    }
}
// Step 1: Subject Interface
interface StudentzService {
    String getStudentDetails(int id);
}

/////////////////////////////////////////////////
// REAL OBJECT
/////////////////////////////////////////////////

class RealStudentzService implements StudentzService {

    public String getStudentDetails(int id) {
        System.out.println("Fetching from database...");
        return "Student Name: Rahul, ID: " + id;
    }
}

/////////////////////////////////////////////////
// CACHING PROXY
/////////////////////////////////////////////////

class CachinggProxy implements StudentzService {

    private StudentzService realService;
    private Map<Integer, String> cache = new HashMap<>();

    public CachinggProxy(StudentzService realService) {
        this.realService = realService;
    }

    public String getStudentDetails(int id) {

        if (cache.containsKey(id)) {
            System.out.println("Returning from cache...");
            return cache.get(id);
        }

        String result = realService.getStudentDetails(id);

        cache.put(id, result);

        return result;
    }
}

/**
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
 */