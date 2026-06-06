package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindMostRepeatedValueInArrayUsingStreamAPI {

    public static List<Integer> findMostFrequentWithLimit(Integer[] arr, int n) {
        return Arrays.stream(arr)
                // Function.identity equals to i -> i
                // with Integer do not use LinkedHashMap::new
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet()
                .stream().filter(f ->f.getValue() > 1L)
                //.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .limit(n)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 13, 4, 21, 13, 27, 2, 59, 59, 59,59, 34,34,34,34,34,34,34};

       int x = Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
                .stream().filter(f ->f.getValue() > 1L)
                .map(m->m.getKey()).findFirst().get();
       System.out.println("findFirst : " + x);

        Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
                .stream().filter(f -> f.getValue() > 1L)
                .map(m -> m.getKey()).toList();

        List<Integer> mostFrequentWithLimit = findMostFrequentWithLimit(arr, 3); // Get the top 2 most frequent elements
        System.out.println("mostFrequentWithLimit "+ mostFrequentWithLimit); // Output: [59, 13]
    }
}
