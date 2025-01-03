package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MyTest {

    public static void main(String[] args) {

        // find prime between 2 to 100;


        int n = 100;

        int j;
        for (j = 2; j <= n; j++) {
            int count = 0;// reinitialized each time
            for (int i = 1; i <= j; i++) {

                if (j % i == 0) {
                    count++;
                }
            }
            if (count == 2) { // A prime number has exactly two divisors: 1 and itself
                System.out.println(j + " is prime.");
            }
        }

        // find factorials between 1 to 100

        int p = 10;

        int sum = 1;
        if (p < 0) {
            System.out.println("factorial of -ve integer is not possible");
        }
        if (p == 0){

            System.out.println("factorial of 0 is " + sum);
        }
        for(int k = 1; k <= p; k++){
            sum = sum * k;
            System.out.println("factorial of "+k +" is "+ sum);
        }


        // find max min average
        int[] arr = {10, 324, 45, 90, 9808};

        // using for loop
        int max = arr[0];
        int min = arr[0];

        int sums = 0;
        for(int num:arr){
            if(num > max){
                max = num;
            }
            if(num < min){
                min = num;
            }
            sums +=num;

        }
        double average = (double) sums / arr.length;
        System.out.println("max is "+ max);
        System.out.println("min is "+ min);
        System.out.println("average is "+ average);



        // using stream comparator

        List<Integer> list = Arrays.stream(arr).boxed().toList();
        Integer maxElement = list.stream().max(Comparator.naturalOrder()).get();
        Integer minElement = list.stream().min(Comparator.naturalOrder()).get();
        double doubleAvarage = list.stream().mapToInt(Integer::intValue).average().getAsDouble();



        // using stream and max min

        Arrays.stream(arr).max().getAsInt();
        Arrays.stream(arr).min().getAsInt();
        Arrays.stream(arr).average().getAsDouble();






        // using sorted()
        List<Integer> listsorted = Arrays.stream(arr).boxed().sorted().toList();

        int minElementInSorted = listsorted.get(0);
        int maxElementInSorted = listsorted.get(listsorted.size() - 1);
        double avg = Arrays.stream(arr).average().getAsDouble();




        // remove duplicates
        List<Integer> withoutDuplicate = Arrays.stream(arr).boxed().distinct().collect(Collectors.toList());
        System.out.println(withoutDuplicate);

        // find which starts with 1
        int a[]={10,20,15,90,11,44,100,43, 10, 15};
        List<String> startWith1 = Arrays.stream(a).mapToObj(String::valueOf).filter(f->f.startsWith(String.valueOf(1))).toList();
        System.out.println("startWith1 "+startWith1);




        // reverse word, from last towards first
        String originalString = "Hello, World!";

        StringBuffer buffer1 = new StringBuffer();
        String[] atrArray = originalString.split(" ");
        for (int i = atrArray.length -  1; i >= 0; i--){

            buffer1.append(atrArray[i]);
            if( i != 0){
                buffer1.append(" ");
            }
        }
        System.out.println(buffer1);




        StringBuffer buffer = new StringBuffer();
        char[] chars = originalString.toCharArray();
        for(int i = originalString.length() - 1; i >= 0 ;i--){
            buffer.append(chars[i]);
        }
        System.out.println();
        System.out.println(buffer);

        // find common longest prefix
        String[] newArray = {"Amazon", "Amazone", "AmazonIn"};

        findCommnonLargestPrefix(newArray);



        //count
        String strings = "A Springboot Java Oracle Zero XML Web Mysql ";

        long count = Arrays.stream(strings.split(" ")).count();
        System.out.println(count);

        int[] aIntArray= {1,2,3,4,5,6,7,8};

        int sumOfAIntArray = Arrays.stream(aIntArray).filter(f -> f % 2 == 0).sum();
        System.out.println(sumOfAIntArray);

        List<Integer> integerList = Arrays.asList(1,2,3,4,5);


        Integer secondLargest = integerList.stream().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();
        //Integer secondLargest = integerList.stream().max(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();
        System.out.println(secondLargest);




    }

    private static String findCommnonLargestPrefix(String[] newArray) {
        String prefix = newArray[0];


        for(int i = 1; i < newArray.length - 1; i++){

            while (newArray[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }
}

