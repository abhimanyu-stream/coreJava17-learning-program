package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class FromListOfStringFindMostRepeatedIPUsingStreamAPI {

    public static void main(String[] args) {


        // Get the most repeated IP address from below list, solution should be generic. In below example 192.168.1.102 is the most repeated IP.
        List<String> list = Arrays.asList(
                "192.168.1.102- com.ab.c.Abc abc xyz",
                "192.168.1.102- com.ab.c.ajd ewe wer",
                "192.168.1.107- com.ab.c.Sss ewr whr",
                "192.168.1.107- com.ab.c.AAd jkj sds",
                "192.168.1.106- com.ab.c.Aads data sub",
                "192.168.1.106- com.ab.c.Abad dfsdL jhj",
                "192.168.1.104- com.ab.c.Esdsc hdsa sa",
                "192.168.1.105- com.ab.c.Aabc a idi ew",
                "192.168.1.100- com.ab.c.Dbc sf hfjs",
                "192.168.1.101- com.ab.c.Hebc adssa sa",
                "192.168.1.102- com.ab.c.Kbc asds asf");

        Map<String, Integer> ipCountMap = new HashMap<>();

        for (String entry : list) {
            String[] parts = entry.split("-");



            //System.out.println(parts[0]);
            //System.out.println(parts[1]);
            String ipAddress = parts[0].trim(); // Extract the IP address
            ipCountMap.put(ipAddress, ipCountMap.getOrDefault(ipAddress, 0) + 1);
        }

        //System.out.println(mostRepeatedIPByStreamAPI);
        // Find the IP address with the highest occurrence count
        String mostRepeatedIP = Collections.max(ipCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println("Most repeated IP address: " + mostRepeatedIP);
    }
}