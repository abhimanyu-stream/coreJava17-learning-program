package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindAllRepeatedCharInString {

    public static void main(String[] args) {

        String str = "dockerkubernetesjenkinsqws   argocd";
        List<Character> allRepeatedChars = str.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
                .entrySet().stream().filter(f -> f.getValue() > 1L).map(m -> m.getKey()).collect(Collectors.toList());
        System.out.println(allRepeatedChars);


        List<Character> allRepeatedChars2 = str.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
                .entrySet().stream().filter(f -> f.getValue() > 1L).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(allRepeatedChars2);



        Map<Character, Long> mapOfRepeatedCharWithCounting = str.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()));
        System.out.println(mapOfRepeatedCharWithCounting);



        LinkedHashMap<String, Long> collect = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println("collect "+ collect);




        System.out.println("\n(a.1) Find Duplicate Strings in Array - Multiple Approaches:");
        String[] strArray = {"hello", "java", "java", "python", "hello", "javascript", "java"};

        // Approach 1: Using Arrays.stream() + groupingBy + counting
        Set<String> stringDuplicates1 = Arrays.stream(strArray)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Approach 1 (Arrays.stream + groupingBy): " + stringDuplicates1);



        System.out.println("(a) Find Duplicate Elements - Multiple Approaches:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 6, 7, 8, 3);

        // Approach 1: Using groupingBy + counting (most common)
        Set<Integer> duplicates1 = numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Approach 1 (groupingBy): " + duplicates1);



        String strings = "better butter";
        // print duplicates only


        System.out.println();
        Map<String, Long> map = Arrays.stream(strings.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap:: new, Collectors.counting()));
        System.out.println(map);
        List<String> listOfChar = Arrays.stream(strings.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap:: new, Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() > 1L).map(Map.Entry::getKey).collect(Collectors.toList());

        System.out.println(listOfChar);

    }


}
