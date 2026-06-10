package com.java17.interview.prepartion;

import java.util.TreeSet;

public class TreeSetMethods {

    public static void main(String[] args) {
        

        TreeSet<Integer> treeSetInteger = new TreeSet<>();
        treeSetInteger.add(1);
        treeSetInteger.add(2);
        treeSetInteger.add(3);
        treeSetInteger.add(4);
        treeSetInteger.add(1);
        System.out.println(treeSetInteger);


        System.out.println("First: " + treeSetInteger.first());
        System.out.println("Last: " + treeSetInteger.last());

        System.out.println("Higher than 3: " + treeSetInteger.higher(3));
        System.out.println("Lower than 4: " + treeSetInteger.lower(4));

        System.out.println("Ceiling 4: " + treeSetInteger.ceiling(4));
        System.out.println("Floor 3: " + treeSetInteger.floor(3));
    



        TreeSet<String> treeSetString = new TreeSet<>();
        treeSetString.add("a");
        treeSetString.add("b");
        treeSetString.add("a");
        System.out.println(treeSetString);




    }


    
    
}
