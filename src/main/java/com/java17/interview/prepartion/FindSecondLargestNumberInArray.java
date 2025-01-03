package com.java17.interview.prepartion;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FindSecondLargestNumberInArray {

    public static void main(String[] args) {


        int[] intArray = {5, 2, 10, 9, 8, 3};



        //int secondLargest = findSecondLargest(intArray);
        //System.out.println(secondLargest);



        int largest = Arrays.stream(intArray).max().getAsInt();
        System.out.println("largest  " + largest);
        int minValue = Arrays.stream(intArray).sorted().findFirst().getAsInt();
        System.out.println("minValue " + minValue);







        List<Integer> listOfInt = new ArrayList<>();
        for (int i : intArray) {
            listOfInt.add(i);
        }
        //Find Second Largest in the List
        Integer secondMax = listOfInt.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println("secondMax "+secondMax);


        // Convert int array to ArrayList<Integer>
        Integer[] inntegerArray = {5, 2, 10, 9, 8, 3};
        List<Integer> intList = Arrays.asList(inntegerArray);
        int secondMin = intList.stream().sorted(Comparator.naturalOrder()).skip(1).findFirst().get();
        System.out.println("secondMin "+secondMin);




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

