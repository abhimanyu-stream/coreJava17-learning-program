package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindCommonAmong3Array {
    public static void main(String[] args) {

        //Explanation: 5 and 20 are common in all the arrays.
        //Input: A[] = {2, 5, 10, 30}, B[] = {5, 20, 34}, C[] = {5, 13, 19}
        //Output: 5
        //Explanation: 5 occurs multiple times in all the three arrays but it will be printed once.

        // int[] A = {2, 5, 10, 30, 20};
        //int[] B = {5, 20, 34};
        //int[] C = {5, 13, 19, 20};
        //int[] A = {2, 5, 10, 30};
        //int[] B = {5, 20, 34};
        //int[] C = {5, 13, 19};



        int[] A = {2, 5, 10, 30, 20};
        int[] B = {5, 20, 34};
        int[] C = {5, 13, 19, 20};

        Set<Integer> set1 = Arrays.stream(A)
                                  .boxed()
                                  .collect(Collectors.toSet());

        Set<Integer> set2 = Arrays.stream(B)
                                  .boxed()
                                  .collect(Collectors.toSet());

        Set<Integer> set3 = Arrays.stream(C)
                                  .boxed()
                                  .collect(Collectors.toSet());

        set1.retainAll(set2);
        set1.retainAll(set3);

        System.out.println(set1);


        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                for(int k = 0; k < C.length; k++) {
                    if(A[i] == B[j] && A[i] == C[k] && B[j] == C[k]) {
                        if(!map.containsKey(A[i])) {

                            map.put(A[i], A[i]);
                        }

                    }
                }

            }


        }
        System.out.println(map.keySet());

        List<Integer> list = map.keySet().stream().sorted().collect(Collectors.toList());

        System.out.println(list);

    }
}
