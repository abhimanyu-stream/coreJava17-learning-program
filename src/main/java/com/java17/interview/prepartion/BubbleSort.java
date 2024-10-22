package com.java17.interview.prepartion;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 12, 1, 6};

        System.out.println("Before sorting:");
        printArray(arr);

        bubbleSortw(arr);

        System.out.println("After sorting:");
        printArray(arr);
    }

    public static void bubbleSort(int[] arr) {
        // Pointing to same index in both loop
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {// ascending order maintain
                    // swapping using temp
                    // it can using bits shift and substract approach
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

        public static void bubbleSortw(int[] array){
            // Pointing to same index in both loop
            int n = array.length;
            int indexOfLastElement = n - 1;
            for (int i = 0; i < indexOfLastElement; i++) {
                for (int j = 0; j < indexOfLastElement - i; j++) {
                    if (array[j] > array[j + 1]) {
                        // swapping using temp
                        // it can using bits shift and substract approach
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }



    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
