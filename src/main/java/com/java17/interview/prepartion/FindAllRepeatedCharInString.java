package com.java17.interview.prepartion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindAllRepeatedCharInString {

    public static void main(String[] args) {

        String str = "dockerkubernetesjenkinsqws   argocd";
        List<Character> allReapeatedChars = str.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
                .entrySet().stream().filter(f -> f.getValue() > 1L).map(m -> m.getKey()).collect(Collectors.toList());
        System.out.println(allReapeatedChars);


        Map<Character, Long> mapOfReapeatedCharWithCounting = str.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()));
        System.out.println(mapOfReapeatedCharWithCounting);


    }

}
