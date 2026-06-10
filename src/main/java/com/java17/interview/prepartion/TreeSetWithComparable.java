package com.java17.interview.prepartion;

import java.util.TreeSet;

public class TreeSetWithComparable {

    public static void main(String[] args) {


    TreeSet<Foods> foodsTreeSet = new TreeSet<>();

        foodsTreeSet.add(new Foods(3, "Allu"));
        foodsTreeSet.add(new Foods(1, "Bagain"));
        foodsTreeSet.add(new Foods(2, "Rice"));
        foodsTreeSet.add(new Foods(2, "Rice"));

        foodsTreeSet.add(new Foods(2, "Tamotoo"));
        System.out.println(foodsTreeSet);
        
    }
    
}
class Foods implements Comparable<Foods> {
    int id;
    String name;

    Foods(int id, String name) {
        this.id = id;
        this.name = name;
    }

    
     @Override
    public int compareTo(Foods e) {
        return this.id - e.id;   // sort by id
        //there is no duplicate by id
        
    }
     



    @Override
    public String toString() {
        return id + " " + name;
    }

    /**
     * @Override
    public int compareTo(Foods o) {

    //It will insert duplicate by id, not name
      int idCompare = Integer.compare(this.id, o.id);

       if (idCompare != 0) {
        return idCompare;
        }

    return this.name.compareTo(o.name);
    }
     */
}