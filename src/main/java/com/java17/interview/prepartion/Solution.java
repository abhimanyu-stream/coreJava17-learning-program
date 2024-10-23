package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;


/*
Character Frequency Count
Given an input string S, output a string containing each character and its frequency in the given string S.

Example:
Input string : "aaaabbcddee"
Output string : "a4b2c1d2e2"//charArray[i]


Input string : "aaba"
Output string : "a3b1"

*/

/* Ensure the solution caters for strings where the characters can be repeated later in the string */
public class Solution {

	public static String charFreqCount(String inputStr) {
		// Your code here

		char[] charArray = inputStr.toCharArray();

		Map<Character, Integer> hashMap = new HashMap<>();

		for(int i = 0; i < inputStr.length() ; i++){

			//int count;
			if(hashMap.containsKey(inputStr.charAt(i))){
				Integer count = hashMap.get(charArray[i]);
				System.out.println(charArray[i] +" ----" +  count);
				hashMap.put(charArray[i], count +  1);

			}else{
				hashMap.put(charArray[i],1);
			}

			System.out.println(hashMap);
			StringBuffer bf = new StringBuffer();
			for(Map.Entry<Character, Integer> m:hashMap.entrySet()){

				bf.append( m.getKey().toString()+m.getValue());

			}
			System.out.println(bf);
		}



		return inputStr;
	}

	// you can change anything
	public static void main(String[] args) {
		String s= "aaaabbcddee";
		charFreqCount(s);

		//JUnitCore.main("Solution");
	}



}
	/*@Test
	void contextLoads() {
	}
	@Test
	public void test1() {
		String input = "aaaabbcddee";
		String expected = "a4b2c1d2e2";
		String actual = charFreqCount(input);
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		String input = "aaaabbcddb";
		String expected = "a4b3c1d2";
		String actual = charFreqCount(input);
		assertEquals(expected, actual);
	}*/
