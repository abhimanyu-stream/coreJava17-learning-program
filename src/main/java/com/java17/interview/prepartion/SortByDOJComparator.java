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
        List<Tank> tankList = Arrays.asList(new Tank(LocalDate.of(2024,02,02),"ArjunTank"),
                new Tank(LocalDate.of(2024,05,16),"Bhim"));
        Comparator<Tank> dateComparator = new Comparator<>() {
            @Override
            public int compare(Tank o1, Tank o2) {
                //descending order
                return o2.getDateofEnforcement().getDayOfMonth()- o1.getDateofEnforcement().getDayOfMonth();
            }
        };
        List<Tank> sortedDayOfMonthForTank = tankList.stream().sorted(dateComparator).toList();
        System.out.println(sortedDayOfMonthForTank);//[Tank(dateofEnforcement=2024-05-16, tankName=ArjunTank), Tank(dateofEnforcement=2024-02-02, tankName=ArjunTank)]

        //sorted((o1, o2) -> o2.getTankName().length()-o1.getTankName().length()) its ascending order
        List<Tank> sortedByNameLength = tankList.stream().sorted((o1, o2) -> o2.getTankName().length()-o1.getTankName().length()).toList();

        System.out.println(sortedByNameLength);
        System.out.println(Integer.valueOf('A'));
        System.out.println(Integer.valueOf('B'));
        System.out.println(Integer.valueOf('a'));
        System.out.println(Integer.valueOf('b'));
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
