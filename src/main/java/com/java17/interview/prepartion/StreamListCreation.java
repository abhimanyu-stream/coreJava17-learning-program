package com.java17.interview.prepartion;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamListCreation{


    public static void main(String[] args) { 
       // =========================================================
        // 1. Collectors.toList()
        // =========================================================
        /*
         * Creates a List using the Stream Collector.
         *
         * IMPORTANT:
         * The Java API does NOT guarantee the exact List implementation
         * or guarantee mutability.
         *
         * In normal JDK implementations, this produces a mutable List.
         */

        List<Integer> list1 = Stream.of(10, 20, 30)
                .collect(Collectors.toList());

        System.out.println("list1 = " + list1);

        // Usually works
        list1.add(40);

        System.out.println("After add = " + list1);


        // =========================================================
        // 2. Collectors.toUnmodifiableList()
        // =========================================================
        /*
         * Creates an UNMODIFIABLE List.
         *
         * add(), remove(), set(), etc. are not allowed.
         */

        List<Integer> list2 = Stream.of(10, 20, 30)
                .collect(Collectors.toUnmodifiableList());

        System.out.println("\nlist2 = " + list2);

        // ❌ Throws UnsupportedOperationException
        // list2.add(40);


        // =========================================================
        // 3. Stream.toList()
        // =========================================================
        /*
         * Available from Java 16.
         *
         * Returns an UNMODIFIABLE List.
         */

        List<Integer> list3 = Stream.of(10, 20, 30)
                .toList();

        System.out.println("\nlist3 = " + list3);

        // ❌ Throws UnsupportedOperationException
        // list3.add(40);


        // =========================================================
        // 4. Collectors.toCollection(ArrayList::new)
        // =========================================================
        /*
         * Explicitly tells Java:
         *
         * "Create an ArrayList and collect the stream elements into it."
         *
         * Therefore, we KNOW the result is an ArrayList.
         *
         * ArrayList is mutable.
         */

        List<Integer> list4 = Stream.of(10, 20, 30)
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("\nlist4 = " + list4);

        // ✅ Guaranteed to work
        list4.add(40);

        System.out.println("After add = " + list4);


        // =========================================================
        // 5. new ArrayList<>(...)
        // =========================================================
        /*
         * First create an unmodifiable List using List.of().
         *
         * Then create a NEW ArrayList from it.
         *
         * The ArrayList is mutable.
         */

        List<Integer> list5 = new ArrayList<>(
                List.of(10, 20, 30)
        );

        System.out.println("\nlist5 = " + list5);

        // ✅ ArrayList is mutable
        list5.add(40);

        System.out.println("After add = " + list5);


        // =========================================================
        // 6. List.of()
        // =========================================================
        /*
         * List.of() creates an UNMODIFIABLE List.
         *
         * It was introduced in Java 9.
         */

        List<Integer> list6 = List.of(10, 20, 30);

        System.out.println("\nlist6 = " + list6);

        // ❌ Throws UnsupportedOperationException
        // list6.add(40);


        // =========================================================
        // 7. Arrays.asList()
        // =========================================================
        /*
         * Arrays.asList() creates a fixed-size List backed by the array.
         *
         * You CANNOT add or remove elements.
         *
         * But you CAN replace an existing element using set().
         */

        List<Integer> list7 = Arrays.asList(10, 20, 30);

        System.out.println("\nlist7 = " + list7);

        // ❌ Cannot change the size
        // list7.add(40);

        // ❌ Cannot change the size
        // list7.remove(0);

        // ✅ Existing element can be replaced
        list7.set(0, 100);

        System.out.println("After set = " + list7);
    }

}