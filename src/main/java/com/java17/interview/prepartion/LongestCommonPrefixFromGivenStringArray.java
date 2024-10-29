package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongestCommonPrefixFromGivenStringArray {

    public static void main(String[] args) {

        //Given a String, find the first non-repeated character in it using Stream functions?  String s1= "Welcome to java world ";  output-c




        String[] strArray = {"Amazon", "Amazone", "Amazonin"};// Amazon will be output
        String prefixResult = longestCommonPrefix(strArray);
        System.out.println("prefixResult   : " + prefixResult);



        String[] strings={"10","12","3","9","49"}; //plz sort given numbers in java8?
        String nums = "1 2 3 4 5";
        // can split by whitespace to store into an array
        String[] num_arr = nums.split(" ");
 
        List<Integer> listOfInt = new ArrayList<>();
        for(int i = 0; i < strings.length; i++){
            listOfInt.add(Integer.parseInt(strings[i]));
        }


        listOfInt.stream().sorted().collect(Collectors.toList());

        List<Integer> list = Arrays.asList(10, 23, -4, 0, 18, 10, 10);


        Integer firstRepeatedInteger = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(f -> f.getValue() > 1L).map(m -> m.getKey()).findFirst().get();
        System.out.println(firstRepeatedInteger);

        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList);

        String  str = "Welcome to java world";


        Optional<Character> found = Optional.empty();
        for (Map.Entry<Character, Long> f : str.chars()
                .mapToObj(i -> Character.toLowerCase(Character.valueOf((char) i)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
                .entrySet()) {
            if (f.getValue() == 1L) {
                Character key = f.getKey();
                found = Optional.of(key);
                break;
            }
        }
        Character firstnonreapeatedchar = found.get();

        System.out.println("firstnonreapeatedchar  :" + firstnonreapeatedchar);






        // Find First reepeated char from given String
        Character result =  str.chars()           // IntStream
                .mapToObj(i -> Character.toLowerCase(Character.valueOf((char) i)))  // convert to lowercase & then to Character object Stream
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // store in a LinkedHashMap with the count
                .entrySet().stream()                       // EntrySet stream
                .filter(entry -> entry.getValue() > 1L)   // extracts characters with a count > greater than  1
                .map(entry -> entry.getKey())              // get the keys of EntrySet
                .findFirst().get();  // get the first entry from the keys

//  .filter(entry -> entry.getValue() > 1L)   // extracts characters with a count ==  1 first non repeated char
//
        long resultCount =  str.chars()           // IntStream
                .mapToObj(i -> Character.toLowerCase(Character.valueOf((char) i)))  // convert to lowercase & then to Character object Stream
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // store in a LinkedHashMap with the count
                .entrySet().stream()                       // EntrySet stream
                .filter(entry -> entry.getValue() > 1L)   // extracts characters with a count of 1
                .map(entry -> entry.getKey()).count();             // get the keys of EntrySet




        System.out.println(resultCount);




        System.out.println(result);



    }
    public static String longestCommonPrefix(String[] strArray){

        if(strArray.length == 0)
            return "";

        String prefix = strArray[0];
        for(int i = 1; i < strArray.length; i++){

            while(strArray[i].indexOf(prefix) != 0 ){

                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";

            }
        }


        return  prefix;
    }
    public static String longestCommonPrefix2(String[] strArray){

        if(strArray.length == 0)
            return "";

        String prefix = strArray[0];
        for(int i = 1; i < strArray.length; i++){
            while(strArray[i].indexOf(prefix) != 0){

                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";

            }
        }


        return  prefix;
    }

}

/***
 *
 * [14:30] Rama Krishna M
 * Find Longest common Prefix in an array
 * [“amazon”, “amazed”, “amaze”, “amazing”, “amazes”]
 * [14:30] Rama Krishna M
 * Given a String, find the first non-repeated character in it using Stream functions?  String s1= "Welcome to java world ";  output-c
 */
