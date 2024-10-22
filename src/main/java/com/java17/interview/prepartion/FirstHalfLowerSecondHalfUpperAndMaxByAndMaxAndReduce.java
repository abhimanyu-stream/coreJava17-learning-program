package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FirstHalfLowerSecondHalfUpperAndMaxByAndMaxAndReduce {

    public static void main(String[] args) {

        // Java program to find longest String from Strings in given array

        String[] strArray = {"java","spring boot","microservices","awscloudmicroservicesdevelopment","c"};

        String longest1 = Arrays.stream(strArray)
                .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2)
                .orElse("");

        System.out.println("Longest string: " + longest1);


        List<String> strings = Arrays.asList("cat", "rabbit", "horse", "goat", "rooster", "ooooooooooooooo");

        String longest2 = strings.stream()
                .max(Comparator.comparingInt(String::length))
                .get();

        System.out.println("Longest string: " + longest2);




        //List<String> strings = Arrays.asList("cat", "rabbit", "horse", "goat", "rooster", "ooooooooooooooo");

        String longest3 = strings.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(String::length)))
                .orElse("");

        System.out.println("Longest string: " + longest3);



        // convert first half of String in lowercase and in rest of half in uppercase

        String inputString = "This is a Java Program This is a Java Program";
        String convertedString = convertHalfCase(inputString);
        System.out.println("Converted string: " + convertedString);

    }

    public static String convertHalfCase(String input) {
        int halfLength = input.length() / 2;
        String firstHalf = input.substring(0, halfLength).toLowerCase();
        String secondHalf = input.substring(halfLength).toUpperCase();
        return firstHalf + secondHalf;
    }
}
