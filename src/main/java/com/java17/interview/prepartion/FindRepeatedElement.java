package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindRepeatedElement {

    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1,2,3,4,5,5,6,6,6);

        List<Integer> allReapeatedElement = integerList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
                .stream().filter(f -> f.getValue() > 1L).map(m -> m.getKey()).collect(Collectors.toList());
        System.out.println(allReapeatedElement);
    }

}
