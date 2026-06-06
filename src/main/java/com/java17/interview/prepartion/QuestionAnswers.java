package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class QuestionAnswers {
    public static void main(String[] args) {
        SpringApplication.run(QuestionAnswers.class,args);



        Integer [] str = {1, 2, 8, 2,2, 2,5,1};
        int count = 0;


        Map<Integer, Integer> hashMap = new HashMap<>();


        for(int i = 0; i < str.length; i++){
            if(hashMap.containsKey(str[i])){
                count = hashMap.get(str[i]);
                hashMap.put(str[i], count + 1);
            }else{
                hashMap.put(str[i],1);
            }

        }//end for loop

        System.out.println(hashMap);
    }

}

