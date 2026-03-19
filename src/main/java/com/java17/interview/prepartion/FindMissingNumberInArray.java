package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.IntStream;

public class FindMissingNumberInArray {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 5, 8);

        // Find the minimum and maximum values in the list
        int min = Collections.min(list);
        int max = Collections.max(list);

       // Integer maxxx = list.stream().max(Comparator.reverseOrder()).get();
        Integer minnn = list.stream().min(Comparator.naturalOrder()).get();

        Integer maxx = list.stream().max(Comparator.naturalOrder()).get();

        // Generate a complete range of numbers from min to max
        List<Integer> completeRange = IntStream.rangeClosed(min, max)
                .boxed()
                .toList();
        System.out.println("completeRange numbers: " + completeRange);
        // Find the missing numbers
        List<Integer> missingNumbers = completeRange.stream()
                .filter(num -> !list.contains(num))

                .toList();

        System.out.println("Missing numbers: " + missingNumbers);


        // Convert List to Set for fast lookup
        Set<Integer> numberSet = new HashSet<>(list);
        // Find missing numbers using Set
        List<Integer> missingNumbersss = IntStream.rangeClosed(min, max)
                .filter(num -> !numberSet.contains(num))
                .boxed()
                .toList();

        System.out.println("Missing numbers: " + missingNumbersss);


    }
}
