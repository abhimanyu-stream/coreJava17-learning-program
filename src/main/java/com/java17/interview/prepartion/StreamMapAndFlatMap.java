package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMapAndFlatMap {

    public static void main(String[] args) {
        //1.	Flattening Explained: Imagine you have a bunch of boxes, and each box contains some items. Now, you want to take out all those items from the boxes and put them into a single box. That’s precisely what flatMap() does with a stream.
        //      It takes objects from different collections (or arrays) within the original stream and puts all those objects into a new stream. In simpler terms, flattening means merging multiple collections or arrays into one.

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),// box1
                Arrays.asList(4, 5),// box2
                Arrays.asList(6, 7, 8)// box3
        );

        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(list -> list.stream()) // Flattening step , From box1 box2 and box3 data collected in a single List Object , its job of flatMap
                .toList(); // Result: [1, 2, 3, 4, 5, 6, 7, 8]

        System.out.println("flattenedList : "+ flattenedList);

        List<String> listString = Arrays.asList("Java", "Oracle", "MS");
        listString = listString.stream().map(m->m.toUpperCase()).collect(Collectors.toList());
        System.out.println(listString);

        List<List<String>> listofListString = Arrays.asList(
                Arrays.asList("java"),
                Arrays.asList("ejb")
        );
        List<String> listStringresult  = listofListString.stream().flatMap(list-> list.stream()).toList();
        System.out.println(listStringresult);


    }
}
