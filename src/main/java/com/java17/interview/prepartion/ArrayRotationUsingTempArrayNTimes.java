package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ArrayRotationUsingTempArrayNTimes {

    public static void main(String[] args) {

        SpringApplication.run(ArrayRotationUsingTempArrayNTimes.class, args);

        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        int arrayLength = arr.length;
        // Rotate 2 times
        int rotationTimes = 2;

        // Function call
        rotate(arr, rotationTimes, arrayLength);
        rotate2(arr, rotationTimes, arrayLength);

    }

    private static void rotate2(int[] arr, int rotationTimes, int arrayLength) {


        int[] temp = new int[arrayLength];
        int k = 0;
        for(int i = rotationTimes; i < arrayLength; i++){
            temp[k] = arr[i];
            k++;

        }
        for(int i = 0; i < rotationTimes ; i++){
            temp[k] = arr[i];
            k++;
        }
        System.out.println("--------solution----------");
        for(int i = 0; i < temp.length; i++){
            System.out.println(arr[i] + " ");
        }

    }

    public static void rotate(int[] arr, int rotationTimes, int arrayLength){

//Time complexity: O(N)
//Auxiliary Space: O(N)
        int [] temp = new int[arrayLength];

        int k = 0;

        for(int i = rotationTimes; i < arrayLength; i++){
            temp[k] =  arr[i];
            k++;

        }
        for(int i = 0; i < rotationTimes; i++){
            temp[k] = arr[i];
            k++;

        }

        System.arraycopy(temp, 0, arr, 0, arrayLength);
        for(int i = 0; i < arrayLength; i++){

            System.out.println(arr[i] + " ");
        }


    }
}
/***
 * After rotating d positions to the left, the first d elements become the last d elements of the array
 *
 * First store the elements from index d to N-1 into the temp array.
 * Then store the first d elements of the original array into the temp array.
 * Copy back the elements of the temp array into the original array
 * Illustration:
 *
 * Suppose the give array is arr[] = [1, 2, 3, 4, 5, 6, 7], d = 2.
 *
 * First Step:
 *     => Store the elements from 2nd index to the last.
 *     => temp[] = [3, 4, 5, 6, 7]
 *
 * Second Step:
 *     => Now store the first 2 elements into the temp[] array.
 *     => temp[] = [3, 4, 5, 6, 7, 1, 2]
 *
 * Third Steps:
 *     => Copy the elements of the temp[] array into the original array.
 *     => arr[] = temp[] So arr[] = [3, 4, 5, 6, 7, 1, 2]
 *
 * Step-by-step approach:
 *
 * Initialize a temporary array(temp[n]) of length same as the original array
 * Initialize an integer(k) to keep a track of the current index
 * Store the elements from the position d to n-1 in the temporary array
 * Now, store 0 to d-1 elements of the original array in the temporary array
 * Lastly, copy back the temporary array to the original array
 */