package com.java17.interview.prepartion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByDOJComparator {

    public static void main(String[] args) {
        List<Tank> tankList = Arrays.asList(new Tank(LocalDate.of(2024,02,02),"ArjunTank"),
                new Tank(LocalDate.of(2024,05,02),"ArjunTank"));
        Comparator<Tank> dateComparator = new Comparator<>() {
            @Override
            public int compare(Tank o1, Tank o2) {
                return o2.getDateofEnforcement().getDayOfMonth()- o1.getDateofEnforcement().getDayOfMonth();
            }
        };
        List<Tank> sortedByYearTank = tankList.stream().sorted(dateComparator).collect(Collectors.toList());
        System.out.println(sortedByYearTank);

    }

}

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
class Tank{

    private LocalDate dateofEnforcement;
    private String tankName;
}
