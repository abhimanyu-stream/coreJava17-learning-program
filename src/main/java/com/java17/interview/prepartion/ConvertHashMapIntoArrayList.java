package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.HashMap;

@SpringBootApplication
public class ConvertHashMapIntoArrayList {

    public static void main(String[] args) {

        SpringApplication.run(ConvertHashMapIntoArrayList.class,args);

        List<String> strList = new ArrayList<>();
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("java", 1);
        hashMap.put("EJB", 2);

        // by keys

        for(String s: hashMap.keySet()){
            strList.add(s);


        }
        System.out.println(strList);

        // by values

        for(Integer i:hashMap.values()){
            strList.add(String.valueOf(i));


        }
        System.out.println(strList);

    }
}
