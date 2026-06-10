package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseStringInPlaceOrFullOrOderWise {

    public static void main(String[] args) {
        //Reverse
        //Input : Java Coding Series
        //Its In Place reverse
        //Output: avaJ gnidoC series



        String str = "Java Coding Series";

        String[] words = str.split(" ");//Convert str into String[] using split(" ")

        for (String word : words) {
            System.out.println(word);
        }
        ////Convert str into String[] using split("\\s+") one or more space
        String[] words2 = str.split(",\\s*");//Convert str into String[] using split("\\s*") zero or more space
        for (String word : words2) {
            System.out.println(word);
        }


        // String Full reverse  .mapToObj(i -> str.charAt(str.length() - 1 - i))
        String fullReverse = IntStream.range(0, str.length())
                .mapToObj(i -> str.charAt(str.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(fullReverse);//seireS gnidoC avaJ


        String input2 ="racecar";

       String out = IntStream.range(0, input2.length())
        .mapToObj(i->input2.charAt(input2.length() - 1 - i))
        .map(String::valueOf)
        .collect(Collectors.joining()); 

        System.out.println("isPalindrome" + input2.equals(out));
        /**
         * Interview Tip
         *
         * If interviewer says:
         *
         * "Reverse string" → full reverse
         * "Reverse words in place" → reverse each word, keep positions same
         * "Reverse word order" → words order reversed
         *
         * Example:
         *
         * Input:  Java Coding Series
         *
         * 1. Full reverse → seireS gnidoC avaJ
         * 2. In-place word reverse → avaJ gnidoC seireS
         * 3. Word order reverse → Series Coding Java
         */


        //Correct Approach (Reverse each word in-place)
        //reverse each word, keep positions same
        String reverseEachWordKeepPositionsSame = Arrays.stream(str.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        System.out.println("reverseEachWordKeepPositionsSame "+reverseEachWordKeepPositionsSame);//avaJ gnidoC seireS












        String result = Arrays.stream(str.split(" "))// String[] of str
                .map(word -> IntStream.range(0, word.length())
                        .mapToObj(i -> word.charAt(word.length() - 1 - i))
                        .map(String::valueOf)
                        .collect(Collectors.joining()))//close map
                .collect(Collectors.joining(" "));
        System.out.println("result reverseEachWordKeepPositionsSame "+result);


















        //3WordOrderReverse
        List<String> words5 = Arrays.asList(str.split(" "));//Converting in List<String> of given str using Arrays.asList()
        Collections.reverse(words5);
        System.out.println("word5 "+words5);

        String WordOrderReverse = words5.stream()
                .collect(Collectors.joining(" "));

        System.out.println(" WordOrderReverse "+WordOrderReverse);//Series Coding Java







        //Input: "Hello, world!  How are you?"
        //
        //    Output: "you? are How  world! Hello,"
        //


        
        String str23 = "Hello, world!  How are you?";

        String mirrorString = Arrays.stream(str23.split(" "))
                .collect(Collectors.collectingAndThen(
                                Collectors.toList(),
                                listt -> {
                                    Collections.reverse(listt);
                                    return String.join(" ", listt);

                                }
                        )
                );


        System.out.println("Mirror Image of String Order reversed "+ mirrorString);

        String mirrorString2 = Arrays.stream(str23.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(ArrayList::new),
                        list -> {
                            Collections.reverse(list);
                            return String.join(" ", list);
                        }
                ));

        System.out.println("Mirror Image of String 2 "+ mirrorString2);













        String str55 = "Java is powerful";

        String[] words55 = str55.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = words55.length - 1; i >= 0; i--) {
            sb.append(words55[i]);

            if (i != 0) {
                sb.append(" ");
            }
        }

        System.out.println("str55 " +sb.toString());


        String str56 = "Java is powerful";

        List<String> words56 = new ArrayList<>(Arrays.asList(str56.split(" ")));

        Collections.reverse(words56);

        String reversed = String.join(" ", words56);

        System.out.println("word56 "+reversed);


        //Other
        String str57 = "Java is powerful";
        String reversed57 = Arrays.stream(str57.split(" "))
                .reduce((a, b) -> b + " " + a)
                .orElse("");

        System.out.println("reversed57 "+reversed57);

    }
}
/**
 * 4. Using Stack
 * String str = "Java is powerful";
 *
 * Stack<String> stack = new Stack<>();
 *
 * for (String word : str.split(" ")) {
 *     stack.push(word);
 * }
 *
 * StringBuilder sb = new StringBuilder();
 *
 * while (!stack.isEmpty()) {
 *     sb.append(stack.pop());
 *
 *     if (!stack.isEmpty()) {
 *         sb.append(" ");
 *     }
 * }
 *
 * System.out.println(sb.toString());
 * 5. Using Deque (Recommended over Stack)
 * String str = "Java is powerful";
 *
 * Deque<String> deque = new ArrayDeque<>();
 *
 * for (String word : str.split(" ")) {
 *     deque.push(word);
 * }
 *
 * String reversed = String.join(" ", deque);
 *
 * System.out.println(reversed);
 *
 */