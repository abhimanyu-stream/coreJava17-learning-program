package com.java17.interview.prepartion;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class TreeMapSortingMechanismDeepUnderstanding {

    public static void main(String[] args) {

        Map<UserKanak, String> mapNaturalSort = new TreeMap<>();

        mapNaturalSort.put(new UserKanak(3, "Aman"), "User3");
        mapNaturalSort.put(new UserKanak(1, "Ravi"), "User1");
        mapNaturalSort.put(new UserKanak(2, "Neha"), "User2");

        mapNaturalSort.forEach((k, v) -> System.out.println(k + " => " + v));
        /**
         *
         * Output (Sorted by id)
         * 1 - Ravi => User1
         * 2 - Neha => User2
         * 3 - Aman => User3
         */


        Map<UserKanak, String> mapCustomSort = new TreeMap<>(
                //Comparator.comparing(u -> u.name) it can cause Comparator Risk: Duplicate Keys by Name
                //(u1, u2) -> u1.name.compareTo(u2.name)

                Comparator.comparing((UserKanak u) -> u.name)
                        .thenComparing(u -> u.id)
        );

        mapCustomSort.put(new UserKanak(3, "Aman"), "User3");
        mapCustomSort.put(new UserKanak(1, "Ravi"), "User1");
        mapCustomSort.put(new UserKanak(2, "Neha"), "User2");

        mapCustomSort.forEach((k, v) -> System.out.println(k + " => " + v));
        /**
         * Output (Sorted by name)
         * 3 - Aman => User3
         * 2 - Neha => User2
         * 1 - Ravi => User1
         */

    }
}
class UserKanak implements Comparable<UserKanak> {
    int id;
    String name;

    public UserKanak(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Natural ordering (by id)
    @Override
    public int compareTo(UserKanak o) {
        //return this.id - o.id; it can cause Integer.MAX_VALUE - (-1)  // overflow
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserKanak)) return false;
        UserKanak u = (UserKanak) o;
        return id == u.id && Objects.equals(name, u.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
