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
        char[] charArray = str.toCharArray();

        Map<Character, Integer> hashMap = new HashMap<>();


        for(int i = 0; i < str.length(); i++){

            if(hashMap.containsKey(charArray[i])){
                int count = hashMap.get(charArray[i]);
                hashMap.put(charArray[i], count + 1);

            }else{
                hashMap.put(charArray[i], 1);
            }
        }
        System.out.println(hashMap);
        System.out.println(Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));



    }

}


