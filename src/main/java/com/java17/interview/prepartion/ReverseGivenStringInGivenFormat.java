package com.java17.interview.prepartion;

public class ReverseGivenStringInGivenFormat {

    public static void main(String[] args) {

        //Input: "I am a java developer"
        //Output: "developer java a am I"

        String input ="I am a java developer";
        String output = reverseSentence(input);
        System.out.println(output);
    }

    public static String reverseSentence(String input) {
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
