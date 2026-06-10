package com.java17.interview.prepartion;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapWithComparator{
    //TreeMap stores keys in sorted Red-Black Tree.

    public static void main(String[] args) {


         // Custom comparator by name
        Comparator<Pencil> nameComparator = (e1, e2) -> e1.getName().compareTo(e2.getName());

        /**
         * Comparator<Employee> nameComparator = new Comparator<Employee>() {
             @Override
            public int compare(Employee e1, Employee e2) {
            return e1.name.compareTo(e2.name);
                }
            };
         */

            /**
             * 
             * Best modern Comparator examples
            Sort by name
            Comparator<Employee> c = Comparator.comparing(e -> e.name);
            Sort by ID descending
            Comparator<Employee> c =
            Comparator.comparingInt((Employee e) -> e.id).reversed();
            Sort by name then ID
            Comparator<Employee> c =
            Comparator.comparing((Employee e) -> e.name)
                  .thenComparingInt(e -> e.id);

            This is what you’ll use in production.



             */




        TreeMap<Pencil, String> map = new TreeMap<>(nameComparator);

        map.put(new Pencil(1, "one"), "one");
        map.put(new Pencil(2, "two"), "two");
        map.put(new Pencil(3, "three"), "three");
        map.put(new Pencil(1, "one"), "one");


        System.out.println(map);


        for (Pencil e : map.keySet()) {
            System.out.println(e + " -> " + map.get(e));
        }




        
    }

    /**
     * 
     * Interview Question: Why TreeMap needs Comparable/Comparator?

        TreeMap stores keys in sorted Red-Black Tree.

        When inserting:

        map.put(key, value)

        TreeMap must decide:

        Go left?
        Go right?
        Is key duplicate?

        It uses:

        compareTo()   OR   Comparator.compare()

        Without comparison:

        TreeMap<Employee, String> map = new TreeMap<>();

        If Employee does NOT implement Comparable and no Comparator is given:

        Runtime Error
        java.lang.ClassCastException

        because TreeMap cannot sort keys.

     */


    
}


class Pencil {

    private Integer id;
    private String name;
   

    public Pencil(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hotel other = (Hotel) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Pencil [id=" + id + ", name=" + name + "]";
    }

    
   

}