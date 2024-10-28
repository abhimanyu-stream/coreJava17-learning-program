package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicatesElementsAndConvertStringToMapUsingStreamAPI {


    public static void main(String[] args) {


        String str="collector";

        //1.  convert into map
        Map<String, Long> map = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap ::new, Collectors.counting()));
        System.out.println(map);
        List<String> listChar = map.entrySet().stream().filter(f->f.getValue() >1L).map(Map.Entry :: getKey).toList();
        System.out.println("Duplicate elements in str "+listChar);


        List<Integer> intList =  Arrays.asList(1,2,3,1,4,3,6);
        // Find duplicates

        intList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(m->m.getValue() > 1L).map(Map.Entry :: getKey).toList();

        System.out.println( "Duplicate elements in intList "+ intList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(m->m.getValue()>1L).map(Map.Entry :: getKey).toList());



        int[] intArray = {2,2,2,3,0};

        // Remove duplicates using streams
        int[] uniqueArray = Arrays.stream(intArray)
                .distinct()
                .toArray();
        // Print the result
        System.out.println("Array with duplicates removed: " + Arrays.toString(uniqueArray));



        // Collect duplicates into a list
        List<Integer> duplicates = Arrays.stream(intArray)
                .boxed() // Convert int to Integer
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1) // Only keep elements with count > 1
                .map(Map.Entry :: getKey).toList();

        // Print the result
        System.out.println("List of duplicates: " + duplicates);



    }
}
