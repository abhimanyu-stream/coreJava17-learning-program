package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountVowelsInEachObjectsInListUsingStreamAPI {

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        l1.add ("apple");
        l1.add("crypt");

        // count vowels in each object


        StringBuffer buffer = new StringBuffer();
        l1.forEach(s -> {
            long vowelCount = s.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".contains(String.valueOf(c))).count();
            System.out.println("Vowels in '" + s + "': " + vowelCount);


            for(int i = 0; i < vowelCount; i++){
                buffer.append(s);
            }
        });
        System.out.println("buffer " +buffer);



        l1.forEach(s->{
            long vowelcount = s.chars()
                    .mapToObj(c->(char)c)
                    .filter(f->"aeiouAEIOU".contains(String.valueOf(f))).count();
            System.out.println(vowelcount);
        });




        String[] strArray = {"apple", "animal", "umberlla"};
        Arrays.stream(strArray).forEach(s->{
            long vowelcount = s.chars().mapToObj(c->(char)c).filter(f->"aeiouAEIOU".contains(String.valueOf(f))).count();
            System.out.println(s + "  has numbers of vowel(s)  " +vowelcount);
        });

    }
}
