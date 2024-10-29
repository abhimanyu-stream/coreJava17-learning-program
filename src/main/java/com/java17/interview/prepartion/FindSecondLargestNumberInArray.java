package com.java17.interview.prepartion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindSecondLargestNumberInArray {

    public static void main(String[] args) {


        int[] intArray = {5,2,10,9,8,3};
        Integer[] inntegerArray = {5,2,10,9,8,3};
        int secondLargest = findSecondLargest(intArray);
       System.out.println(secondLargest);



       List<Integer> listOfInt = new ArrayList<>();
       for(int i :intArray){
           listOfInt.add(i);
       }

        int largest = Arrays.stream(intArray).max().getAsInt();
        System.out.println("largest"+largest);



        //Find Second Largest in the List
      Integer result =  listOfInt.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
      System.out.println(result);

      int x = Arrays.stream(intArray).sorted().skip(1).findFirst().getAsInt();
      System.out.println(x);

      // Convert int array to ArrayList<Integer>

      List<Integer> intList = new ArrayList<>(Arrays.asList(inntegerArray));
      int y = intList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
      System.out.println(y);





    }
    static int findSecondLargest(int[] intArray) {
        int largest = intArray[0];
        int secondLargest = -1;
        for (int i = 1; i < intArray.length; i++) {
            if (intArray[i] > largest) {
                secondLargest = largest;
                largest = intArray[i];
            } else if (intArray[i] < largest && intArray[i] > secondLargest) {
                secondLargest = intArray[i];
            }
        }
        return secondLargest;
    }
}

