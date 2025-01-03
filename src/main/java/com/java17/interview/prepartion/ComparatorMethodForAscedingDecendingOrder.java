package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorMethodForAscedingDecendingOrder {
    public static void main(String[] args) {

        //Example Code
        //Here’s an example of sorting both numbers and strings:
        // Example for Numbers
        List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 2);
        Collections.sort(numbers); // Ascending by default
        System.out.println("Ascending Numbers: " + numbers);

        Collections.sort(numbers, Comparator.reverseOrder());
        numbers.sort(Comparator.reverseOrder());
        System.out.println("Descending Numbers: " + numbers);

        // Example for Strings
        List<String> strings = Arrays.asList("apple", "orange", "banana", "grape");
        Collections.sort(strings); // Ascending by default
        System.out.println("Ascending Strings: " + strings);

        Collections.sort(strings, Comparator.reverseOrder());
        strings.sort(Comparator.reverseOrder());
        System.out.println("Descending Strings: " + strings);
    }
}
/***
 * In Java, the behavior of the Comparator for digits and strings depends on the natural ordering of the elements being compared. Here's the detailed explanation:
 *
 * 1. Digits (Numbers)
 * Natural Ordering: Numbers in Java follow an ascending order by default.
 * Example: 1 < 2 < 3.
 * When using Comparator.naturalOrder() or simply sorting a collection of numbers without a custom comparator, the result will be in ascending order.
 * 2. Strings
 * Natural Ordering: Strings are sorted in lexicographical (dictionary) order, which is effectively ascending order.
 * Example: "apple" < "banana" < "cherry".
 * Java compares strings based on the Unicode value of each character in the string. For instance:
 * 'a' (Unicode 97) comes before 'b' (Unicode 98).
 * Uppercase letters (e.g., 'A', Unicode 65) come before lowercase letters ('a', Unicode 97).
 * Default Ordering in Comparators
 * Ascending by Default:
 *
 * If you use Collections.sort() on a List<Integer> or List<String>, it sorts in ascending order.
 * Comparator.naturalOrder() sorts in ascending order.
 * Descending Order:
 *
 * To sort in descending order, you explicitly use Comparator.reverseOrder() or write a custom comparator:

 * Comparator<Integer> descendingComparator = (a, b) -> b - a;
 * Summary Table
 * Data Type	Default (Natural) Order	Comparator for Reverse Order
 * Digits	Ascending	Comparator.reverseOrder()
 * Strings	Ascending (Lexicographical)	Comparator.reverseOrder()
 *
 * */