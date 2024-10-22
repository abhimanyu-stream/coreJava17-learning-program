package com.java17.interview.prepartion;

public class RotateElementsNTimesUsingForLoop {


    public static void main(String[] args) {



        int[] arr = {1,2,3,4,5,6,7};

       // n = 3

        //output  :
//[5,6,7,1,2,3,4]


        // using for loop
        int numberOfRoratation = 4;

        int[] temp = new int[arr.length];
        int k = 0;
        System.out.println("arr length : " + arr.length);
        for(int i = numberOfRoratation; i < arr.length; i++){
            temp[k] = arr[i];
            k++;

        }
        for(int i = 0; i < numberOfRoratation; i++ ){

            temp[k] = arr[i];
            k++;
        }


        System.arraycopy(temp, 0, arr, 0, arr.length);

        for(int i = 0; i< arr.length; i++){

            System.out.println(arr[i] );

        }

    }
}
