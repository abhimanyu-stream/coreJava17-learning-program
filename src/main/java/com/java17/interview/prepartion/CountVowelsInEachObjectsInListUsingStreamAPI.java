package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountVowelsInEachObjectsInListUsingStreamAPI {

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        l1.add ("apple");
        l1.add("crypt");
        //Arrays.asList("jj","");
        // count vowels in each object



        String buffer = l1.stream()
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(buffer);




        StringBuilder buffers = new StringBuilder();

        l1.forEach(s -> {

            // extract vowels from the string
            String vowels = s.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                    .map(String::valueOf)
                    .collect(Collectors.joining());

            System.out.println("Vowels in '" + s + "': " + vowels.length());

            // append vowels to buffer
            buffers.append(vowels);
        });

        System.out.println("buffers = " + buffers.toString());





        String[] strArray = {"apple", "animal", "umberlla"};
        Arrays.stream(strArray).forEach(s->{
            long vowelcount = s.chars().mapToObj(c->(char)c).filter(f->"aeiouAEIOU".contains(String.valueOf(f))).count();
            System.out.println(s + "  has numbers of vowel(s)  " +vowelcount);
        });

    }
}
