package com.java17.interview.prepartion;

public class PrintStringInReverseOrder {

    public static void main(String[] args) {


        String str = "the java developer jobs";


        String[] splitted = str.split(" ");
        StringBuffer buffer = new StringBuffer(splitted.length);
        for(int last = splitted.length - 1; last >=0; last--){
            buffer.append(splitted[last]).append(" ");

        }
        System.out.println(buffer);

    }
}
