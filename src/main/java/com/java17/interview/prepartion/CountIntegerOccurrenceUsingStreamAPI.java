package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class CountIntegerOccurrenceUsingStreamAPI {

    public static void main(String[] args) {
        SpringApplication.run(CountIntegerOccurrenceUsingStreamAPI.class, args);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,1,1,2,6,6,6,6,6,6);
        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
}
