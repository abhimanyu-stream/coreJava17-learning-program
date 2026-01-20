package com.java17.interview.prepartion;

import java.util.Arrays;

public class AlphabeticalSort {
    public static void main(String[] args) {

        String s = "Abhimanyu";

        s.chars()                         // IntStream of Unicode values
                .mapToObj(c -> (char) c)         // convert int → Character
                .sorted()                        // alphabetical (ASCII) order
                .forEach(System.out::println);     // print result


        Arrays.stream(s.split("")).sorted().forEach(System.out::print);
        s.toLowerCase()
                .chars()
                .sorted()
                .forEach(c -> System.out.print((char) c));
    }
}
