package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicatesElementsAndConvertStringToMapUsingStreamAPI {


    public static void main(String[] args) {


        String str="collector";

        // convert into map


        Map<String, Long> map = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        List<String> listChar = map.entrySet().stream().filter(f->f.getValue() >1L).map(m->m.getKey()).collect(Collectors.toList());
        System.out.println("Duplicate elements in str "+listChar);
        List<Integer> intList =  Arrays.asList(1,2,3,1,4,3,6);
        // Find duplicates

        intList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(m->m.getValue()>1L).map(m->m.getKey()).collect(Collectors.toList());

        System.out.println( "Duplicate elements in intList "+ intList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(m->m.getValue()>1L).map(m->m.getKey()).collect(Collectors.toList()));


    }
}
