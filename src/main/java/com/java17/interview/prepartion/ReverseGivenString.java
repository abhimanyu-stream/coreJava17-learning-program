package com.java17.interview.prepartion;

import java.nio.charset.StandardCharsets;

public class ReverseGivenString {

	public static void main(String[] args) {


        String s = "ejb";
        char[] inputString = s.toCharArray();
        System.out.println(inputString);
        char[] reversedString = new char[s.length()];
        int x = s.length() - 1;

        //System.out.println("x length " +  x);
        //System.out.println("s length " +  s.length());
        for (int i = 0; i < s.length(); i++) {// s.length() [ for length start count from 1]
            //System.out.println("inputString[x] " +  inputString[x]);
            //reversedString[i] = inputString[x];// as Array works from 0 onwards index so 0, 1, 2 all indexes are accessed [ for index start count from 0]
            reversedString[i] = inputString[s.length() - 1 - i];

            //x--;
        }
        System.out.println(reversedString);




        System.out.println("Using byte[] implementation");
        byte[] b = s.getBytes(StandardCharsets.UTF_8);
        byte[] reversedByteArray = new byte[s.length()];

        int x2 = b.length - 1;
        for (int i = 0; i < b.length; i++) {// b.length
            //reversedByteArray[i] = b[s.length() - 1 - i];
            reversedByteArray[i] = b[x2];
            x2--;
        }
        System.out.println("byte[] : " + new String(reversedByteArray));

    }

}
