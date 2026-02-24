package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Interviewers {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 40);
        map.put("C", 20);
        map.put("D", 30);

        List<EmpJP> employees = List.of(
                new EmpJP("Ravi", "IT", 60_000),
                new EmpJP("Amit", "IT", 75_000),
                new EmpJP("Neha", "HR", 50_000),
                new EmpJP("Priya", "HR", 65_000)
        );


        //Top N entries from a Map by value
        //List.of(...) returns an immutable list, so you must not sort it in place.
        List<EmpJP> listSortedAscending = employees.stream()
                .sorted(Comparator.comparingInt(EmpJP::getSalary))
                .toList();
        //Sort by salary (descending)
        List<EmpJP> listSortedAscendingDescending = employees.stream()
                .sorted(Comparator.comparingInt(EmpJP::getSalary).reversed())
                .toList();
        //Sort by salary, then by name (tie-breaker)
        List<EmpJP> sortedSalaryThenName = employees.stream().sorted(Comparator.comparingInt(EmpJP::getSalary).thenComparing(EmpJP::getName)).toList();

        //Sort within department, by salary

        Map<String, List<EmpJP>> deptSortedListEmp = employees.stream()
                .collect(Collectors.groupingBy(
                                EmpJP::getDept,
                                Collectors.collectingAndThen(
                                        toList(),
                                        list -> list.stream().sorted(Comparator.comparingInt(EmpJP::getSalary)).toList()
                                )
                        )
                );

        //If interviewer asks for in-place sort
        List<EmpJP> mutable = new ArrayList<>(employees);
        mutable.sort(Comparator.comparingInt(EmpJP::getSalary));

        //Top N entries from a Map by value

        List<Map.Entry<String, Integer>> top3 = map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).toList();

        //Sort Map by value and preserve order

        LinkedHashMap<String, Integer> orderSortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        //Filter and sort employees by salary

        //Business meaning
        //Filter = eligibility rule
        //Sort = presentation / reporting rule

        List<EmpJP> gt40kList = employees.stream()
                .filter(f -> f.getSalary() > 40000)
                .sorted(Comparator.comparingInt(EmpJP::getSalary))
                .toList();

        //Second highest salary (correct handling of duplicates)
        //Why distinct() matters
        //Without it:
        //
        //Same salary repeated → wrong ranking

        Optional<Integer> secondHighestSalary = employees.stream()
                .map(EmpJP::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        //Sort Map by value DESC, then key ASC
        //Comparator chaining (very important)

        Map<String, Integer> sortedKeyASC_ValueDESC =
                map.entrySet().stream()
                        .sorted(
                                Map.Entry.<String, Integer>comparingByValue().reversed()
                                        .thenComparing(Map.Entry.comparingByKey())
                        )
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));

        //Correct solution: group all objects by key
        //✔ Banking-grade solution (most common)



        Map<String, List<EmpJP>> byDept =
                employees.stream()
                        .collect(Collectors.groupingBy(EmpJP::getDept));
        //Result:
        //
        //IT → [Ravi, Amit]
        //HR → [Neha, Priya]
        //
        //
        //Interview explanation:
        //
        //groupingBy naturally models one-to-many relationships.

        //Explicit version (shows deeper understanding)

        Map<String, List<EmpJP>> byDeptHashMap =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                EmpJP::getDept,
                                HashMap::new,
                                Collectors.toList()
                        ));

        //Why this matters:
        //
        //Shows you understand the full collector signature
        //
        //Useful in performance or concurrency discussions


        //If you want to preserve insertion order
        Map<String, List<EmpJP>> byDeptLinkedHashMap =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                EmpJP::getDept,
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        //If you want sorting inside each key
//Example: employees sorted by salary per department.
        Map<String, List<EmpJP>> byDeptSorted =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                EmpJP::getDept,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.comparingInt(EmpJP::getSalary))
                                                .toList()
                                )
                        ));









    }

}
class EmpJP {
    private final String name;
    private final String dept;
    private final int salary;

    public EmpJP(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDept() { return dept; }
    public int getSalary() { return salary; }
}
