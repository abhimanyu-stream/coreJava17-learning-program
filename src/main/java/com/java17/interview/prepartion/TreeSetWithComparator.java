package com.java17.interview.prepartion;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetWithComparator {
    public static void main(String[] args) {
       

        Comparator<Copy> nameComparator = (e1, e2) -> e1.getName().compareTo(e2.getName());

        TreeSet<Copy> employees = new TreeSet<>(nameComparator);

        employees.add(new Copy(3, "John"));
        employees.add(new Copy(1, "Alice"));
        employees.add(new Copy(2, "Bob"));

        System.out.println(employees);
    }

    /**  TreeMap stores keys in sorted Red-Black Tree.
     * TreeSet vs HashSet
        Feature	              HashSet	TreeSet
        Order	               No	    Sorted
        Duplicate	           No	     No
        Performance	         O(1) avg	O(log n)
        Internal DS	         HashMap	Red-Black Tree
        Interview one-line answer

        TreeSet = Sorted Set + No duplicates + Red-Black Tree + 
        O(log n)
     */
    
}

class Copy{

    private int id;
    private String name;





    
    public Copy(int id, String name) {
        this.id = id;
        this.name = name;
    }



    @Override
    public String toString() {
        return "Copy [id=" + id + ", name=" + name + "]";
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}
