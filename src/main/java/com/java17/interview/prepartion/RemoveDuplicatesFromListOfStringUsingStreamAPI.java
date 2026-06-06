package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromListOfStringUsingStreamAPI {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Manikanta","Ravi","Dinesh","Mohan","Ravi"); //Remove the dublicate by using Stream api




        names =names.stream().distinct().collect(Collectors.toList());
        System.out.println(names);

    }
}
