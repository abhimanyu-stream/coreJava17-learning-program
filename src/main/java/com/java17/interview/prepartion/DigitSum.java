package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class DigitSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        scanner.close();

        int sum = 0;
        int temp = number;
        List<Integer> intList = new ArrayList<>();
        int i = 0;
        while (temp > 0) {
            int lastDigit = temp % 10;
            intList.add(lastDigit) ;
            i++;
            temp /= 10;

            //System.out.println("temp: " + temp);
        }
        System.out.println("intList: " + intList);
        // reverse list first

        Collections.reverse(intList);
        System.out.println("Reversed intList: " + intList);
        int listSize = intList.size();
        //intList =  intList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        //System.out.println("intList: " + intList);
        List<Integer> outputList = new ArrayList<>();
        int toBeAdded = intList.size();
        while(toBeAdded > 0){
            for (int j = 0; j < listSize; j++){
                //System.out.println(intList.get(j));

                int value = intList.get(j) + toBeAdded;
                toBeAdded--;
                //System.out.println("value " + value);
                //System.out.println("toBeAddedNext " + toBeAdded);

                if(value > 9){
                    value = value / 10;
                    outputList.add(value);
                    //System.out.println(outputList);
                }else{
                    outputList.add(value);
                    //System.out.println(outputList);
                }



            }// end for loop
        }
        //outputList = outputList.stream().collect(Collectors.toList());
        System.out.println(outputList);
        //Enter a positive integer: 7982
        //intList: [2, 8, 9, 7]
        //[8, 6, 1, 1]


        /*
        * "C:\Program Files\Java\jdk-17\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2022.2\lib\idea_rt.jar=55889:C:\Program Files\JetBrains\IntelliJ IDEA 2022.2\bin" -Dfile.encoding=UTF-8 -classpath E:\ws-abhimanyukumar\corejava17programs\target\classes;C:\Users\acode\.m2\repository\org\springframework\boot\spring-boot-starter\3.2.2\spring-boot-starter-3.2.2.jar;C:\Users\acode\.m2\repository\org\springframework\boot\spring-boot\3.2.2\spring-boot-3.2.2.jar;C:\Users\acode\.m2\repository\org\springframework\spring-context\6.1.3\spring-context-6.1.3.jar;C:\Users\acode\.m2\repository\org\springframework\spring-aop\6.1.3\spring-aop-6.1.3.jar;C:\Users\acode\.m2\repository\org\springframework\spring-beans\6.1.3\spring-beans-6.1.3.jar;C:\Users\acode\.m2\repository\org\springframework\spring-expression\6.1.3\spring-expression-6.1.3.jar;C:\Users\acode\.m2\repository\io\micrometer\micrometer-observation\1.12.2\micrometer-observation-1.12.2.jar;C:\Users\acode\.m2\repository\io\micrometer\micrometer-commons\1.12.2\micrometer-commons-1.12.2.jar;C:\Users\acode\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\3.2.2\spring-boot-autoconfigure-3.2.2.jar;C:\Users\acode\.m2\repository\org\springframework\boot\spring-boot-starter-logging\3.2.2\spring-boot-starter-logging-3.2.2.jar;C:\Users\acode\.m2\repository\ch\qos\logback\logback-classic\1.4.14\logback-classic-1.4.14.jar;C:\Users\acode\.m2\repository\ch\qos\logback\logback-core\1.4.14\logback-core-1.4.14.jar;C:\Users\acode\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.21.1\log4j-to-slf4j-2.21.1.jar;C:\Users\acode\.m2\repository\org\apache\logging\log4j\log4j-api\2.21.1\log4j-api-2.21.1.jar;C:\Users\acode\.m2\repository\org\slf4j\jul-to-slf4j\2.0.11\jul-to-slf4j-2.0.11.jar;C:\Users\acode\.m2\repository\jakarta\annotation\jakarta.annotation-api\2.1.1\jakarta.annotation-api-2.1.1.jar;C:\Users\acode\.m2\repository\org\springframework\spring-core\6.1.3\spring-core-6.1.3.jar;C:\Users\acode\.m2\repository\org\springframework\spring-jcl\6.1.3\spring-jcl-6.1.3.jar;C:\Users\acode\.m2\repository\org\yaml\snakeyaml\2.2\snakeyaml-2.2.jar;C:\Users\acode\.m2\repository\org\slf4j\slf4j-api\2.0.11\slf4j-api-2.0.11.jar com.java17.interview.prepartion.DigitSum
Enter a positive integer: 7634
intList: [4, 3, 6, 7]
Reversed intList: [7, 6, 3, 4]
7
value 11
toBeAddedNext 3
[1]
6
value 9
toBeAddedNext 2
[1, 9]
3
value 5
toBeAddedNext 1
[1, 9, 5]
4
value 5
toBeAddedNext 0
[1, 9, 5, 5]
[1, 9, 5, 5]
        * */

    }
}
/*
*
* Explanation:

We read the input number (7832).
We iterate through the digits from right to left.
For each digit (except the last one), we add it to the adjacent digit.
The final sum is printed (7+4 + 8+3 + 3+2 + 2+1 = 1 + 1 + 5 + 3 = 10 + 8 = 18).
Feel free to modify the input number and test the program with different values! 🚀*/