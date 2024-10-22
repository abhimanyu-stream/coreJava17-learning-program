package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AccessArrayListIndexWise {

    public static void main(String[] args) {
        SpringApplication.run(AccessArrayListIndexWise.class,args);

        List<String> strList = new ArrayList<>();
        strList.add("java");
        strList.add("hibernate");
        for(int i = 0; i < strList.size(); i++){
            System.out.println(strList.get(i));
        }


    }
}
