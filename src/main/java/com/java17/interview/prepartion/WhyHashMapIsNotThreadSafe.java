package com.java17.interview.prepartion;

public class WhyHashMapIsNotThreadSafe {
    public static void main(String[] args) {
        /**
         * Explanation
         *
         * Concurrent resize can corrupt bucket chain
         *
         * Leads to infinite loop
         *
         * JVM CPU spikes to 100%
         *
         * Classic corruption scenario
         *
         * Two threads rehash at same time
         *
         * Linked list becomes cyclic
         *
         * get() never terminates
         *
         * 👉 This actually caused real production outages pre-Java 8.
         */
    }
}
