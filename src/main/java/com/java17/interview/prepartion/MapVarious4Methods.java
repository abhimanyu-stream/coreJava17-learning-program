package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapVarious4Methods {
    public static void main(String[] args) {

        Map<String, Integer> fruits = new HashMap<>();

        fruits.put("apple", 1);
        fruits.put("banana", 2);
        System.out.println(fruits);
        
        //
        int getOrDefaultApple = fruits.getOrDefault("apple", -1);
        System.out.println(getOrDefaultApple);
         int getOrDefaultAppleNo = fruits.getOrDefault("applen", -1);
        System.out.println(getOrDefaultAppleNo);
        int getOrDefaultMango = fruits.getOrDefault("mango", 1);
        System.out.println(getOrDefaultMango);


        //
        fruits.putIfAbsent("mango", 5);
        /**
         * 
         * Equivalent old-style code:
            if (!map.containsKey("banana")) {
            map.put("banana", 20);
        }
         */
        System.out.println(fruits);

        //
        fruits.computeIfAbsent("guava",  k -> 11);
        /**
         * Equivalent old-style code:
            if (!map.containsKey("banana")) {
            map.put("banana", 20);
        }
         */
        System.out.println(fruits);
        
        fruits.computeIfPresent("apple", (k,v) -> v + 49);
        /**
         * Equivalent old-style code:
            if (map.containsKey("banana")) {
            map.put("banana", map.get("banana") + 20);
        }
         */
        
        System.out.println(fruits);
        fruits.merge("mango",15,(a,b) -> a + b);// b is 15 and a is 5
        /**
         * Use case:

        Add / update / combine values in one method.

        Behavior:
        If key missing → insert value
        If key exists → combine old + new value

         * //map.merge("apple", 5, (oldVal, newVal) -> oldVal + newVal);
         * //map.merge("orange", 30, (a, b) -> a + b);
         * 
         * 
         * Equivalent old-style code:
        if (map.containsKey("apple")) {
         map.put("apple", map.get("apple") + 5);
        } else {
            map.put("apple", 5);
        }
         */
        System.out.println(fruits);


        
    }
}
