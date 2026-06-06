package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;

public class FindHighestSecondHighestWithoutStream {
    public static void main(String[] args) {

        int[] arr = {120, 456, 789, 90};


        if (arr.length < 2) {
            System.out.println("Array must contain at least two elements");
            return;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {

            if (num > largest) {
                secondLargest = largest;   // demote current largest
                largest = num;             // promote new largest
            }
            else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }




        System.out.println("Largest: " + largest);
        System.out.println("Second Largest: " + secondLargest);

        /**
         * Now let’s see a different example:
         *
         * {100, 50, 80}
         *
         * Iteration:
         *
         * 100 → largest = 100
         *
         * 50 → secondLargest = 50
         *
         * 80 → not > largest
         * 80 > 50 → yes
         * 80 != 100 → yes
         * secondLargest = 80
         *
         * Perfect.
         *
         * So the full logic meaning of that condition is:
         *
         * “Among numbers that are not the largest, promote the biggest one to second place.”
         */

        Integer min = Arrays.stream(arr).boxed().min(Comparator.naturalOrder()).get();
        Integer max = Arrays.stream(arr).boxed().max(Comparator.naturalOrder()).get();
        Integer secondLargesttt = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
    }
}
