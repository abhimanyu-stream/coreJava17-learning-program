package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicateCharsFromStringUsingStreamAPI {

    public static void main(String[] args) {

        String str = "better butter";
        // print dulicates only


        System.out.println();
        Map<String, Long> map = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        List<String> listOfChar = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() > 1L).map(m->m.getKey()).collect(Collectors.toList());

        System.out.println(listOfChar);


    }


}
