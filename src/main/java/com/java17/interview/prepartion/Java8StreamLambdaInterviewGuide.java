package com.java17.interview.prepartion;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 🎯 COMPREHENSIVE JAVA 8 STREAM & LAMBDA INTERVIEW GUIDE
 * 
 * This class covers all essential conceptual questions and coding problems
 * that are frequently asked in Java Lead/Senior Developer interviews.
 * 
 * Topics Covered:
 * 1. Conceptual Questions (Stream API vs Collection API, etc.)
 * 2. Hands-on Coding Questions (duplicates, frequency, etc.)
 * 3. Tricky Pitfalls & Advanced Scenarios
 * 4. Collection & Map Advanced Questions
 */
public class Java8StreamLambdaInterviewGuide {

    // Employee class for advanced examples
    static class Employee {
        private int id;
        private String name;
        private double salary;
        private String department;

        public Employee(int id, String name, double salary, String department) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }

        @Override
        public String toString() {
            return String.format("Employee{id=%d, name='%s', salary=%.2f, dept='%s'}",
                    id, name, salary, department);
        }
    }

    public static void main(String[] args) {
        Java8StreamLambdaInterviewGuide guide = new Java8StreamLambdaInterviewGuide();

        System.out.println("🎯 JAVA 8 STREAM & LAMBDA INTERVIEW GUIDE");
        System.out.println("=".repeat(60));

        // Run all examples
        guide.demonstrateConceptualQuestions();
        guide.demonstrateCodingQuestions();
        guide.demonstrateTrickyPitfalls();
        guide.demonstrateAdvancedCollections();
    }

    /**
     * 🔹 1. CONCEPTUAL QUESTIONS DEMONSTRATION
     * These are theoretical concepts you should be able to explain in interviews
     */
    public void demonstrateConceptualQuestions() {





        String input = "Abhimanyu";

        String sorted = input.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(sorted);

        //String input = "Abhimanyu";

        String sortedd = input.chars()                // IntStream of character codes
                .mapToObj(c -> (char) c)              // convert int to Character
                .sorted()                             // natural (ASCII) order
                .map(String::valueOf)                 // Character -> String
                .collect(Collectors.joining());

        System.out.println(sorted);

        Arrays.stream(input.split(""))
                .sorted()
                .forEach(System.out::print);

        List<String> wordss = Arrays.asList(
                "GFG", "Geeks", "for", "GeeksQuiz", "GeeksforGeeks"
        );

        String largest = wordss.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

        System.out.println(largest);

        List<Integer> listOfNumbers = Arrays.asList(6, 7, 8, 9, 25);
        int min = listOfNumbers.stream()
                .sorted(Comparator.naturalOrder())
                .findFirst()
                .orElseThrow();

        int max = listOfNumbers.stream()
                .sorted(Comparator.naturalOrder())
                .reduce((first, second) -> second)
                .orElseThrow();

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

        int maxx = listOfNumbers.stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElseThrow();

        int minn = listOfNumbers.stream()
                .sorted(Comparator.reverseOrder())
                .reduce((first, second) -> second)
                .orElseThrow();

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        int secondLargest = listOfNumbers.stream()
                .distinct()                // important if duplicates exist
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No second largest element"));

        System.out.println("Second Largest: " + secondLargest);








        System.out.println("\n🔹 1. CONCEPTUAL QUESTIONS DEMONSTRATION");
        System.out.println("-".repeat(50));

        // Q1: Stream API vs Collection API
        System.out.println("Q1: Stream API vs Collection API");
        System.out.println("✅ Collection: Data structure that stores elements in memory");
        System.out.println("✅ Stream: Sequence of elements that supports functional operations");
        System.out.println("✅ Collections are eagerly constructed, Streams are lazily evaluated");

        // Q2: Intermediate vs Terminal operations
        System.out.println("\nQ2: Intermediate vs Terminal Operations");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Intermediate operations (lazy) - return Stream
        Stream<Integer> intermediateStream = numbers.stream()
                .filter(n -> n > 2) // Intermediate
                .map(n -> n * 2); // Intermediate

        // Terminal operation (eager) - triggers execution
        List<Integer> result = intermediateStream.collect(Collectors.toList()); // Terminal
        System.out.println("✅ Intermediate ops: filter(), map(), sorted(), distinct()");
        System.out.println("✅ Terminal ops: collect(), forEach(), reduce(), count()");
        System.out.println("Result: " + result);

        // Q3: map() vs flatMap()
        System.out.println("\nQ3: map() vs flatMap()");
        List<String> words = Arrays.asList("Hello", "World");

        words.stream()
                .map(word -> word.toUpperCase()) // map() - 1:1 transformation
                .forEach(System.out::println);

        List.of("Hello", "World").stream()
                .map(word -> word.toUpperCase()) // map() - 1:1 transformation
                .forEach(System.out::println);



        // map() - 1:1 transformation
        List<Integer> lengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("map() result lengths: " + lengths);

        // flatMap() - 1:many transformation (flattening)
        List<String> letters = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .collect(Collectors.toList());
        System.out.println("flatMap() result: " + letters);

        // Q4: Lazy Evaluation
        System.out.println("\nQ4: Lazy Evaluation");
        System.out.println("✅ Streams execute only when terminal operation is called");
        System.out.println("✅ Intermediate operations are stored as pipeline, not executed immediately");

        // Q5: forEach() vs forEachOrdered()
        System.out.println("\nQ5: forEach() vs forEachOrdered()");
        System.out.println("✅ forEach(): No guarantee of order in parallel streams");
        System.out.println("✅ forEachOrdered(): Maintains encounter order even in parallel streams");

        // Q6: Functional Interfaces
        System.out.println("\nQ6: Functional Interfaces Examples");

        // Predicate<T> - takes T, returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Predicate (isEven): " + isEven.test(4));

        // Function<T,R> - takes T, returns R
        Function<String, Integer> stringLength = String::length;
        System.out.println("Function (length): " + stringLength.apply("Hello"));

        // Consumer<T> - takes T, returns void
        java.util.function.Consumer<String> printer = System.out::println;
        System.out.print("Consumer (print): ");
        printer.accept("Hello Consumer!");

        // Supplier<T> - takes nothing, returns T
        Supplier<Double> randomSupplier = Math::random;
        System.out.println("Supplier (random): " + randomSupplier.get());
    }

    /**
     * 🔹 2. HANDS-ON CODING QUESTIONS
     * These are practical problems you'll be asked to solve in interviews
     */
    public void demonstrateCodingQuestions() {
        System.out.println("\n🔹 2. HANDS-ON CODING QUESTIONS");
        System.out.println("-".repeat(50));

        // (a) Find duplicate elements in a list - MULTIPLE APPROACHES
        System.out.println("(a) Find Duplicate Elements - Multiple Approaches:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 6, 7, 8, 3);
        
        // Approach 1: Using groupingBy + counting (most common)
        Set<Integer> duplicates1 = numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Approach 1 (groupingBy): " + duplicates1);
        
        // Approach 2: Using Set.add() - returns false if element already exists
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates2 = numbers.stream()
                .filter(n -> !seen.add(n))  // add() returns false for duplicates
                .collect(Collectors.toSet());
        System.out.println("Approach 2 (Set.add): " + duplicates2);
        
        // Approach 3: Using frequency from Collections
        Set<Integer> duplicates3 = numbers.stream()
                .filter(n -> Collections.frequency(numbers, n) > 1)
                .collect(Collectors.toSet());
        System.out.println("Approach 3 (Collections.frequency): " + duplicates3);
        
        // Approach 4: Using distinct() and comparing sizes
        Set<Integer> uniqueSet = new HashSet<>(numbers);
        Set<Integer> duplicates4 = numbers.stream()
                .filter(n -> numbers.indexOf(n) != numbers.lastIndexOf(n))
                .collect(Collectors.toSet());
        System.out.println("Approach 4 (indexOf/lastIndexOf): " + duplicates4);
        
        // Approach 5: Using Collectors.toMap with merge function
        Set<Integer> duplicates5 = numbers.stream()
                .collect(Collectors.toMap(
                        n -> n,                    // key
                        n -> 1,                    // initial value
                        Integer::sum               // merge function (sum counts)
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Approach 5 (toMap with merge): " + duplicates5);

        // (a.1) Find duplicate elements in String Array - Using Arrays.stream()
        System.out.println("\n(a.1) Find Duplicate Strings in Array - Multiple Approaches:");
        String[] strArray = {"hello", "java", "java", "python", "hello", "javascript", "java"};
        
        // Approach 1: Using Arrays.stream() + groupingBy + counting
        Set<String> stringDuplicates1 = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Approach 1 (Arrays.stream + groupingBy): " + stringDuplicates1);
        
        // Approach 2: Using Arrays.stream() + Set.add()
        Set<String> seenStrings = new HashSet<>();
        Set<String> stringDuplicates2 = Arrays.stream(strArray)
                .filter(s -> !seenStrings.add(s))
                .collect(Collectors.toSet());
        System.out.println("Approach 2 (Arrays.stream + Set.add): " + stringDuplicates2);
        
        // Approach 3: Using Arrays.stream() + Collections.frequency()
        List<String> strList = Arrays.asList(strArray);
        Set<String> stringDuplicates3 = Arrays.stream(strArray)
                .filter(s -> Collections.frequency(strList, s) > 1)
                .collect(Collectors.toSet());
        System.out.println("Approach 3 (Arrays.stream + frequency): " + stringDuplicates3);
        
        // Approach 4: Get duplicates with their counts
        Map<String, Long> duplicatesWithCount = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Approach 4 (with counts): " + duplicatesWithCount);
        
        // Approach 5: Find only unique elements (non-duplicates)
        Set<String> uniqueOnly = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Approach 5 (unique only): " + uniqueOnly);
        
        // Approach 6: Using Stream.of() instead of Arrays.stream()
        Set<String> stringDuplicates6 = Stream.of(strArray)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Approach 6 (Stream.of): " + stringDuplicates6);

        // (b) Find frequency of each character in a string
        System.out.println("\n(b) Character Frequency:");
        String input = "Java Streams Interview";
        Map<Character, Long> freq = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Character frequency: " + freq);

        // (c) Find 2nd highest number in a list
        System.out.println("\n(c) Second Highest Number:");
        List<Integer> list = Arrays.asList(5, 9, 11, 2, 8, 21, 1);
        int secondHighest = list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(-1);
        System.out.println("Second highest: " + secondHighest);

        // (d) Reverse each word of a sentence
        System.out.println("\n(d) Reverse Each Word:");
        String str = "Java Lead Role Interview";
        String reversed = Arrays.stream(str.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));
        System.out.println("Reversed: " + reversed);

        // (e) Find longest string in a list
        System.out.println("\n(e) Longest String:");
        List<String> words = Arrays.asList("java", "microservices", "springboot", "cloud");
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Longest: " + longest);

        // (f) Partition numbers into odd and even using partitioningBy
        System.out.println("\n(f) Partition Odd/Even Numbers:");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Traditional approach - separate operations
        List<Integer> evens = nums.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Evens (traditional): " + evens);
        
        // Better approach - partitioningBy (single pass, both results)
        Map<Boolean, List<Integer>> partitioned = nums.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Evens (partitioned): " + partitioned.get(true));
        System.out.println("Odds (partitioned): " + partitioned.get(false));
        System.out.println("✅ partitioningBy is more efficient - single pass through data");

        // (g) Convert List to Map (with duplicates handling)
        System.out.println("\n(g) List to Map with Count:");
        List<String> names = Arrays.asList("Tom", "Jerry", "Tom", "John");
        Map<String, Long> nameCount = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Name count: " + nameCount);

        // (h) Flatten a list of lists
        System.out.println("\n(h) Flatten Nested Lists:");
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));
        List<Integer> flat = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened: " + flat);

        // (i) Employee Problems (classic lead-level)
        System.out.println("\n(h) Employee Problems:");
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 75000, "IT"),
                new Employee(2, "Jane", 85000, "IT"),
                new Employee(3, "Bob", 65000, "HR"),
                new Employee(4, "Alice", 95000, "Finance"),
                new Employee(5, "Charlie", 70000, "HR"),
                new Employee(6, "Diana", 90000, "Finance"));

        // Find highest paid employee per department
        Map<String, Optional<Employee>> topSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println("Highest paid per department:");
        topSalaryByDept.forEach((dept, emp) -> System.out.println("  " + dept + ": " + emp.orElse(null)));

        // Find average salary per department
        Map<String, Double> avgSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average salary per department:");
        avgSalary.forEach((dept, avg) -> System.out.println("  " + dept + ": $" + String.format("%.2f", avg)));
    }

    /**
     * 🔹 3. TRICKY PITFALLS & ADVANCED SCENARIOS
     * These are gotchas and edge cases interviewers love to ask about
     */
    public void demonstrateTrickyPitfalls() {
        System.out.println("\n🔹 3. TRICKY PITFALLS & ADVANCED SCENARIOS");
        System.out.println("-".repeat(50));

        // Pitfall 1: Stream reusability
        System.out.println("Pitfall 1: Stream Reusability");
        Stream<Integer> stream = Arrays.asList(1, 2, 3).stream();
        stream.forEach(System.out::print); // Works
        System.out.println();
        try {
            stream.forEach(System.out::print); // IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("✅ Streams cannot be reused: " + e.getMessage());
        }

        // Pitfall 2: peek() vs map()
        System.out.println("\nPitfall 2: peek() vs map()");
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // peek() - for debugging, doesn't transform
        List<Integer> peekResult = numbers.stream()
                .peek(n -> System.out.print("Processing: " + n + " "))
                .collect(Collectors.toList());
        System.out.println("\npeek() result: " + peekResult);

        // map() - transforms elements
        List<Integer> mapResult = numbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("map() result: " + mapResult);

        // Pitfall 3: Collectors.toMap() with duplicate keys
        System.out.println("\nPitfall 3: toMap() with Duplicate Keys");
        List<String> words = Arrays.asList("apple", "banana", "apricot");
        try {
            // This will throw IllegalStateException due to duplicate key 'a'
            Map<Character, String> unsafeMap = words.stream()
                    .collect(Collectors.toMap(
                            word -> word.charAt(0), // Key: first character
                            word -> word // Value: the word
                    ));
            System.out.println("This won't be reached: " + unsafeMap);
        } catch (IllegalStateException e) {
            System.out.println("✅ Duplicate key error: " + e.getMessage());
        }

        // Safe version with merge function
        Map<Character, String> safeMap = words.stream()
                .collect(Collectors.toMap(
                        word -> word.charAt(0),
                        word -> word,
                        (existing, replacement) -> existing + "," + replacement // Merge function
                ));
        System.out.println("Safe toMap() result: " + safeMap);

        // Pitfall 4: reduce() variations
        System.out.println("\nPitfall 4: reduce() Variations");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        // reduce(accumulator) - returns Optional
        Optional<Integer> sum1 = nums.stream().reduce((a, b) -> a + b);
        System.out.println("reduce(accumulator): " + sum1.orElse(0));

        // reduce(identity, accumulator) - returns T
        Integer sum2 = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println("reduce(identity, accumulator): " + sum2);

        // reduce(identity, accumulator, combiner) - for parallel streams
        Integer sum3 = nums.parallelStream().reduce(0, (a, b) -> a + b, (a, b) -> a + b);
        System.out.println("reduce(identity, accumulator, combiner): " + sum3);

        // Pitfall 5: findFirst() vs findAny()
        System.out.println("\nPitfall 5: findFirst() vs findAny()");
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);

        // Sequential stream - both return same result
        Optional<Integer> first = numbers2.stream().filter(n -> n > 2).findFirst();
        Optional<Integer> any = numbers2.stream().filter(n -> n > 2).findAny();
        System.out.println("Sequential - findFirst(): " + first + ", findAny(): " + any);

        // Parallel stream - findAny() may return different element
        Optional<Integer> firstParallel = numbers2.parallelStream().filter(n -> n > 2).findFirst();
        Optional<Integer> anyParallel = numbers2.parallelStream().filter(n -> n > 2).findAny();
        System.out.println("Parallel - findFirst(): " + firstParallel + ", findAny(): " + anyParallel);

        // Pitfall 6: Infinite Streams
        System.out.println("\nPitfall 6: Infinite Streams");

        // Stream.iterate() - infinite stream with seed and function
        List<Integer> fibonacci = Stream.iterate(new int[] { 0, 1 },
                arr -> new int[] { arr[1], arr[0] + arr[1] })
                .map(arr -> arr[0])
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Fibonacci (iterate): " + fibonacci);

        // Stream.generate() - infinite stream with supplier
        List<Double> randomNumbers = Stream.generate(Math::random)
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("Random numbers (generate): " + randomNumbers);
    }

    /**
     * 🔹 4. ADVANCED COLLECTION & MAP QUESTIONS
     * These cover advanced collection concepts and Map operations
     */
    public void demonstrateAdvancedCollections() {
        System.out.println("\n🔹 4. ADVANCED COLLECTION & MAP QUESTIONS");
        System.out.println("-".repeat(50));

        // Map Types Comparison
        System.out.println("Map Types Comparison:");
        System.out.println("✅ HashMap: No ordering, allows null key/values, not thread-safe");
        System.out.println("✅ LinkedHashMap: Maintains insertion order");
        System.out.println("✅ TreeMap: Sorted by keys, implements NavigableMap");
        System.out.println("✅ ConcurrentHashMap: Thread-safe, no null keys/values");

        // Sort Map by Values
        System.out.println("\nSorting Map by Values:");
        Map<String, Integer> unsorted = Map.of("A", 10, "B", 5, "C", 20, "D", 15);

        LinkedHashMap<String, Integer> sortedByValue = unsorted.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        System.out.println("Original: " + unsorted);
        System.out.println("Sorted by value (desc): " + sortedByValue);

        // ConcurrentHashMap Operations
        System.out.println("\nConcurrentHashMap Safe Operations:");
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);

        // computeIfAbsent - thread-safe way to add if key doesn't exist
        Integer valueA = concurrentMap.computeIfAbsent("A", k -> 10);
        Integer valueC = concurrentMap.computeIfAbsent("C", k -> 30);
        System.out.println("computeIfAbsent results: A=" + valueA + ", C=" + valueC);

        // merge - thread-safe way to update values
        concurrentMap.merge("A", 5, Integer::sum); // A = 1 + 5 = 6
        concurrentMap.merge("D", 40, Integer::sum); // D = 40 (new key)
        System.out.println("After merge operations: " + concurrentMap);

        // Parallel Stream Considerations
        System.out.println("\nParallel Stream Considerations:");
        List<Integer> largeList = Stream.iterate(1, n -> n + 1)
                .limit(1000)
                .collect(Collectors.toList());

        // Sequential processing
        long startTime = System.nanoTime();
        long sequentialSum = largeList.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sequentialTime = System.nanoTime() - startTime;

        // Parallel processing
        startTime = System.nanoTime();
        long parallelSum = largeList.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parallelTime = System.nanoTime() - startTime;

        System.out.println("Sequential sum: " + sequentialSum + " (time: " + sequentialTime / 1000 + " μs)");
        System.out.println("Parallel sum: " + parallelSum + " (time: " + parallelTime / 1000 + " μs)");
        System.out.println("✅ Use parallel streams for CPU-intensive operations on large datasets");
        System.out.println("✅ Avoid parallel streams for I/O operations or small datasets");

        // Collection Modification During Streaming
        System.out.println("\nCollection Modification During Streaming:");
        List<Integer> modifiableList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        try {
            // This will throw ConcurrentModificationException
            modifiableList.stream()
                    .filter(n -> n % 2 == 0)
                    .forEach(n -> modifiableList.remove(n)); // Modifying source collection
        } catch (Exception e) {
            System.out.println("✅ Cannot modify source collection during streaming: " + e.getClass().getSimpleName());
        }

        // Safe way: collect results first, then modify
        List<Integer> toRemove = modifiableList.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        modifiableList.removeAll(toRemove);
        System.out.println("Safe modification result: " + modifiableList);
    }
}
