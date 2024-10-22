package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LongestCommonSubstring {




    public static String findLCS(String x, String y) {
        int m = x.length();
        int n = y.length();

        //to compensate for additional row and column with 0
        int[][] lcsTable = new int[m + 1][n + 1];

        // to find the maximum length
        int maxLength = 0;
        int maxRow = 0;
        int maxColumn = 0;

        // init first row with 0
        for (int i = 0; i < m; i++) {
            lcsTable[i][0] = 0;
        }

        // init first col with 0
        for (int j = 0; j < n; j++) {
            lcsTable[0][j] = 0;
        }

        // starting from 1 as row 0 and col 0 filled with 0. <= since it has go up to string length.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    lcsTable[i][j] = 1 + lcsTable[i - 1][j - 1];
                    if (maxLength < lcsTable[i][j]) {
                        maxLength = lcsTable[i][j];
                        maxRow = i;
                        maxColumn = j;
                    }
                } else {
                    lcsTable[i][j] = 0;
                }
            }
        }
        return fetchLCS(x, maxLength, maxRow, maxColumn);

    }

    private static String fetchLCS(String x, int maxLength, int maxRow, int maxColumn) {
        System.out.println("The length of longest common substring is: " + maxLength);

        System.out.println("Max Row is: " + maxRow);
        System.out.println("Max Column is: " + maxColumn);

        StringBuilder longestCommonSubstring = new StringBuilder(maxLength);

        while (maxLength > 0) {
            longestCommonSubstring.append(x.charAt(maxRow - 1));
            maxRow--;
            maxLength--;
        }

        return longestCommonSubstring.reverse().toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(LongestCommonSubstring.class,args);
        String x = "tiktok";
        String y = "ticktock";

        String longestCommonSubstring = findLCS(x, y);

        System.out.println("The longest common substring is " + longestCommonSubstring);



        String[] newArray = {"Amazon", "Amazone", "AmazonIn"};


        String result = getLargestCommonStringLiterals(newArray);
        System.out.println(" result :" + result);


    }

    public static String getLargestCommonStringLiterals(String[] newArray){

        if(newArray.length < 0)
            return "";

        String prefix = newArray[0];
        for(int i = 1; i < newArray.length; i++){

            while(newArray[i].indexOf(prefix) != 0 ){

                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";

            }
        }
        return prefix;
    }
}
