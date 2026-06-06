package com.java17.interview.prepartion;

public class StringPermutations {

    public static void main(String[] args) {
        String input = "abc";
        System.out.println("Permutations of " + input + ":");
        findPermutations(input, "");
    }

    // Recursive method to find all permutations of a string
    public static void findPermutations(String str, String prefix) {
        // Base case: if the input string is empty, print the prefix
        if (str.isEmpty()) {
            System.out.println(prefix);
            return;
        }

        // Recursive case: for each character in the input string
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i); // Current character
            String remaining = str.substring(0, i) + str.substring(i + 1); // Remaining string
            findPermutations(remaining, prefix + current); // Recur with updated prefix
        }
    }
}
