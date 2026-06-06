package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertStringInToMapUsingStreamAPI extends  Error{

    public static void main(String[] args) {

        String str = "Abhimanyuaaabbb";
        // count of char
        //char[] charArray = str.toCharArray();
        String[] split = str.split("");
        Map<String, Integer> hashMap = new HashMap<>();


        for(int i = 0; i < str.length(); i++){

            if(hashMap.containsKey(split[i])){
                int count = hashMap.get(split[i]);
                hashMap.put(split[i], count + 1);

            }else{
                hashMap.put(split[i], 1);
            }
        }
        System.out.println("hashMap " +hashMap);


        System.out.println(Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));


        // below is without for loop implementation
        Arrays.stream(split).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream().map(e->new StringBuffer().append(e.getKey()).append(e.getValue())).forEach(System.out::print);


    }

}


