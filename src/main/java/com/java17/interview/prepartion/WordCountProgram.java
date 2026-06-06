package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordCountProgram {

    public static void main(String[] args) {


        String strings = "A Springboot Java Oracle Zero XML Web Mysql ";

        long count = Arrays.stream(strings.split(" ")).count();
        System.out.println(count);

        List<String> list = new ArrayList<>();
        for (String s : strings.split(" ")) {
            list.add(s);
        }

        System.out.println("list "+list);



        list.sort(Collections.reverseOrder());// sorted
        System.out.println("list "+list);



        list.sort((a,b)->b.length()-a.length());// by length sorting
        System.out.println(list);
    }


}
