package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LongestStringBLength {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "GFG", "Geeks", "for", "GeeksQuiz", "GeeksforGeeks"
        );

        String longest =
                words.stream()
                        .max(Comparator.comparingInt(String::length))
                        .orElse(null);

        System.out.println(longest);

    }

}
