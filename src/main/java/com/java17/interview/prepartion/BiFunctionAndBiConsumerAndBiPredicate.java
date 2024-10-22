package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BiFunctionAndBiConsumerAndBiPredicate {

    public static void main(String[] args) {


        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(2, 3, 4, 6);


        BiFunction<List<Integer>, List<Integer>, List<Integer>> biFunction2 = (n, m) -> {
            return Stream.of(n, m).flatMap(List :: stream).distinct().collect(Collectors.toList());// remove duplicates in[ list1 and list2]
        };
        System.out.println(biFunction2.apply(list1, list2));

        BiConsumer<List<Integer>, List<Integer>> biConsumer = (n, m) -> {
            System.out.println(Stream.of(n, m).distinct().collect(Collectors.toList()));
            System.out.println(Stream.of(n, m).flatMap(List :: stream).distinct().collect(Collectors.toList()));

        };
        biConsumer.accept(list1, list2);

        BiPredicate<List<Integer>, List<Integer>> biPredicate = (n, m) -> {

            return Stream.of(n, m).flatMap(List :: stream).count() > 2;
        };
        System.out.println(biPredicate.test(list1, list2));


    }

   /* @Override
    public List<Integer> apply(List<Integer> list1, List<Integer> list2) {
        return Stream.of(list1, list2).flatMap(List::stream).distinct().collect(Collectors.toList());// remove duplicates in[ list1 and list2]
    }

    @Override
    public <V> BiFunction<List<Integer>, List<Integer>, V> andThen(Function<? super List<Integer>, ? extends V> after) {
        return BiFunction.super.andThen(after);
    }*/


}
