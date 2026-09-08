package com.java17.interview.prepartion;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

public class SortByDOJComparator {

    public static void main(String[] args) {
        List<Tank> tankList = Arrays.asList(
                new Tank(LocalDate.of(2024, 2, 2), "ArjunTank"),
                new Tank(LocalDate.of(2024, 5, 16), "a")
        );

        // Approach - 1: Sort by day-of-month of enforcement date in descending order
        List<Tank> sortedTankListByDayOfMonth = tankList.stream()
                .sorted((o1, o2) -> o2.getDateofEnforcement().getDayOfMonth() - o1.getDateofEnforcement().getDayOfMonth())
                .toList();
        System.out.println("sortedTankListByDayOfMonth   : " + sortedTankListByDayOfMonth);

        // Approach - 1b: Sort by full LocalDate in descending order using Comparator.comparing
        // Note: LocalDate is not an int, so comparingInt() cannot be used here — use comparing() instead
        List<Tank> sortedByDateDesc = tankList.stream()
                .sorted(Comparator.comparing(Tank::getDateofEnforcement).reversed())
                .toList();
        System.out.println("sortedByDateDesc             : " + sortedByDateDesc);

        // Approach - 2: Sort by tank name length in descending order
        List<Tank> sortedByNameLength = tankList.stream()
                .sorted((o1, o2) -> o2.getTankName().length() - o1.getTankName().length())
                .toList();
        System.out.println("sortedByNameLength           : " + sortedByNameLength);

        // Approach - 3: Using BiFunction for sorting by name length in descending order
        BiFunction<Tank, Tank, Integer> compareByNameLength = (tank1, tank2) ->
                Integer.compare(tank2.getTankName().length(), tank1.getTankName().length());

        List<Tank> sortedByBiFunction = tankList.stream()
                .sorted(compareByNameLength::apply) // equivalent to: .sorted((o1, o2) -> compareByNameLength.apply(o1, o2))
                .toList();
        System.out.println("sortedByBiFunction           : " + sortedByBiFunction);

        // Unicode value examples
        System.out.println("Unicode value of 'A'         : " + (int) 'A');
        System.out.println("Unicode value of 'B'         : " + (int) 'B');
        System.out.println("Unicode value of 'a'         : " + (int) 'a');
        System.out.println("Unicode value of 'b'         : " + (int) 'b');
    }
}

// Tank class — defined in the same file since it is only used here
class Tank {

    private LocalDate dateofEnforcement;
    private String tankName;

    public Tank(LocalDate dateofEnforcement, String tankName) {
        this.dateofEnforcement = dateofEnforcement;
        this.tankName = tankName;
    }

    public LocalDate getDateofEnforcement() {
        return dateofEnforcement;
    }

    public String getTankName() {
        return tankName;
    }

    @Override
    public String toString() {
        return "Tank{date=" + dateofEnforcement + ", name='" + tankName + "'}";
    }
}
