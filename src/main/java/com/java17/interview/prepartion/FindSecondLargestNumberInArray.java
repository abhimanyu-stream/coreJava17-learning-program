package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;

public class FindSecondLargestNumberInArray {

    public static void main(String[] args) {


        int[] intArray = {5, 2, 10, 9, 8, 3};


        int secondLargest =
                Arrays.stream(intArray)
                        .boxed()                // convert int → Integer
                        .sorted(Comparator.reverseOrder())
                        .skip(1)                // drop the largest
                        .findFirst()
                        .orElseThrow();

        System.out.println(secondLargest);






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

//  List<Integer> integerList ->>> sorted(Comparator.reverseOrder())
//   Map<String, Integer> map->> sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
//  int[] intArray = {5, 2, 10, 9, 8, 3}; -->>   int largest = Arrays.stream(intArray).max().getAsInt();,     int min = Arrays.stream(intArray).min().getAsInt();,   int minValue = Arrays.stream(intArray).sorted().findFirst().getAsInt();// ascending order
// Integer[] inntegerArray = {5, 2, 10, 9, 8, 3}; ->> List<Integer> intList = Arrays.asList(inntegerArray);
// listOfInt.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();

//intList.stream().sorted(Comparator.naturalOrder()).skip(1).findFirst().get();
