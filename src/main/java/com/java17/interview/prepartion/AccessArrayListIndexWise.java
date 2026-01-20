package com.java17.interview.prepartion;


import java.util.ArrayList;
import java.util.List;


public class AccessArrayListIndexWise {

    public static void main(String[] args) {


        List<String> strList = new ArrayList<>();
        strList.add("java");
        strList.add("hibernate");
        for(int i = 0; i < strList.size(); i++){
            System.out.println(strList.get(i));
        }


    }
}
