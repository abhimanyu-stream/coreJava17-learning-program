package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class CountCharacterOccurrenceUsingStreamAPI {

    public static void main(String[] args) {
        SpringApplication.run(CountCharacterOccurrenceUsingStreamAPI.class, args);



        String str = "HelloworldJavaAAAApplication worlds";
        System.out.println(Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));


        List<String> listOfRepeatedChar = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f->f.getValue() > 1L).map(Map.Entry::getKey).collect(Collectors.toList());

        System.out.println(listOfRepeatedChar);

    }


}
