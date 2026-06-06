package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingNumbers {

    public static void main(String[] args) {

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
