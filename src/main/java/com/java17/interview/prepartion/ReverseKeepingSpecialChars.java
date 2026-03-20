package com.java17.interview.prepartion;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ReverseKeepingSpecialChars {

    public static void main(String[] args) {
        String input = "abcd@efg#hi";

        // output: ihgf@edc#ba

        /**
         * But your expected output is:
         * ihgf@edc#ba
         *
         * 👉 That means:
         *
         * Only alphabets are reversed
         *
         * Special characters (@, #) stay at same position
         *
         * ✅ Correct Approach (Two Pointer Technique)
         * 💡 Logic:
         *
         * Convert string → char array
         *
         * Use two pointers (left, right)
         *
         * Swap only when both are letters
         *
         * Skip special characters
         */
        char[] arr = input.toCharArray();

        int left = 0, right = arr.length - 1;

        while (left < right) {

            if (!Character.isLetter(arr[left])) {
                left++;
            } else if (!Character.isLetter(arr[right])) {
                right--;
            } else {
                // swap
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        System.out.println(new String(arr));



        //String input = "abcd@efg#hi";
/**
 * Stream-based solution (Interview tricky, less optimal)
 *
 * Streams are not ideal here because:
 *
 * You need index control + conditional swaps
 *
 * Streams are better for transformation, not in-place mutation
 *
 * Still, if interviewer insists, you can:
 *
 * Extract letters
 *
 * Reverse them
 *
 * Reinsert
 */
        List<Character> letters = input.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .collect(Collectors.toList());

        Collections.reverse(letters);

        AtomicInteger index = new AtomicInteger(0);

        String result = input.chars()
                .mapToObj(c -> {
                    char ch = (char) c;
                    if (Character.isLetter(ch)) {
                        return String.valueOf(letters.get(index.getAndIncrement()));
                    } else {
                        return String.valueOf(ch);
                    }
                })
                .collect(Collectors.joining());

        System.out.println(result);
    }
}
