package com.java17.interview.prepartion;

public class RotateIntArrayElement {


    public static void main(String[] args) {


        // shift array elements

        // d 2
        int[] intArray = {2,5,8,9};

        int[] intRotated = new int[intArray.length];
        int k = 0;

        for(int i = 2; i < intArray.length; i++){
            intRotated[k] = intArray[i];
            k++;

        }
        for(int i = 0; i < 2; i++){
            intRotated[k] = intArray[i];
            k++;

        }
        for(int i = 0; i < intArray.length; i++){
          intArray[i] = intRotated[i];
          System.out.println(intArray[i]);

        }


    }
}
