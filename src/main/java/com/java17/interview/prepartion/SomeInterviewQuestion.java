package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SomeInterviewQuestion {

    public static void main(String[] args) {

        int a[]={10,20,15,90,11,44,100,43, 10, 15};
        // filter those number starts with 1

        List<String> startWith1 = Arrays.stream(a).mapToObj(String::valueOf).filter(f->f.startsWith(String.valueOf(1))).toList();
        System.out.println("startWith1 "+startWith1);



        Arrays.stream(a).forEach(System.out::println);
        System.out.println("------");

        List<Integer> withoutDuplicate = Arrays.stream(a).boxed().distinct().collect(Collectors.toList());
        System.out.println(withoutDuplicate);


        int[] i= {3,4,1,289,0};// find max, min, avg

        Integer min = Arrays.stream(i).boxed().sorted(Integer::compareTo).limit(2).findFirst().get();
        System.out.println("min " +min);
        Arrays.stream(i).max().getAsInt();
        Arrays.stream(i).min().getAsInt();
        Arrays.stream(i).average();

        Integer max = Arrays.stream(i).boxed().sorted(Collections.reverseOrder()).limit(2).findFirst().get();
        System.out.println("max " + max);
        OptionalDouble average1 = OptionalDouble.of(Arrays.stream(i).average().orElse(0));

        double average = IntStream.of(i)
                .average()
                .orElse(0); // Default value if stream is empty

        System.out.println("Average: " + average);
    }
}
