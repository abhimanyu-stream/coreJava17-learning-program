package com.java17.interview.prepartion;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterCountPrintItTheNumberOfTimeItIsPresent {

    public static void main(String[] args) {
        String str = "java Oracle Mysql";

        // Count occurrences of each character
        Map<Character, Long> charCountMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("charCountMap "+charCountMap);


        charCountMap.entrySet().stream().map(e->new StringBuffer().append(e.getKey()).append(e.getValue())).forEach(System.out :: print);



        // Construct the output string
        StringBuilder output = new StringBuilder();
        charCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by character[ key] [ Higher value are placed at first place and so on]
                .forEach(entry -> {
                    char c = entry.getKey();// key
                    long counting = entry.getValue();// value
                    /*for (int i = 0; i < counting; i++) {
                        output.append(c);
                    }*/
                    output.append(c).append(counting);
                });
        System.out.println();
        System.out.println("output "+output); // Print the final output
        System.out.println("output String "+new String(output));



    }
}
