package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIUsageOfMapAndSorting {


    public static void main(String[] args) {


        List<Integer> listOfInt = Arrays.asList(2, 7, -2, 5);

        // find min integer

        Integer result = listOfInt.stream().sorted().findFirst().get();
        System.out.println(result);
        List<Integer> results = listOfInt.stream().map(i-> i+ 5).collect(Collectors.toList());
        System.out.println(results);

    }
}
