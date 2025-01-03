package com.java17.interview.prepartion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.Ascii;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByDOJComparator {

    public static void main(String[] args) {
        List<Tank> tankList = Arrays.asList(
                new Tank(LocalDate.of(2024, 2, 2), "ArjunTank"),
                new Tank(LocalDate.of(2024, 5, 16), "a"),
                new Tank(LocalDate.of(2024, 11, 20), "B2-stealth-USA")
        );




        //*******************************************************
        // approach - 1
        Comparator<Tank> dateComparatorByMonth = (o1, o2) -> {
            //descending order
            return o2.getDateofEnforcement().getDayOfMonth()- o1.getDateofEnforcement().getDayOfMonth();
        };

        List<Tank> sortedTankListByMonthOfDate = tankList.stream().sorted(dateComparatorByMonth).toList();
        System.out.println("sortedTankListByMonthOfDate  " + sortedTankListByMonthOfDate);


        // approach - 2

        List<Tank> sortedByNameLength2 = tankList.stream().sorted((o1, o2) -> o2.getTankName().length() - o1.getTankName().length()).toList();// larger-length first
        System.out.println("sortedByNameLength2 "+sortedByNameLength2);
        //*******************************************************













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
class  Tank{

    private LocalDate dateofEnforcement;
    private String tankName;
}
