package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;

public class AlphabeticalSort {
    public static void main(String[] args) {

        String s = "Abhimanyu";

        s.toLowerCase().chars()                         // IntStream of Unicode values
                .mapToObj(c -> (char) c)         // convert int → Character
                //.sorted()                        // alphabetical (ASCII) order
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);     // print result


        Arrays.stream(s.toLowerCase().split("")).sorted().forEach(System.out::print);
        System.out.println("--------------------------------------------------");
        s.toLowerCase()
                .chars()
                .sorted()
                .forEach(c -> System.out.print((char) c));
    }
}
