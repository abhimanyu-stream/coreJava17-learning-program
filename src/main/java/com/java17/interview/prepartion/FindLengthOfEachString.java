package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindLengthOfEachString {
    public static void main(String[] args) {
        

        List<String> list = Arrays.asList("apple", "mango", "fruits");

        List<Integer> lenghtOfString = list.stream().
        map(String::length)
        .collect(Collectors.toList());
        System.out.println(lenghtOfString);


      int total =   list.stream().
        map(String::length).reduce(0, Integer::sum);
            System.out.println(total);

    }

}
