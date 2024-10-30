package com.java17.interview.prepartion;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapFailSafe {

    public static void main(String args[]){


        Map<Integer,String> concurrentHashMap=new ConcurrentHashMap<Integer,String>();

        concurrentHashMap.put(11, "ankit");
        concurrentHashMap.put(21, "javaMadeSoEasy");

        /**
         *
         * ConcurrentHashMap - Iterator on keySet, values and  entrySet is  fail-safe or fail-fast?
         *
         * The iterators returned by the iterator() method of the collections returned by all three Map's “collection view methods" are fail-safe. Means any structural modification made to ConcurrentHashMap like adding or removing elements during Iteration won’t throw any Exception.
         *
         *
         *
         * Note:--these are  all write operations.
         * public V put(K key, V value) {
         *         // Gets the Segment, and calls the put method implemented within that segment.
         *     }
         *
         *     public V remove(Object key) {
         *         // Gets the Segment, and calls the remove method implemented within that segment.
         *     }
         *
         *     public boolean replace(K key, V oldValue, V newValue) {
         *          // Gets the Segment, and calls the replace method implemented within that segment.
         *     }
         *
         *     public void clear() {
         *         // Gets the Segment, and calls the clear method implemented within that segment.
         *     }
         * */


        System.out.println("\n---1. Iterate on keys, by obtaining iterator on keySet---");


        //fail-safe
        Iterator<Integer> keyIterator=concurrentHashMap.keySet().iterator();
        while(keyIterator.hasNext()){
            concurrentHashMap.put(4,"newEle1");
            System.out.println("concurrentHashMap inside 1"+concurrentHashMap);
            System.out.println(keyIterator.next());

        }
        System.out.println("concurrentHashMap 1"+concurrentHashMap);


        System.out.println("\n---2. Iterate on values, by obtaining iterator on values---");



        //fail-safe

        Iterator<String> valueIterator=concurrentHashMap.values().iterator();
        while(valueIterator.hasNext()){
            concurrentHashMap.put(4,"newEle2");
            concurrentHashMap.put(5,"newEle5");
            System.out.println("concurrentHashMap inside 2"+concurrentHashMap);
            System.out.println(valueIterator.next());
        }
        System.out.println("concurrentHashMap 2"+concurrentHashMap);
        System.out.println("\n---3. Iterate on entry, by obtaining iterator on entrySet---");



        //fail-safe

        Iterator<Map.Entry<Integer, String>> entryIterator=concurrentHashMap.entrySet().iterator();
        while(entryIterator.hasNext()){
            concurrentHashMap.put(4,"newEle3");
            concurrentHashMap.put(6,"newEle6");
            System.out.println("concurrentHashMap inside 3"+concurrentHashMap);
            System.out.println(entryIterator.next());
        }
        System.out.println("concurrentHashMap 3"+concurrentHashMap);
    }
}
