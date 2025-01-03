package com.java17.interview.prepartion;


import java.util.Arrays;

public class RotateIntArrayNTimesEvenWhenNisLargerThanTheLengthOfTheArray {

    // Function to rotate the array n times
    public static void rotateArray(int[] arr, int n) {
        int len = arr.length;

        // Base condition: If the array is empty or no rotation needed
        if (len == 0 || n % len == 0) {
            return;
        }

        // Normalize n to handle cases where n > len or n < 0
        if(n > len || n < 0){
            n = n % len;
            if (n < 0) {
                //n = len + n;// note n is negative like -3 so n = len + (-3)= 2
                n += len; // Convert negative rotations to positive
            }
        }
       // otherwise n is less than len already, no need for Normalize, program will go as normal

        // Reverse the entire array
        reverse(arr, 0, len - 1);
        // Reverse the first n elements
        reverse(arr, 0, n - 1);
        // Reverse the remaining elements
        reverse(arr, n, len - 1);
    }

    // Helper function to reverse a portion of the array
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int n = 7; // Number of rotations

        System.out.println("Original Array: " + Arrays.toString(arr));

        rotateArray(arr, n);

        System.out.println("Rotated Array: " + Arrays.toString(arr));
    }
}
/**
 *
 * Yes, we can rotate an integer array n times even when n is larger than the length of the array. The key is to realize that rotating an array of length len by len or a multiple of len results in the array remaining unchanged. Hence, rotating the array n times is equivalent to rotating it n % len times.
 *
 * Example:
 * Let's take an array:
 *
 * plaintext
 * Copy code
 * [1, 2, 3, 4, 5]
 * Logic for Rotation:
 *
 * If n = 2, the array rotates to the right 2 times:
 * Rotate 1: [4, 5, 1, 2, 3]
 * Rotate 2: [3, 4, 5, 1, 2]
 *
 * If n = 7 (which is greater than len = 5), the actual number of rotations required is n % len = 7 % 5 = 2. So, the result is the same as rotating the array 2 times:
 * [3, 4, 5, 1, 2]
 *
 * Base Conditions:
 * If n == 0 or n % len == 0, the array remains unchanged.
 * Handle negative values of n: Convert -n to a positive equivalent by using len - (-n % len).
 * Java Program:
 * Here is the implementation:
 *
 * java
 * Copy code
 * import java.util.Arrays;
 *
 * public class ArrayRotation {
 *
 *     // Function to rotate the array n times
 *     public static void rotateArray(int[] arr, int n) {
 *         int len = arr.length;
 *
 *         // Base condition: If the array is empty or no rotation needed
 *         if (len == 0 || n % len == 0) {
 *             return;
 *         }
 *
 *         // Normalize n to handle cases where n > len or n < 0
 *         n = n % len;
 *         if (n < 0) {
 *             n += len; // Convert negative rotations to positive
 *         }
 *
 *         // Reverse the entire array
 *         reverse(arr, 0, len - 1);
 *         // Reverse the first n elements
 *         reverse(arr, 0, n - 1);
 *         // Reverse the remaining elements
 *         reverse(arr, n, len - 1);
 *     }
 *
 *     // Helper function to reverse a portion of the array
 *     private static void reverse(int[] arr, int start, int end) {
 *         while (start < end) {
 *             int temp = arr[start];
 *             arr[start] = arr[end];
 *             arr[end] = temp;
 *             start++;
 *             end--;
 *         }
 *     }
 *
 *     public static void main(String[] args) {
 *         int[] arr = {1, 2, 3, 4, 5};
 *         int n = 7; // Number of rotations
 *
 *         System.out.println("Original Array: " + Arrays.toString(arr));
 *
 *         rotateArray(arr, n);
 *
 *         System.out.println("Rotated Array: " + Arrays.toString(arr));
 *     }
 * }
 * Explanation:
 * Normalize n:
 * Calculate n % len to avoid unnecessary full rotations. Convert negative rotations to positive equivalents.
 * Reverse Technique:
 * Reverse the entire array.
 * Reverse the first n elements.
 * Reverse the remaining elements.
 * Base Condition:
 * If the array is empty or n % len == 0, no rotation is needed.
 * Example Walkthrough:
 * Input:
 *
 * plaintext
 * Copy code
 * Array = [1, 2, 3, 4, 5], n = 7
 * Steps:
 *
 * Normalize n: n = n % len = 7 % 5 = 2.
 * Reverse entire array: [5, 4, 3, 2, 1].
 * Reverse first n elements: [4, 5, 3, 2, 1].
 * Reverse the remaining elements: [3, 4, 5, 1, 2].
 * Output:
 *
 * plaintext
 * Copy code
 * Rotated Array = [3, 4, 5, 1, 2]
 * This approach runs in
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n) time and
 * 𝑂
 * (
 * 1
 * )
 * O(1) space since we modify the array in place.
 *
 *
 *
 *
 *
 *
 * You said:
 * run above program with n = -3
 * ChatGPT said:
 * ChatGPT
 * Let’s analyze the behavior of the program when n = -3. The program is designed to handle negative rotations by converting them into their positive equivalent. Negative rotation means rotating the array to the left, which is equivalent to rotating it to the right by len - (-n % len) times.
 *
 * Example Walkthrough:
 * Input:
 *
 * plaintext
 * Copy code
 * Array = [1, 2, 3, 4, 5], n = -3
 * Steps:
 *
 * Normalize n:
 *
 * Compute n % len: -3 % 5 = -3 (in Java, the % operator retains the sign of the dividend).
 * Convert n to positive: n = len + n = 5 + (-3) = 2.
 * Perform rotation using the normalized n:
 *
 * Rotate right by n = 2.
 * Steps of the Reverse Technique:
 *
 * Reverse the entire array:
 * plaintext
 * Copy code
 * [5, 4, 3, 2, 1]
 * Reverse the first n elements (n = 2):
 * plaintext
 * Copy code
 * [4, 5, 3, 2, 1]
 * Reverse the remaining elements:
 * plaintext
 * Copy code
 * [3, 4, 5, 1, 2]
 * Output:
 *
 * plaintext
 * Copy code
 * Rotated Array = [3, 4, 5, 1, 2]
 * Full Program Output:
 * If you run the program with n = -3, here’s what the console output will show:
 *
 * plaintext
 * Copy code
 * Original Array: [1, 2, 3, 4, 5]
 * Rotated Array: [3, 4, 5, 1, 2]
 * This is the expected result, as rotating the array 3 times to the left is the same as rotating it 2 times to the right.
 * */