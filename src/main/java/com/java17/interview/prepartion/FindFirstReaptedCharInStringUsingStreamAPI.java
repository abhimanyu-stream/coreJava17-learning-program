package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFirstReaptedCharInStringUsingStreamAPI {

    public static void main(String[] args) {
       // String str ="Abhimanyu is a Java Programmer";
        // first repeated char
        String str ="Abhimanyu is a Java Programmer";
        // first  repeated char
        char c = str.chars()
                .mapToObj( i->Character.toLowerCase(Character.valueOf((char)i)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1L).
                map(entry-> entry.getKey())
                .findFirst().get();
        System.out.println(c);





    }



}
