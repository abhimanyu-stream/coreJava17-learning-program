package com.java17.interview.prepartion;

import java.util.Arrays;

public class MergeTwoGivenArrayAfterSortingInNewArray {


    public static void main(String[] args) {


        int[] arr1 = new int[]{33, 65, 0, -2, 1};
        int[] arr2 = new int[]{29, 52, 43};
        int size = arr1.length + arr2.length;
        int[] mergedArray = new int[size];


        sortByBubble(arr1);
        for(int i = 0; i <arr1.length; i++){
            System.out.println(arr1[i]);
        }
        System.out.println("------------------------");
        sortByBubble(arr2);
        for(int i = 0; i <arr2.length ; i++){
            System.out.println(arr2[i]);
        }
        System.out.println("------------------------");
        mergeTwoArray(arr1, arr2, mergedArray);
        for(int i = 0; i < mergedArray.length; i++){
            System.out.println(mergedArray[i]);
        }
        System.out.println("------------------------");
        System.out.println(Arrays.toString(mergedArray));




    }

    private static void mergeTwoArray(int[] arr1, int[] arr2, int[] mergedArray) {
        int i = 0, j = 0, k = 0;

        // Merge arrays while there are elements in both
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        // If elements remain in arr1, add them to mergedArray
        while (i < arr1.length) {
            mergedArray[k++] = arr1[i++];
        }

        // If elements remain in arr2, add them to mergedArray
        while (j < arr2.length) {
            mergedArray[k++] = arr2[j++];
        }
    }

    private static void sortByBubble(int[] arr) {

        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j< arr.length - i - 1; j++){

                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j +  1];
                    arr[j + 1] =  temp;
                }

            }
        }


    }
    /**
     * Issues:
     * Incorrect Inner Loop Condition:
     *
     * for(int j = 0; j< arr.length; i++) should be for(int j = 0; j < arr.length - i - 1; j++).
     * The condition should ensure that the inner loop does not go out of bounds. j + 1 will cause an ArrayIndexOutOfBoundsException when j reaches arr.length - 1.
     * Wrong Index in Comparison:
     *
     * if(arr[i] > arr[j + 1]) should be if(arr[j] > arr[j + 1]).
     * In bubble sort, you compare adjacent elements, not arr[i] with arr[j + 1].
     * Wrong Loop Variable Increment:
     *
     * i++ is used instead of j++ in the inner loop. This causes an infinite loop.
     */

}
