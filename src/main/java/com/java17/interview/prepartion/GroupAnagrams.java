package com.java17.interview.prepartion;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {

        String[] words = {
                "eat", "tea", "tan",
                "ate", "nat", "bat"
        };

        Map<String, List<String>> map = new HashMap<>();

        for(String word : words) {

            char[] chars = word.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);
            /**
             * if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
             */

           

            

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);

            /**
             * 
             * Modern Alternative (Very Important)

Using computeIfAbsent()

map.computeIfAbsent(key, k -> new ArrayList<>())
   .add(word);

This does both operations together.

Equivalent to:

if(!map.containsKey(key)) {
    map.put(key, new ArrayList<>());
}

map.get(key).add(word);

             */
        }

        System.out.println(map.values());
    }
}