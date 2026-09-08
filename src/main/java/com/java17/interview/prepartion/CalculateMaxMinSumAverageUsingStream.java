package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;

public class CalculateMaxMinSumAverageUsingStream {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 7, 9, 11);

        // Calculate max, min, sum, and average using streams
        
        
        Integer maxByNaturalOrdering = numbers.stream().max(Comparator.naturalOrder()).get();
        Integer minByNaturalOrdering = numbers.stream().min(Comparator.naturalOrder()).get();
        
        
     // Sum
        int sum =
                numbers.stream()
                       .mapToInt(Integer::intValue)
                       .sum();

        // Average
        double average =
                numbers.stream()
                       .mapToInt(Integer::intValue)
                       .average()
                       .orElse(0.0);

        System.out.println("Max     : " + maxByNaturalOrdering);
        System.out.println("Min     : " + minByNaturalOrdering);
        System.out.println("Sum     : " + sum);
        System.out.println("Average : " + average);

        

       

        // Alternatively, using IntSummaryStatistics for all in one go
        IntSummaryStatistics stats = numbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println("\nUsing IntSummaryStatistics:");
        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Sum: " + stats.getSum());

        //Alternative: Using IntSummaryStatistics
        //The IntSummaryStatistics class computes max, min, sum, and average in one go, which can be more efficient if you need all these statistics.

    }
}


