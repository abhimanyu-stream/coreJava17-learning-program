package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class CountCharacterOccurrenceUsingStreamAPI {

    public static void main(String[] args) {
        SpringApplication.run(CountCharacterOccurrenceUsingStreamAPI.class, args);



        String str = "HelloworldJavaAAAApplication worlds";
        System.out.println(Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));// Map


        List<String> listOfRepeatedChar = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() > 1L).map(Map.Entry::getKey).collect(Collectors.toList());

        System.out.println(listOfRepeatedChar);

        //1.  convert into map
        Map<String, Long> map = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));// LinkedHashMap it preserve insertion order
        System.out.println(map);
        List<String> listChar = map.entrySet().stream().filter(f->f.getValue() >1L).map(Map.Entry :: getKey).toList();
        System.out.println("Duplicate elements in str "+listChar);

        // LinkedHashMap::new it preserves insertion order of incoming data
        String str2 = "JavaJavaEE";
        //System.out.println(Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        List<Character> c =  str2.chars()
                .mapToObj( i->Character.toLowerCase((char) i))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new ,Collectors.counting()))
                .entrySet().stream()
                .filter(entry->entry.getValue() > 1L)
                .map(entry-> entry.getKey())//Map.Entry::getKey
                .toList();


        System.out.println(c);


        String str3="collector";

        //1.  convert into map
        Map<String, Long> map3 = Arrays.stream(str3.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap ::new, Collectors.counting()));
        System.out.println(map);
        List<String> listChar3 = map3.entrySet().stream().filter(f->f.getValue() >1L).map(Map.Entry :: getKey).toList();
        System.out.println("Duplicate elements in str "+listChar3);




    }


}
