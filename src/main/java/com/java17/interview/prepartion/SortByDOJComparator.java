package com.java17.interview.preparation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class SortByDOJComparator {

    public static void main(String[] args) {
        List<Tank> tankList = Arrays.asList(
                new Tank(LocalDate.of(2024, 2, 2), "ArjunTank"),
                new Tank(LocalDate.of(2024, 5, 16), "a"),
                new Tank(LocalDate.of(2024, 11, 20), "B2-stealth-USA")
        );

        //*******************************************************
        // Approach - 1: Sort by date of enforcement day in descending order
        List<Tank> sortedTankListByMonthOfDate = tankList.stream()
                .sorted((o1, o2) -> o2.getDateofEnforcement().getDayOfMonth() - o1.getDateofEnforcement().getDayOfMonth())
                .toList();
        System.out.println("sortedTankListByMonthOfDate  " + sortedTankListByMonthOfDate);

        // Approach - 2: Sort by tank name length in descending order
        List<Tank> sortedByNameLength2 = tankList.stream()
                .sorted((o1, o2) -> o2.getTankName().length() - o1.getTankName().length())
                .toList();
        System.out.println("sortedByNameLength2 " + sortedByNameLength2);

        //*******************************************************
        // Approach - 3: Using BiFunction for sorting by name length
        BiFunction<Tank, Tank, Integer> compareByNameLength = (tank1, tank2) ->
                Integer.compare(tank2.getTankName().length(), tank1.getTankName().length());

        List<Tank> sortedByBiFunction = tankList.stream()
                .sorted(compareByNameLength::apply)//compareByNameLength.apply(o1,o2)
                .toList();
        System.out.println("sortedByBiFunction " + sortedByBiFunction);

        // Unicode value examples
        System.out.println("Unicode value of 'A': " + (int) 'A');
        System.out.println("Unicode value of 'B': " + (int) 'B');
        System.out.println("Unicode value of 'a': " + (int) 'a');
        System.out.println("Unicode value of 'b': " + (int) 'b');
    }

}

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
class Tank {
    private LocalDate dateofEnforcement;
    private String tankName;
}
