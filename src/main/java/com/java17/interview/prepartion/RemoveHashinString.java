package com.java17.interview.prepartion;

import java.util.Arrays;

public class RemoveHashinString {
    public static void main(String[] args) {
        String[] arr = {"John", "S#m", "123", "###12", "Player"};

        String[] result = Arrays.stream(arr)
                .map(s -> s.replace("#", ""))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(result));
    }
}
