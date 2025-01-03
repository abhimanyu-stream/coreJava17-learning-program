package com.java17.interview.prepartion;

import java.nio.charset.StandardCharsets;

public class ReverseGivenString {

	public static void main(String[] args) {

// char by char approach
        //length count start from 1
        // element are placed via index outing from 0
        //String s = "ejb";
        String s = "racecar";
        char[] charArray = s.toCharArray();
        //System.out.println(inputString);
        char[] reversedcharArray = new char[s.length()];
        //int x = s.length() - 1;//last element of String s

        for (int i = 0; i < charArray.length; i++) {// s.length() [ for length start count from 1]

            //reversedString[i] = inputString[x];// as Array works from 0 onwards index so 0, 1, 2 all indexes are accessed [ for index start count from 0]
            reversedcharArray[i] = charArray[s.length() - 1 - i];

            //x--;
        }
        System.out.println("reversedString "+new String(reversedcharArray));
        if(new String(reversedcharArray).equals(s)){
            System.out.println("palindrome");
        }






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






        char c='S';
        String s2=String.valueOf(c);
        System.out.println("String is: "+s2);

        char c2='M';
        String s3=Character.toString(c2);
        System.out.println("String is: "+s3);

    }

    // To convert a character array to a string
    // using the StringBuilder class
    public static String toString(char[] a)
    {
        // Creating object of String class
        StringBuilder sb = new StringBuilder();

        // Creating a string using append() method
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
        }

        return sb.toString();
    }
    /***
     * StringBuffer is synchronized, meaning its methods are thread-safe and can be safely used in a multithreaded environment.
     * On the other hand, StringBuilder is not synchronized, which makes it faster than StringBuffer, but it is not thread-safe and should not be used in a multithreaded environment
     * */

}
