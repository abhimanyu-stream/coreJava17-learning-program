package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindMissingNumberInArray {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 5, 8);

        // Find the minimum and maximum values in the list
        int min = Collections.min(list);
        int max = Collections.max(list);

       List<Integer> sortedArray = list.stream().sorted(Comparator.reverseOrder()).toList();
       System.out.println(sortedArray);




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
                // .filter(num -> !list.contains(num))
                .boxed()
                .toList();

        System.out.println("Missing numbers: " + missingNumbersss);



             int[] ints = {1, 3, 4, 6, 7, 10};

        Set<Integer> present = Arrays.stream(ints)
                .boxed()
                .collect(Collectors.toSet());

        IntStream.rangeClosed(
                        Arrays.stream(ints).min().getAsInt(),
                        Arrays.stream(ints).max().getAsInt()
                )
                .filter(i -> !present.contains(i))
                .forEach(System.out::println);// print missing elements only


    }
}
