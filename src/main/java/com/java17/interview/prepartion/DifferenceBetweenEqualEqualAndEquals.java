package com.java17.interview.prepartion;

public class DifferenceBetweenEqualEqualAndEquals {
    public static void main(String[] args) {


        String a = new String("A");
        String b = new String("A");

        System.out.println(a==b);// false
        System.out.println(a.equals(b));// true
        System.out.println(a.hashCode());     // 65
        System.out.println(b.hashCode());     // 65
    }
}
