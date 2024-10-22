package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;

public class CountVowelsInEachObjectsInListUsingStreamAPI {

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        l1.add ("apple");
        l1.add("crypt");

        // count vowels in each object



        l1.forEach(s -> {
            long vowelCount = s.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".contains(String.valueOf(c)))
                    .count();
            System.out.println("Vowels in '" + s + "': " + vowelCount);
        });
        l1.forEach(s->{
            long vowelcount = s.chars().mapToObj(c->(char)c).filter(f->"aeiouAEIOU".contains(String.valueOf(f))).count();
            System.out.println(vowelcount);
        });

    }
}
