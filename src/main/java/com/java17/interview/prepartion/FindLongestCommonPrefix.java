package com.java17.interview.prepartion;

public class FindLongestCommonPrefix {
    public static void main(String[] args) {
        String[] s = {"flower", "flow", "flight"};
        String resuly = longestCommonPrefix1(s);
        System.out.println(resuly);
    }

    public static String longestCommonPrefix(String[] S) {
        if (S.length == 0) return "";
        String prefix = S[0];
        for (int i = 1; i < S.length; i++) {
            while (S[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }

        }
        return prefix;
    }
    public static String longestCommonPrefix1(String[] s) {

        if(s.length == 0)
            return "";

        String prefix = s[0];
        for(int i = 1; i < s.length; i++){
            while (s[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";

            }
        }

        return prefix;
    }


}

