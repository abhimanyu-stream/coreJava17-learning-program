package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamBasedReverseProblems {
	
	public static void main(String[] args) {

        String input = "I, am a Java Developer";

        // ============================================================
        // 1. MIRROR / REVERSE WORD ORDER
        // ============================================================
        // Input  : I, am a Java Developer
        // Output : Developer Java a am I,

        String mirrorString =
                Arrays.stream(input.split(" "))
                        .collect(Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Collections.reverse(list);
                                    return String.join(" ", list);
                                }
                        ));

        System.out.println("1. Mirror String : " + mirrorString);


        // ============================================================
        // 2. FULL REVERSE
        // ============================================================
        // Input  : I, am a Java Developer
        // Output : repoleveD avaJ a ma ,I

        String fullReverse =
                IntStream.range(0, input.length())
                        .mapToObj(i -> input.charAt(input.length() - 1 - i))
                        .map(String::valueOf)
                        .collect(Collectors.joining());

        System.out.println("2. Full Reverse  : " + fullReverse);


        // ============================================================
        // 3. REVERSE EACH WORD AT ITS PLACE
        // ============================================================
        // Input  : I, am a Java Developer
        // Output : ,I ma a avaJ repoleveD

        String reverseEachWord =
                Arrays.stream(input.split(" "))
                        .map(word ->
                                IntStream.range(0, word.length())
                                        .mapToObj(i ->
                                                word.charAt(word.length() - 1 - i))
                                        .map(String::valueOf)
                                        .collect(Collectors.joining())
                        )//map
                        .collect(Collectors.joining(" "));

        System.out.println("3. Reverse Words : " + reverseEachWord);
    }

}
