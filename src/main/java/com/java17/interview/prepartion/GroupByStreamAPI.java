package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupByStreamAPI {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Oracle", "Java", "Elasticsearch", "Elasticsearch");


        Map<Character, Long> collect = names.stream().map(m -> m.charAt(0)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);


        Map<Character, Long> firstCharCounting = names.stream().collect(Collectors.groupingBy(n->n.charAt(0), Collectors.counting()));
        System.out.println("firstCharCounting " + firstCharCounting);
        Map<String, Long> nameCounting = names.stream().collect(Collectors.groupingBy(n->n, Collectors.counting()));
        System.out.println(nameCounting);


    }


}
