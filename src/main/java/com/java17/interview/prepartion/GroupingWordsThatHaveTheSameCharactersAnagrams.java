package com.java17.interview.prepartion;

import java.util.*;

public class GroupingWordsThatHaveTheSameCharactersAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);                  // sort characters
            String key = new String(arr);      // create key

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());//List<List<String>>
    }

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = groupAnagrams(input);

        // Print result
        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}