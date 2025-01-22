package com.java17.interview.prepartion;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exam {
    private static String s = "Welcome";
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5,2,5,6,1,2,5,6};

        StringBuilder sb = new StringBuilder(s);
        System.out.println(sb.equals(s));//false



        Map<Integer, Integer> map = new HashMap<>();

        for(int i: array){

            if(map.containsKey(i)){
                Integer count = map.get(i);
                map.put(i, count  + 1);
            }else{
                map.put(i, 1);
            }
        }
        System.out.println(map);
        List<Integer> list = map.entrySet().stream().filter(f -> f.getValue() > 1L).map(Map.Entry::getKey).toList();
        System.out.println("Dulpicates are "+ list);

        //input : abcabcbb
        //output : abc


        String str = "abcabcbb";

        List<String> list1 = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(f -> f.getValue() > 1L).map(Map.Entry::getKey).toList();
        System.out.println("list1  " + list1);
//list1  [a, b, c]
        // Initialize the list
        List<Integer> list3 = Arrays.asList(1, 2, 3);

        // Compute sum using stream with a starting value (identity = 5)
        Integer sum = list3.stream().reduce(5, (a, b) -> a + b);

        // Compute parallel sum using parallelStream with a starting value (identity = 5)
        Integer pSum = list3.parallelStream().reduce(5, (a, b) -> a + b);

        // Print if the results are equal
        System.out.println(sum.equals(pSum));

    }
}
//{1=2, 2=3, 3=1, 4=1, 5=3, 6=2}
//Dulpicates are [1, 2, 5, 6]