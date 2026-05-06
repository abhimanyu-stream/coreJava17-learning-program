package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongestStringBLength {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "GFG", "Geeks", "for", "GeeksQuiz", "GeeksforGeeks"
        );

        String longest =
                words.stream()
                        .max(Comparator.comparingInt(String::length))
                        .orElse(null);

        System.out.println(longest);

        Map<Integer, List<String>> result1 =
                words.stream()
                        .collect(Collectors.groupingBy(String::length));

        System.out.println(result1);

        Map<String, Integer> result2 =
                words.stream()
                        .collect(Collectors.toMap(
                                Function.identity(),
                                String::length
                        ));

        System.out.println(result2);
        String longest2 = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

        System.out.println("Longest: " + longest2);


        List<String> list = words.stream()
                .sorted(Comparator.comparingInt(String::length)).toList();


        /**
         *
         */

        /**
         * Interview Tip
         * Collectors.toList() → mutable
         * .toList() → immutable
         */

        /**
         * Interview Insight (Important)
         * Use Case	Method
         * Key → List of values	groupingBy()
         * Key → Single value	toMap()
         * Need aggregation	groupingBy(..., downstream)
         */


    }

}
