package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSumOfConsecutiveElements {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the size of the array
        //int n = scanner.nextInt();

        // Create an array of size n
       // int[] arr = new int[n];

        // Get the elements of the array
        //for (int i = 0; i < n; i++) {
            //arr[i] = scanner.nextInt();
       // }

        // Find the maximum sum of consecutive elements
        /*int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += arr[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
        }*/
        int[] arr2 = { 2, 3, 4 ,5};

        int sumOfAllElements = Arrays.stream(arr2).reduce(Integer::sum).getAsInt();
        System.out.println("sumOfAllElements "+sumOfAllElements);

        int[] arr = {-2, -3, -1, -5};

int maxSum = arr[0];
int currentSum = arr[0];

for (int i = 1; i < arr.length; i++) {
    currentSum = Math.max(arr[i], currentSum + arr[i]);
    maxSum = Math.max(maxSum, currentSum);
}

System.out.println("Maximum Subarray Sum = " + maxSum);
        }

        
    
}

