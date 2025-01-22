package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFirstRepeatedCharInStringUsingStreamAPI {

    public static void main(String[] args) {
       // String str ="Abhimanyu is a Java Programmer";
        // first repeated char
        String str ="Accccbhimanyu is a Java Programmer";
        // first  repeated char
        Optional<Character> found = Optional.empty();

        for (Map.Entry<Character, Long> characterLongEntry : str.chars()
                .mapToObj(i -> Character.toLowerCase((char) i))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
                .entrySet()) {
            if (characterLongEntry.getValue() > 1L) {
                //characterLongEntry.find
                Character key = characterLongEntry.getKey();
                found = Optional.of(key);
                break;
            }
        }
        Character firstRepeatedChar = found.get();
        System.out.println("firstRepeatedChar "+firstRepeatedChar);





        List<Character> allRepeatedCharacter = new ArrayList<>();
        for (Map.Entry<Character, Long> characterLongEntry : str.chars()
                .mapToObj(i -> Character.toLowerCase((char) i))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
                .entrySet()) {
            if (characterLongEntry.getValue() > 1L) {
                Character key = characterLongEntry.getKey();
                allRepeatedCharacter.add(key);

            }
        }
        System.out.println("allRepeatedCharacter" + allRepeatedCharacter);





        System.out.println(Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1L)
                .map(m->new StringBuffer().append(m.getKey()).append(m.getValue())).toList());



        Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1L)
                .map(m->new StringBuffer().append(m.getKey()).append(m.getValue())).forEachOrdered(System.out::print);
        Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1L)
                .map(m->new StringBuffer().append(m.getKey()).append(m.getValue())).toList();





    }



}
