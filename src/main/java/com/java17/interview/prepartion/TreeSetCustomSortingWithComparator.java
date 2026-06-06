package com.java17.interview.prepartion;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetCustomSortingWithComparator {
    public static void main(String[] args) {
        // Sort in descending order using Comparator
        TreeSet<Integer> numbers = new TreeSet<>(Comparator.reverseOrder());

        numbers.add(10);
        numbers.add(40);
        numbers.add(20);
        numbers.add(30);

        System.out.println("Descending TreeSet: " + numbers);
    }

}
