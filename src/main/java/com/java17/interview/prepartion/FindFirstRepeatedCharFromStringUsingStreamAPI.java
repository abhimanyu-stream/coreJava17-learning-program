package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFirstRepeatedCharFromStringUsingStreamAPI {
    public static void main(String[] args) {
// LinkedHashMap::new it preserves insertion order of incoming data
        String str = "JavaJavaEE";
        //System.out.println(Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        List<Character> c =  str.chars()
                .mapToObj( i->Character.toLowerCase((char) i))
                        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new ,Collectors.counting()))
                        .entrySet().stream()
                        .filter(entry->entry.getValue() > 1L)
                        .map(entry-> entry.getKey())//Map.Entry::getKey
                        .toList();


        System.out.println(c);


        String str2="collector";

        //1.  convert into map
        Map<String, Long> map = Arrays.stream(str2.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap ::new, Collectors.counting()));
        System.out.println(map);
        List<String> listChar = map.entrySet().stream().filter(f->f.getValue() >1L).map(Map.Entry :: getKey).toList();
        System.out.println("Duplicate elements in str "+listChar);





    }
}
