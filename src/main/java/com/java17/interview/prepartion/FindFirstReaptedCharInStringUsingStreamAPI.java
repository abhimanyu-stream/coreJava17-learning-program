package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFirstReaptedCharInStringUsingStreamAPI {

    public static void main(String[] args) {
       // String str ="Abhimanyu is a Java Programmer";
        // first repeated char
        String str ="Abhimanyu is a Java Programmer";
        // first  repeated char
        char firstRepeatedChar = str.chars()
                .mapToObj( i->Character.toLowerCase((char) i))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1L).
                map(Map.Entry ::getKey)
                .findFirst().get();

        System.out.println(firstRepeatedChar);



        System.out.println(Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1L)
                .map(m->new StringBuffer().append(m.getKey()).append(m.getValue())).toList());



        Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1L)
                .map(m->new StringBuffer().append(m.getKey()).append(m.getValue())).forEachOrdered(System.out::print);





    }



}
