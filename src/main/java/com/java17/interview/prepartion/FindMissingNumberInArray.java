package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindMissingNumberInArray {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 5, 8);

        // Find the minimum and maximum values in the list
        int min = Collections.min(list);
        int max = Collections.max(list);

        // Generate a complete range of numbers from min to max
        List<Integer> completeRange = IntStream.rangeClosed(min, max)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("completeRange numbers: " + completeRange);
        // Find the missing numbers
        List<Integer> missingNumbers = completeRange.stream()
                .filter(num -> !list.contains(num))
                .collect(Collectors.toList());

        System.out.println("Missing numbers: " + missingNumbers);

    }
}
