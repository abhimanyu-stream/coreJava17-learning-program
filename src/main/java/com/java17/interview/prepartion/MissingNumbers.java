package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingNumbers {

    public static void main(String[] args) {

        int[] input = {2, 6, 8, 15, 50, 15};

        Set<Integer> set = Arrays.stream(input)
                .boxed()
                .collect(Collectors.toSet());

        int min = Arrays.stream(input).min().orElseThrow();
        int max = Arrays.stream(input).max().orElseThrow();

        IntStream.rangeClosed(min, max)
                .filter(i -> !set.contains(i))
                .forEach(System.out::println);




    }
}
