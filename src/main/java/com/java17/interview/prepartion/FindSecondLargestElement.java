package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindSecondLargestElement {

    public static void main(String[] args) {


        List<Integer> integerList = Arrays.asList(1,2,3,4,5);


        Integer secondLargest = integerList.stream().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();
        System.out.println(secondLargest);





    }

}
