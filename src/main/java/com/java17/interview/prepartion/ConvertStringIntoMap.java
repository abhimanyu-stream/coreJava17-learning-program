package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertStringIntoMap {
    public static void main(String[] args) {
        String str = "name=John,age=25,city=Delhi";

        Map<String, String> map = Arrays.stream(str.split(","))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> arr[1]
                ));

        System.out.println(map);



        String str2 = "name=John,age=25,city=Delhi";
        Map<String, String> map2 = new HashMap<>();

        String[] pairs = str2.split(",");

        for (String pair : pairs) {

            String[] keyValue = pair.split("=");

            map2.put(keyValue[0], keyValue[1]);
        }

        System.out.println(map2);

        String str3 = "hello";

        Map<Character, Long> map3 = str3.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
        System.out.println(map3);

        Map<String, Long> mapp = Arrays.stream(str3.split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println(mapp);


        Map<String, Integer> mappp = Arrays.stream(str3.split(""))
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> 1,
                        Integer::sum
                ));

        System.out.println(mappp);



        String str4 = "java is java powerful";

        Map<String, Long> wordMap = Arrays.stream(str4.split(" "))
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        System.out.println(wordMap);

        String str5 = "ABC";

        Map<Integer, Character> map5 = new HashMap<>();

        for (int i = 0; i < str5.length(); i++) {
            map5.put(i, str5.charAt(i));
        }

        System.out.println(map5);


    }
}
