package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIUsageOfMapAndSorting {


    public static void main(String[] args) {


        List<Integer> listOfInt = Arrays.asList(2, 7, -2, 5);

        // find min integer

        Integer minResult = listOfInt.stream().sorted().findFirst().get();
        System.out.println(minResult);
        List<Integer> plus5results = listOfInt.stream().map(i-> i+ 5).collect(Collectors.toList());
        System.out.println(plus5results);

        List<Integer> decendingOrder = listOfInt.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println(decendingOrder);

        listOfInt.sort(Comparator.reverseOrder());

        List<Integer> increasingOrder = listOfInt.stream().sorted(Comparator.naturalOrder()).toList();
        System.out.println(increasingOrder);

    }
}
