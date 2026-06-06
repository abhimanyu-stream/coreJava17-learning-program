package com.java17.interview.prepartion;

import java.util.Comparator;

public class SortGivenString {

    public static void main(String[] args) {
        String s = "Abhimanyu";

        s.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)                 // IntStream → Stream<Character>
                .sorted(Comparator.naturalOrder())       // now legal
                .forEach(System.out::print);

        System.out.println("");



        s.toLowerCase()
                .chars()
                .sorted()//“Primitive streams don’t accept comparators; their sorted() is always natural order.”
                .forEach(c -> System.out.print((char) c));


    }
}
