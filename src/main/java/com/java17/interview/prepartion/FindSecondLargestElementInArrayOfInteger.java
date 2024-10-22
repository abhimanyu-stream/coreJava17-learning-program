package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindSecondLargestElementInArrayOfInteger {

    public static void main(String[] args) {
        SpringApplication.run(FindSecondLargestElementInArrayOfInteger.class, args);


        int[] intArray = {1,22,33,44,55,66};

        int largest = intArray[0];
        int secondLargest = -1;

        for(int i = 0; i < intArray.length; i++){

            if(intArray[i] > largest){
                secondLargest = largest;
                largest = intArray[i];
            }else if(intArray[i] < largest && intArray[i] > secondLargest){
                secondLargest = intArray[i];
            }


        }
        System.out.println(secondLargest);
    }
}
