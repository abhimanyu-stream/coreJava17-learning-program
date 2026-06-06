package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindOddEvenUsingPartitioningBy {

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(3,7,1,2,10,20);

        List<Integer> evenList = ints.stream().filter(f -> f % 2 ==0).collect(Collectors.toList());
        List<Integer> oddList = ints.stream().filter(f -> f % 2 !=0).collect(Collectors.toList());

        System.out.println(evenList);
        System.out.println(oddList);


        Map<Boolean, List<Integer>> partitioned = ints.stream().collect(Collectors.partitioningBy(num -> num % 2 == 0));

        List<Integer> evens = partitioned.get(true);
        List<Integer> odds = partitioned.get(false);

        System.out.println("Even numbers: " + evens);
        System.out.println("Odd numbers: " + odds);
    }
}
