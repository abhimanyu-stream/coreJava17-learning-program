package com.java17.interview.prepartion;

import java.util.Collections;
import java.util.TreeMap;

public class TreeMapWithReverseOrderComparator {

    public static void main(String[] args) {
         TreeMap<Integer, String> map =
                new TreeMap<>(Collections.reverseOrder());

        map.put(10, "Ten");
        map.put(30, "Thirty");
        map.put(20, "Twenty");

        System.out.println(map);
    }
    
}
