package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Objects;
public class RemoveHash {

    public static void main(String[] args) {
        String[] arr = {"John", "S#m", "123", "###12", "Player"};

        String[] result = Arrays.stream(arr)
                .map(s -> s.replace("#", ""))// replace("#", "") removes all occurrences of # from each string.
                .toArray(String[]::new);

        System.out.println(Arrays.toString(result));
        
        String[] arr2 = {"John", "S#m", "12@3", "###12", "Pla!yer"};

        String[] resultReg = Arrays.stream(arr2)
                .map(s -> s.replaceAll("[^a-zA-Z0-9]", ""))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(resultReg));

        //Output:

        //[John, Sm, 123, 12, Player]

        //Here:

        //[^a-zA-Z0-9]

        //means:

        //anything that is NOT a letter or digit.
        
        
        String[] arr3 = {"John", "S#m", null, "###12", "Player"};

        String[] resultOO = Arrays.stream(arr3)
                .map(s -> s == null ? null : s.replace("#", ""))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(resultOO));

        //Output:

        //[John, Sm, null, 12, Player]

        //If instead you want to remove null elements:

        String[] resultO = Arrays.stream(arr)
                .filter(Objects::nonNull)
                .map(s -> s.replace("#", ""))
                .toArray(String[]::new);

        //You would need:

        //import java.util.Objects;
    }
}
