package com.java17.interview.prepartion;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCountPrintItTheNumberOfTimeItIsPresent {

    public static void main(String[] args) {
        String str = "java is a programming language";

        // Count occurrences of each character
        Map<Character, Long> charCountMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println(charCountMap);

        // Construct the output string
        StringBuilder output = new StringBuilder();
        charCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by character[ key] [ Higher value are placed at first place and so on]
                .forEach(entry -> {
                    char c = entry.getKey();// key
                    long counting = entry.getValue();// value
                    for (int i = 0; i < counting; i++) {
                        output.append(c);
                    }
                });

        System.out.println("output "+output); // Print the final output



    }
}
