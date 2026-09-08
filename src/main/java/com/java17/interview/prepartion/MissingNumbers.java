package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingNumbers {

    public static void main(String[] args) {

        int[] input = {2, 6, 8, 15, 50, 15};// primitive []

        Set<Integer> set = Arrays.stream(input)//<------- main point
                .boxed()
                .collect(Collectors.toSet());

       // int min = Arrays.stream(input).min().orElseThrow(); for primitive array
        //int max = Arrays.stream(input).max().orElseThrow();

        int minInArray = Arrays.stream(input).boxed().min(Comparator.naturalOrder()).orElseThrow();
        int maxInArray = Arrays.stream(input).boxed().max(Comparator.naturalOrder()).orElseThrow();
        //Arrays.stream(input).min(Comparator.naturalOrder()).orElseThrow()
        System.out.println("minInArray "+minInArray);
         System.out.println("maxInArray "+maxInArray);

        
      

        IntStream.rangeClosed(minInArray, maxInArray)
                .filter(i -> !set.contains(i))
                .forEach(System.out::println);
        
        
        /*
         * For int[], use this instead

If your array is primitive:

int[] input = {10, 5, 20, 3, 15};

int min = Arrays.stream(input)
        .min()
        .orElseThrow();

System.out.println(min);

Here you don't need Comparator.naturalOrder() because Arrays.stream(int[]) produces an IntStream.

Interview shortcut
Array	Minimum
int[]	Arrays.stream(input).min().orElseThrow()
Integer[]	Arrays.stream(input).min(Comparator.naturalOrder()).orElseThrow()
String[]	Arrays.stream(input).min(Comparator.naturalOrder()).orElseThrow()


         * */




    }
}
