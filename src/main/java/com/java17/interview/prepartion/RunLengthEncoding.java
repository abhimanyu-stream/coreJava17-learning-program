package com.java17.interview.prepartion;

import java.util.stream.Collectors;

public class RunLengthEncoding {
    public static void main(String[] args) {
        String str = "aabbbcccc";

        String result = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(e -> e.getKey() + String.valueOf(e.getValue()))
                .collect(Collectors.joining());

        System.out.println(result); // a2b3c4
    }
}
