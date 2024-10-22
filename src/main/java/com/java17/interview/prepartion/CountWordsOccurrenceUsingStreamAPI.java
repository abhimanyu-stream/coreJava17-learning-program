package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class CountWordsOccurrenceUsingStreamAPI {

    public static void main(String[] args) {
        SpringApplication.run(CountWordsOccurrenceUsingStreamAPI.class, args);

        String str = "Hello World Hello Abhimanyu Abhimanyu Abhimanyu";



        String[] strArray = str.split(" ");
        System.out.println(Arrays.stream(strArray).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));


    }
}
