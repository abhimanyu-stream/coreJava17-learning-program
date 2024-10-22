package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;

public class LargestElementUsingStreamAPI {


    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(5, 77, 8, 3, 1);
        int largest = intList.stream().max((a, b) -> a.compareTo(b)).get();
        int largestt = intList.stream().max((a, b) -> a - b).get();
        System.out.println(largest);
        System.out.println(largestt);
        int smallest = intList.stream().max((a, b) -> b - a).get();
        System.out.println(smallest);



        int[] arr = {10, 324, 45, 90, 9808};
        int max = Arrays.stream(arr).max().getAsInt();
        System.out.println("Largest element in the array: " + max);


        String originalString = "Hello, World!";
        String reversedString = reverse(originalString);
        System.out.println("Original String: " + originalString);
        System.out.println("Reversed String: " + reversedString);
    }

    public static String reverse(String s) {
        if (s == null || s.isEmpty()) {
            return s; // Return the string itself if it's null or empty
        }

        byte[] byteArray = s.getBytes(); // Convert the string to a byte array

        // Swap bytes from both ends
        for (int start = 0, end = byteArray.length - 1; start < end; start++, end--) {
            // bubble concept
            byte temp = byteArray[start];
            byteArray[start] = byteArray[end];
            byteArray[end] = temp;
        }

        // Convert the byte array back to a string
        return new String(byteArray);
    }
}
