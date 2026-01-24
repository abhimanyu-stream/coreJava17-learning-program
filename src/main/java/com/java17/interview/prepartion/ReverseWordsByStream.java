package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class ReverseWordsByStream {
    public static void main(String[] args) {
        String sentence = "Java is powerful";

        String result = Arrays.stream(sentence.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return String.join(" ", list);
                        }
                ));

        System.out.println(result);
    }
}
