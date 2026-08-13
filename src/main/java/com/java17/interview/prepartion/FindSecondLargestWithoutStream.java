package com.java17.interview.prepartion;



import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindSecondLargestWithoutStream {


    public static void main(String[] args){

         //Find Second largest 
         int[] numbers = {20, 10, 50, 70, 70, 40, 90};

         Integer secondLargest = Arrays.stream(numbers)
         .boxed()
         .sorted(Comparator.reverseOrder())
         .skip(1)
         .findFirst()
         .orElseThrow();

         System.out.println("secondLargest  "+ secondLargest);

         //Remove duplicate
         System.out.println("---------------------");

         Arrays.stream(numbers).boxed().distinct().forEach(System.out::println);

         List<Integer> list = Arrays.stream(numbers).boxed().toList();
         System.out.println("---------------------");
         System.out.println(list);

         System.out.println("---------------------");
         Integer largest = Integer.MIN_VALUE;
         Integer secondLargestNumber = Integer.MIN_VALUE;

         for(int number:numbers){
            if(number > largest){
                secondLargestNumber = largest;
                largest = number;
            }else if(number > secondLargestNumber && number != largest){
                secondLargestNumber = number;

            }
         }
         System.out.println("secondLargest Number :"+ secondLargestNumber);

         System.out.println("---------------------------------");
         Integer largestNumber = Arrays.stream(numbers)
         .boxed()
         .sorted(Comparator.reverseOrder())
         .findFirst()
         .orElseThrow();
         System.out.println("LargestNumber :"+largestNumber);

        System.out.println("---------------------------------");
        Integer minimum = Arrays.stream(numbers).boxed().sorted(Comparator.naturalOrder()).findFirst().orElseThrow();
        System.out.println("Minimum :"+ minimum);



    }
    
}
