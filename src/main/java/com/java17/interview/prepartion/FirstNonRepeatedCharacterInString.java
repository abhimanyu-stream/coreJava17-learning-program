package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FirstNonRepeatedCharacterInString {

    public static void main(String[] args) {
       
        
        //find the first non-repeated character in it using Stream functions?  String s1= "Welcome to java world ";  output-c
        String s1= "Welcome to java world "; // output-c
        
        String firstNonRepeatedChar1 = Arrays.stream(s1.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
        .stream()
        .filter(e-> e.getValue() == 1L)
        .map(Map.Entry::getKey)
        .findFirst()
        .get();
        
        System.out.println("firstNonRepeatedChar1  " +firstNonRepeatedChar1);
        
        
        Character firstNonRepeatedChar = s1.chars()           // IntStream
        .mapToObj(i -> Character.toLowerCase((char) i))  // convert to lowercase & then to Character object Stream
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // store in a LinkedHashMap with the count
        .entrySet().stream()                       // EntrySet stream
        .filter(entry -> entry.getValue() == 1L)   // extracts characters with a count of 1
        .map(Map.Entry::getKey)              // get the keys of EntrySet
        .findFirst().get();
        
        System.out.println("The  first  non  repeated  character  is  : " + firstNonRepeatedChar);
        		
        
    }

   
}
