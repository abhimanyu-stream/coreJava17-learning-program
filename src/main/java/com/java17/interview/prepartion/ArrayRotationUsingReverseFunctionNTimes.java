package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArrayRotationUsingReverseFunctionNTimes {

    public static void main(String[] args) {

        SpringApplication.run(ArrayRotationUsingReverseFunctionNTimes.class, args);
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        rotateArray(arr, 3);


        for(int i = 0; i < arr.length ;i++) {
            System.out.println(arr[i] + "");
        }

    }

    public static void rotateArray(int[] arr, int k) {
        int n = arr.length;
        reverse(arr, 0, n - k - 1);
        reverse(arr, n - k, n - 1);
        reverse(arr, 0, n - 1);
    }

    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
