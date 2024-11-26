package com.java17.interview.prepartion;

public class FindMinimumClostestPair {
    public static void main(String[] args) {
        int[] arr1 = {1, 4, 5, 7};
        int[] arr2 = {10, 20, 30, 40};
        int x = 32;

        findClosestPair(arr1, arr2, x);
    }

    public static void findClosestPair(int[] arr1, int[] arr2, int x) {
        int m = arr1.length;
        int n = arr2.length;

        // Initialize variables
        int i = 0, j = n - 1; // Start with the first element of arr1 and the last element of arr2
        int closestPair1 = 0, closestPair2 = 0;
        int closestDiff = Integer.MAX_VALUE;

        // Traverse the arrays
        while (i < m && j >= 0) {
            int sum = arr1[i] + arr2[j];
            int diff = Math.abs(sum - x);

            // Update the closest pair if needed
            if (diff < closestDiff) {
                closestDiff = diff;
                closestPair1 = arr1[i];
                closestPair2 = arr2[j];
            }

            // Move pointers to try to minimize the difference
            if (sum < x) {
                i++;
            } else {
                j--;
            }
        }

        System.out.println("The closest pair is: " + closestPair1 + " and " + closestPair2);
    }
}
/***
 *
 *
 *
 * //Find the closest pair from two sorted arrays
 *
 * 		//	Given two arrays arr1[0…m-1] and arr2[0..n-1], and a number x, the task is to find the pair arr1[i] + arr2[j] such that absolute value of (arr1[i] + arr2[j] – x) is minimum.
 * //Example:
 * //Input:  arr1[] = {1, 4, 5, 7};
 *            // arr2[] = {10, 20, 30, 40};
 *            // x = 32
 * //Output:  1 and 30
 * //Input:  arr1[] = {1, 4, 5, 7};
 *            // arr2[] = {10, 20, 30, 40};
 *            // x = 50
 * //Output:  7 and 40 java program
 *
 *
 *
 *
 *
 * Explanation:
 * Initialization:
 * Start with the first element of arr1 (i = 0) and the last element of arr2 (j = n - 1).
 * Iterate through arrays:
 * Calculate the sum of arr1[i] and arr2[j].
 * Compute the absolute difference from
 * 𝑥
 * x.
 * If the difference is smaller than the previously recorded difference, update the closest pair and the minimum difference.
 * Pointer Movement:
 * If the sum is less than
 * 𝑥
 * x, increment
 * 𝑖
 * i to increase the sum.
 * If the sum is greater than
 * 𝑥
 * x, decrement
 * 𝑗
 * j to decrease the sum.
 * Output the result:
 * After the loop, the closest pair is printed.
 */