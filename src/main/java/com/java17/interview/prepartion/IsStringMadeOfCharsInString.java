package com.java17.interview.prepartion;

import java.util.Map;
import java.util.stream.Collectors;

public class IsStringMadeOfCharsInString {
    public static void main(String[] args) {

        String s1 = "welcome"; String s2 = "meow";
        boolean result = canForm(s1, s2);
        System.out.println(result);

    }
    //Stream + Reduce (Functional Style)
    public static boolean canForm(String s1, String s2) {
        Map<Character, Integer> freq =
                s1.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toMap(
                                c -> c,
                                c -> 1,
                                Integer::sum
                        ));

        return s2.chars()
                .mapToObj(c -> (char) c)
                .allMatch(c -> freq.merge(c, -1, Integer::sum) >= 0);
    }

}
