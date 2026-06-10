package com.java17.interview.prepartion;

public class MapVariousMethods{
    public static void main(String[] args) {

        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        // getOrDefault - Safe Read
        int value = map.getOrDefault("c", -1); // returns -1 as "c" is missing
        System.out.println(value);

        // computeIfAbsent - Create Value
        map.computeIfAbsent("d", k -> 4); // inserts "d" with value 4
        System.out.println(map);

        // computeIfPresent - Update Value
        map.computeIfPresent("a", (k, v) -> v * 50); // updates "a" with value 2
        System.out.println(map);

        // merge - Smart Combine
        map.merge("b", 3, Integer::sum); // combines values of "b" (2+1=3)
        System.out.println(map);//5

         map.merge("b", 5, (v1, v2) -> v1 * v2);
        System.out.println(map);


        
    }
}

/**
 * 
 * FINAL COMPARISON (INTERVIEW GOLD TABLE)
Method	Purpose	Key Exists	Key Missing	Old Equivalent
getOrDefault	Safe read	return value	return default	if-null check
computeIfAbsent	create value	do nothing	insert value	containsKey + put
computeIfPresent	update value	update	ignore	containsKey + update
merge	smart combine	combine values	insert value	if-else full logic
🚀 SIMPLE MEMORY TRICK
getOrDefault → READ
computeIfAbsent → CREATE
computeIfPresent → UPDATE
merge → CREATE + UPDATE (SMART)

 */