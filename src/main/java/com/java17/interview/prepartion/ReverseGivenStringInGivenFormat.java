package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class ReverseGivenStringInGivenFormat {

    public static void main(String[] args) {

        //Input: "I am a java developer"
        //Output: "developer java a am I"

        String input ="I am a java developer";
        String output = reverseAsMirror(input);
        System.out.println(output);

        //Input:you? are How  world! Hello,
        String str23 = "Hello, world!  How are you?";

        String mirrorString = Arrays.stream(str23.split(" "))
                .collect(Collectors.collectingAndThen(
                                Collectors.toList(),
                                listt -> {
                                    Collections.reverse(listt);
                                    return String.join(" ", listt);

                                }
                        )
                );
        System.out.println(mirrorString);
    }

    public static String reverseAsMirror(String input) {
        // Split the input sentence by spaces
        String[] words = input.split(" ");

        // Initialize a StringBuilder to store the result
        StringBuilder reversedSentence = new StringBuilder();

        // Loop through the words array in reverse order
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }

        // Trim the extra space at the end and return the result
        return reversedSentence.toString().trim();
    }
}
