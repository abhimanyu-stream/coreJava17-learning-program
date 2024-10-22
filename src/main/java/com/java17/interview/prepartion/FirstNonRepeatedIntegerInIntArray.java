package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedIntegerInIntArray {

    public static void main(String[] args) {


        int[] intArray = { 2, 6, 7, 8, 9, 8, 9, 2};

        Map.Entry<Integer, Long> map =  Arrays.stream(intArray).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() == 1L).findFirst().get();
        int x = map.getKey();
        System.out.println(x);


    }
}
