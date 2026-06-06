package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapConcurrentHashMapBehaviour {
    public static void main(String[] args) {



        Map<Integer,String> map=new HashMap<>();

        map.put(null,"value1");// Not NullPointerException
        map.put(1,"apple");


        map.put(3,"grapes");
        map.put(null,null);// Not NullPointerException
        map.put(5,"papaya");
        map.put(2,null);// Not NullPointerException
        map.put(4,null);// Not NullPointerException
        map.put(null,"value6");// Not NullPointerException






        map.forEach((key, value) ->
                System.out.println(key + "::" + value)
        );
        map.entrySet().stream().forEach(System.out::println);

        Map<Integer, String> conMap = new ConcurrentHashMap<>();
        conMap.put(null, "A");   // 💥 NullPointerException
        conMap.put(1, null);   // 💥 NullPointerException





    }
}
