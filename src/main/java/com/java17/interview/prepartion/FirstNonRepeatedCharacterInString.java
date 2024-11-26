package com.java17.interview.prepartion;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedCharacterInString {

    public static void main(String[] args) {
        System.out.println("  Please  enter  the  input  string  :");
        Scanner in = new Scanner(System.in);    // read from System input
        String input = in.nextLine();
        Character firstNonRepeatedChar = logic(input);
        System.out.println("The  first  non  repeated  character  is  :    " + firstNonRepeatedChar);
        in.close();
    }

    private static Character logic(String input) {

        return input.chars()           // IntStream
                .mapToObj(i -> Character.toLowerCase((char) i))  // convert to lowercase & then to Character object Stream
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // store in a LinkedHashMap with the count
                .entrySet().stream()                       // EntrySet stream
                .filter(entry -> entry.getValue() == 1L)   // extracts characters with a count of 1
                .map(Map.Entry::getKey)              // get the keys of EntrySet
                .findFirst().get();
    }
}
