package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedIntegerInIntArray {// Find Distinct or Remove Duplicate

    public static void main(String[] args) {


        int[] intArray = { 2, 6, 7, 8, 9, 8, 9, 2};
        List<Integer> integerList = Arrays.asList(2, 6, 7, 8, 9, 8, 9, 2);


        List<Integer> collect = Arrays.stream(intArray).boxed().distinct().collect(Collectors.toList());
        System.out.println(collect);
        List<Integer> collect1 = integerList.stream().distinct().collect(Collectors.toList());
        System.out.println(collect1);



        Map.Entry<Integer, Long> map =  Arrays.stream(intArray).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() == 1L).findFirst().get();
        int x = map.getKey();
        System.out.println(x);
        Arrays.stream(intArray).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() == 1L).map(Map.Entry::getKey).toList();


        integerList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() == 1L).findFirst().get();




    }
}
