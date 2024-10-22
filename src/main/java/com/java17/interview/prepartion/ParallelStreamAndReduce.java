package com.java17.interview.prepartion;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class ParallelStreamAndReduce {
    public static void main(String[] args) {



        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 20);
        int sum = listOfNumbers.parallelStream().reduce(0, Integer::sum) + 5;
        System.out.println(sum);// [1 + 2 + 3 + 4 + 20] + 5 = 35



        // creating a list of Strings
        List<String> words = Arrays.asList("GFG", "Geeks", "for",
                "GeeksQuiz", "GeeksforGeeks");

        // The lambda expression passed to
        // reduce() method takes two Strings
        // and returns the longer String.
        // The result of the reduce() method is
        // an Optional because the list on which
        // reduce() is called may be empty.
        Optional<String> longestString = words.stream()
                .reduce((word1, word2)
                        -> word1.length() > word2.length()
                        ? word1 : word2);

        // Displaying the longest String
        longestString.ifPresent(System.out::println);



        // String array
        String[] array = { "Geeks", "for", "Geeks" };

        // The result of the reduce() method is
        // an Optional because the list on which
        // reduce() is called may be empty.
        Optional<String> String_combine = Arrays.stream(array)
                .reduce((str1, str2)
                        -> str1 + "-" + str2);

        // Displaying the combined String
        if (String_combine.isPresent()) {
            System.out.println(String_combine.get());
        }

        // Creating list of integers
        List<Integer> arrayOfInt = Arrays.asList(-2, 0, 4, 6, 8);

        // Finding sum of all elements
        int sumResult = arrayOfInt.stream().reduce(0,
                (element1, element2) -> element1 + element2);

        // Displaying sum of all elements
        System.out.println("The sum of all elements is " + sumResult);

        // To get the product of all elements
        // in given range excluding the
        // rightmost element
        int product = IntStream.range(2, 4)
                .reduce((num1, num2) -> num1 * num2)
                .orElse(-1);

        // Displaying the product
        System.out.println("The product is : " + product);


        // Creating a list of integers
        List<Integer> list = Arrays.asList(-9, -18, 0, 25, 4);

        System.out.print("The maximum value is : ");

        // Using stream.max() to get maximum
        // element according to provided Comparator
        // and storing in variable var
        //  Integer var = list.stream().max(Integer::compare).get();
        Integer var = list.stream().max(Comparator.comparing(i -> Integer.parseInt(i.toString()))).get();

        System.out.print(var);

        // Creating a list of Strings
        List<String> listOfString = Arrays.asList("G",  "z", "Z");

        // using Stream.max() method with Comparator
        // Here, the character with maximum ASCII value
        // is stored in variable MAX
        String MAX = listOfString.stream().max(Comparator.
                comparing(String::valueOf)).get();

        // Displaying the maximum element in
        // the stream according to provided Comparator
        System.out.println();

        System.out.println("Maximum element in the "
                + "stream is : " + MAX);

        // creating an array of strings
        String[] arrayOfString = { "Geeks", "for", "GeeksforGeeks",
                "GeeksQuiz" };

        // Here, the Comparator compares the strings
        // based on their last characters and returns
        // the maximum value accordingly
        // The result is stored in variable MAX
        Optional<String> maxString = Arrays.stream(arrayOfString).max((str1, str2) ->
                Character.compare(str1.charAt(str1.length() - 1),
                        str2.charAt(str2.length() - 1)));

        // If a value is present,
        // isPresent() will return true
        if (maxString.isPresent())
            System.out.println(maxString.get());
        else
            System.out.println("-1");


        // using Stream.generate() method
        // to generate 5 random Integer values
        Stream.generate(new Random()::nextInt).limit(5).forEach(System.out::println);

    }
    private static void parallelStreamProcess() throws ExecutionException, InterruptedException {

        int start = 1;
        int end = 10000;

        List<Integer> intList = IntStream.rangeClosed(start, end).boxed()
                .collect(Collectors.toList());
        System.out.println(intList.size());

        ForkJoinPool newCustomThreadPool = new ForkJoinPool(5);
        int actualTotal = newCustomThreadPool.submit(
                () -> {
                    int a = intList.stream().parallel().reduce(0, Integer::sum).intValue();
                    return a;
                }).get();
        newCustomThreadPool.shutdown();

        System.out.println("actualTotal " + actualTotal);

    }


}
