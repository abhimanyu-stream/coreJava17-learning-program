package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamPeekMethod {

    public static void main(String[] args) {


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> result = numbers.stream()
                .peek(num -> System.out.println("Original: " + num)) // Log original numbers
                .filter(num -> num % 2 == 0) // Filter even numbers
                .peek(num -> System.out.println("After filter: " + num)) // Log after filtering
                .map(num -> num * 2) // Double each even number
                .peek(num -> System.out.println("After map: " + num)) // Log after mapping
                .toList();

        System.out.println("Result: " + result);






        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        names.stream()
                .peek(name -> System.out.println("Processing: " + name))
                .filter(name -> name.startsWith("A"))
                .peek(name -> System.out.println("Filtered: " + name))
                .forEach(System.out::println);

        //Example 2: Using peek to Track Elements Processed
        //Here, we demonstrate how peek can be helpful in debugging when multiple operations are applied in sequence.
    }

}
/**
 * In Java 8, the peek method in streams is an intermediate operation that allows you to perform additional actions on each element as it flows through the stream. It's typically used for debugging or logging, as it doesn't alter the elements but provides a way to "peek" at them.
 *
 * Here's an example of how peek can be used in a Java Stream:
 *
 * Example 1: Using peek for Logging
 * Suppose we have a list of numbers and want to filter even numbers, double them, and collect the result into a list. We'll use peek to log each step.
 *
 *
 * In Java 8, the peek method in streams is an intermediate operation that allows you to perform additional actions on each element as it flows through the stream. It's typically used for debugging or logging, as it doesn't alter the elements but provides a way to "peek" at them.
 *
 * Here's an example of how peek can be used in a Java Stream:
 *
 * Example 1: Using peek for Logging
 * Suppose we have a list of numbers and want to filter even numbers, double them, and collect the result into a list. We'll use peek to log each step.
 *
 *
 * */