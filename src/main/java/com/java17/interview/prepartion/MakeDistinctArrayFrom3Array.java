package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MakeDistinctArrayFrom3Array {
    public static void main(String[] args) {


        int[] A = {2, 5, 10, 30};
        int[] B = {5, 20, 34};
        int[] C = {5, 13, 19};

        // Convert B and C to Sets for O(1) lookup
        Set<Integer> setB = Arrays.stream(B).boxed().collect(Collectors.toSet());
        Set<Integer> setC = Arrays.stream(C).boxed().collect(Collectors.toSet());

        // Find common elements
        List<Integer> common = Arrays.stream(A)
                .filter(setB::contains)
                .filter(setC::contains)
                .distinct()
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        System.out.println(common); // Output: [5]

        Consumer<List<Integer>> listConsumer = (l)->{
            l.forEach(System.out::println);
        };
        listConsumer.accept(common);

        List<Integer> distinctSortedList = Stream.of(A, B, C)  // Stream<int[]>
                .flatMapToInt(Arrays::stream)                 // IntStream of all elements
                .distinct()                                   // Remove duplicates
                .sorted()                                     // Sort them
                .boxed()                                      // Convert int to Integer
                .collect(Collectors.toList());                // Collect into List

        System.out.println(distinctSortedList); // Output: [2, 5, 10, 13, 19, 20, 30, 34]


    }
    /**
     * Explanation:
     * Stream.of(A, B, C) → creates a stream of the 3 arrays.
     *
     * .flatMapToInt(Arrays::stream) → flattens them into one IntStream.
     *
     * .distinct() → removes duplicate values.
     *
     * .sorted() → sorts all values.
     *
     * .boxed() → converts int to Integer for the List.
     *
     * .collect(Collectors.toList()) → collects into List<Integer>
     *
     */

}
