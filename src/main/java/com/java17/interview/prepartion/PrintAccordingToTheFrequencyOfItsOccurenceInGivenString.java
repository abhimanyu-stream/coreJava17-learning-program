package com.java17.interview.prepartion;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrintAccordingToTheFrequencyOfItsOccurenceInGivenString {

    public static void main(String[] args) {

        //java program input is  "aaaaaaammdddddkkkkx" output is "xmmkkkkdddddaaaaaaa"
        // Given text: "aaaaaaammdddddkkkkx"

        String str = "axaddaaakkaammdddkk";

        Map<Character, Long> charMap = str.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        StringBuilder builder = new StringBuilder();
        charMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(

                        characterLongEntry -> {
                            Character character = characterLongEntry.getKey();
                            Long value = characterLongEntry.getValue();
                            for(int i = 0; i < value.intValue(); i ++){
                                builder.append(character);
                            }

                        }
                );
        System.out.println(builder);


    }


}
