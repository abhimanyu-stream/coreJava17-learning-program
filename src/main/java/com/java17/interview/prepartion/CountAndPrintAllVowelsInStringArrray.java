package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountAndPrintAllVowelsInStringArrray {

    public static void main(String[] args) {

        String[] str = {"apple","ieapple", "out"};

        Arrays.stream(str).forEach(


                s -> {
                    List<Character> collect = s.chars().mapToObj(i -> (char) i).filter(f -> "aeiouAEIOU".contains(String.valueOf(f))).toList();
                    System.out.println(s +"  contains "+collect);
                }
        );



        List<String> l1 = new ArrayList<>();
        l1.add ("apple");
        l1.add("crypt");
        //Arrays.asList("jj","");
        // count vowels in each object


        StringBuffer buffer = new StringBuffer();
        l1.forEach(s -> {
            long vowelCount = s.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".contains(String.valueOf(c)))
                    .count();
            System.out.println("Vowels in '" + s + "': " + vowelCount);


            for(int i = 0; i < vowelCount; i++){
                buffer.append(s);//s ->
            }
        });
        System.out.println("buffer " +buffer);
        System.out.println(new String(buffer));
    }
}
