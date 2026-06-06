package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeBruijnSequenceCombinatorialMathematics {
    static Set<String> seen = new HashSet<>();
    static List<Integer> edges = new ArrayList<>();

    static void dfs(String node, int k, String A) {
        for (int i = 0; i < k; ++i) {
            String str = node + A.charAt(i);
            if (!seen.contains(str)) {
                seen.add(str);
                dfs(str.substring(1), k, A);
                edges.add(i);
            }
        }
}
    static String deBruijn (int n, int k, String A){
        seen.clear();
        edges.clear();
        String startingNode = A.substring(0, n - 1);
        dfs(startingNode, k, A);

        StringBuilder result = new StringBuilder();
        int l = (int) Math.pow(k, n);
        for (int i = 0; i < l; ++i)
            result.append(A.charAt(edges.get(i)));
        result.append(startingNode);

        return result.toString();
    }
    public static void main (String[] args){
        int n = 3, k = 2;
        String A = "01";
        System.out.println(deBruijn(n, k, A));
    }
}
