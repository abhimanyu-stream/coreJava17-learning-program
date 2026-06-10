package com.java17.interview.prepartion;

import java.util.TreeMap;

public class TreeMapWithComparable{

    public static void main(String[] args){

        TreeMap<Hotel, String> map = new TreeMap<>();
        map.put(new Hotel(1, "YKD"), "YKD");
        map.put(new Hotel(2, "PLO"), "PLO");
        map.put(new Hotel(3, "MKU"), "MKU");
        map.put(new Hotel(1, "ERT"), "ERT");

        System.out.println(map);

        




    }


}

class Hotel implements  Comparable<Hotel>{

    private Integer id;
    private String name;
    @Override
    public int compareTo(Hotel o) {
        int idCompare = Integer.compare(this.id, o.id);

        //TreeMap uses compareTo(), NOT equals() / hashCode()

//If compareTo() returns 0, TreeMap treats keys as duplicate

    if (idCompare != 0) {
        return idCompare;
    }

    return this.name.compareTo(o.name);

    }

    /**
     * 
     * @Override
    public int compareTo(Hotel o) {
    return Comparator.comparing(Hotel::getId)
                     .thenComparing(Hotel::getName)
                     .compare(this, o);
}
     */
    public Hotel(Integer id, String name) {
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
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Hotel [id=" + id + ", name=" + name + "]";
    }

    
   

}