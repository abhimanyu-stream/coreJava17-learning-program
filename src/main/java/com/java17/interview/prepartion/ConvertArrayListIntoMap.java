package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.HashMap;

@SpringBootApplication
public class ConvertArrayListIntoMap {

    public static void main(String[] args) {
        SpringApplication.run(ConvertArrayListIntoMap.class, args);

        List<String> strList = Arrays.asList("Java","EJB");
        Map<String, Integer> hashMap = new HashMap<>();
        for(String s: strList){
            hashMap.put(s, s.length());


        }
        System.out.println(hashMap);
        System.out.println(strList);
    }
}
