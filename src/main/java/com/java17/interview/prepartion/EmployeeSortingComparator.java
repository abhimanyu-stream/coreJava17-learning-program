
package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeSortingComparator {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(

                new Employee(101, "Rahul", 30, 75000, "IT"),
                new Employee(105, "Amit", 28, 65000, "HR"),
                new Employee(102, "Priya", 32, 90000, "IT"),
                new Employee(104, "Neha", 27, 55000, "Finance"),
                new Employee(103, "Vikas", 35, 85000, "Finance"),
                new Employee(106, "Anita", 29, 90000, "HR")
        );


        // =========================================================
        // 1. Comparable - Natural Ordering
        // =========================================================

        System.out.println("\n========== Comparable - ID ASC ==========");

        List<Employee> sortedById = employees.stream()
                .sorted()// sorting by ids of public int compareTo(Employee other) {return Integer.compare(this.id, other.id} like [this.id- other.id]
                .collect(Collectors.toList());

        sortedById.forEach(System.out::println);
        
        List<Employee> sortedByIdE = employees.stream()
                .sorted(Comparator.naturalOrder())// sorting by ids of public int compareTo(Employee other) {return Integer.compare(this.id, other.id} like [this.id- other.id]
                .collect(Collectors.toList());

        sortedByIdE.forEach(System.out::println);
        


        // =========================================================
        // 2. Comparable - Without Stream
        // =========================================================

        System.out.println("\n========== Comparable - Collections.sort() ==========");

        List<Employee> employeesCopy = new ArrayList<>(employees);

        Collections.sort(employeesCopy);

        employeesCopy.forEach(System.out::println);


        // =========================================================
        // 3. Comparable - Reverse Order
        // =========================================================

        System.out.println("\n========== Comparable - ID DESC ==========");

        List<Employee> idDescending = employees.stream()
                .sorted(Comparator.reverseOrder())// sorting by ids of public int compareTo(Employee other) {return Integer.compare(this.id, other.id} but in reversed order [ like other.id - this.id]
                .collect(Collectors.toList());

        idDescending.forEach(System.out::println);
        
        
        
        


        // =========================================================
        // 4. Comparator - Salary ASC
        // =========================================================

        System.out.println("\n========== Salary ASC ==========");

        List<Employee> salaryAsc = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))// in this case Comparator<Employee> created
                .collect(Collectors.toList());

        salaryAsc.forEach(System.out::println);


        // =========================================================
        // 5. Comparator - Salary DESC
        // =========================================================

        System.out.println("\n========== Salary DESC ==========");

        List<Employee> salaryDesc = employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()// in this case Comparator<Employee> created
                )
                .collect(Collectors.toList());

        salaryDesc.forEach(System.out::println);


        // =========================================================
        // 6. Comparator - Name ASC
        // =========================================================

        System.out.println("\n========== Name ASC ==========");

        List<Employee> nameAsc = employees.stream()
                .sorted(Comparator.comparing(Employee::getName))// in this case Comparator<Employee> created
                .collect(Collectors.toList());

        nameAsc.forEach(System.out::println);


        // =========================================================
        // 7. Comparator - Age DESC
        // =========================================================

        System.out.println("\n========== Age DESC ==========");

        List<Employee> ageDesc = employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getAge)
                                .reversed()// in this case Comparator<Employee> created
                )
                .collect(Collectors.toList());

        ageDesc.forEach(System.out::println);


        // =========================================================
        // 8. Multiple Conditions
        // Salary DESC
        // If salary same -> Name ASC
        // =========================================================

        System.out.println("\n========== Salary DESC + Name ASC ==========");

        List<Employee> multipleCondition = employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()// in this case Comparator<Employee> created
                                .thenComparing(Employee::getName)// in this case Comparator<Employee> created
                )
                .collect(Collectors.toList());

        multipleCondition.forEach(System.out::println);


        // =========================================================
        // 9. Highest Salary
        // =========================================================

        System.out.println("\n========== Highest Salary ==========");

        Optional<Employee> highestSalary = employees.stream()
                .max(Comparator.comparing(Employee::getSalary));// in this case Comparator<Employee> created

        highestSalary.ifPresent(System.out::println);


        // =========================================================
        // 10. Lowest Salary
        // =========================================================

        System.out.println("\n========== Lowest Salary ==========");

        Optional<Employee> lowestSalary = employees.stream()
                .min(Comparator.comparing(Employee::getSalary));// in this case Comparator<Employee> created

        lowestSalary.ifPresent(System.out::println);


        // =========================================================
        // 11. Second Highest Salary
        // =========================================================

        System.out.println("\n========== Second Highest Salary ==========");

        Optional<Double> secondHighestSalary = employees.stream()
                .map(Employee::getSalary)//first retrieve all salary from List of Employee
                .distinct()
                .sorted(Comparator.reverseOrder())// natural reverse ordering
                .skip(1)
                .findFirst();

        secondHighestSalary.ifPresent(
                salary -> System.out.println("Second Highest Salary = " + salary)
        );


        // =========================================================
        // 12. Second Highest Paid Employee
        // =========================================================

        System.out.println("\n========== Second Highest Paid Employee ==========");

        Optional<Employee> secondHighestEmployee = employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()
                )
                .skip(1)
                .findFirst();

        secondHighestEmployee.ifPresent(System.out::println);


        // =========================================================
        // 13. Highest Salary Per Department
        // =========================================================

        System.out.println("\n========== Highest Salary Per Department ==========");

        Map<String, Optional<Employee>> highestSalaryByDepartment =
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Employee::getDepartment,
                                        Collectors.maxBy(
                                                Comparator.comparing(Employee::getSalary)
                                        )
                                )
                        );

        highestSalaryByDepartment.forEach(
                (department, employee) ->
                        System.out.println(
                                department + " -> " + employee
                        )
        );


        // =========================================================
        // 14. Explicit Comparator - Salary
        // =========================================================

        System.out.println("\n========== Explicit Comparator - Salary ==========");

        Comparator<Employee> salaryComparator =
                new Comparator<Employee>() {

                    @Override
                    public int compare(Employee e1, Employee e2) {

                        int result = Double.compare(
                                e1.getSalary(),
                                e2.getSalary()
                        );

                        System.out.println(
                                "compare(): Salary "
                                        + e1.getSalary()
                                        + " vs "
                                        + e2.getSalary()
                                        + " => "
                                        + result
                        );

                        return result;
                    }
                };

        List<Employee> explicitSalarySort =
                new ArrayList<>(employees);

        explicitSalarySort.sort(salaryComparator);

        explicitSalarySort.forEach(System.out::println);


        // =========================================================
        // 15. Explicit Comparator - Name
        // =========================================================

        System.out.println("\n========== Explicit Comparator - Name ==========");

        Comparator<Employee> nameComparatorExplicit =
                new Comparator<Employee>() {

                    @Override
                    public int compare(Employee e1, Employee e2) {

                        int result =
                                e1.getName()
                                        .compareTo(e2.getName());

                        System.out.println(
                                "compare(): Name "
                                        + e1.getName()
                                        + " vs "
                                        + e2.getName()
                                        + " => "
                                        + result
                        );

                        return result;
                    }
                };

        List<Employee> explicitNameSort =
                new ArrayList<>(employees);

        explicitNameSort.sort(nameComparatorExplicit);

        explicitNameSort.forEach(System.out::println);


        // =========================================================
        // 16. Lambda Comparator
        // =========================================================

        System.out.println("\n========== Lambda Comparator - Salary DESC ==========");

        Comparator<Employee> salaryLambda =
                (e1, e2) -> {

                    int result = Double.compare(
                            e2.getSalary(),
                            e1.getSalary()
                    );

                    System.out.println(
                            "Lambda compare(): "
                                    + e1.getSalary()
                                    + " vs "
                                    + e2.getSalary()
                                    + " => "
                                    + result
                    );

                    return result;
                };

        List<Employee> lambdaSalarySort =
                new ArrayList<>(employees);

        lambdaSalarySort.sort(salaryLambda);

        lambdaSalarySort.forEach(System.out::println);
    }
}


/*
 * ============================================================
 * Employee
 * ============================================================
 *
 * Comparable<Employee>
 *
 * Natural ordering:
 * Employee ID ASC
 *
 */

class Employee implements Comparable<Employee> {

    private int id;
    private String name;
    private int age;
    private double salary;
    private String department;


    public Employee(
            int id,
            String name,
            int age,
            double salary,
            String department) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }


    // =========================================================
    // Comparable
    // =========================================================

    @Override
    public int compareTo(Employee other) {

        int result = Integer.compare(
                this.id,
                other.id
        );

        System.out.println(
                "compareTo(): ID "
                        + this.id
                        + " vs "
                        + other.id
                        + " => "
                        + result
        );

        return result;
    }


    // =========================================================
    // Getters
    // =========================================================

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }


    // =========================================================
    // toString()
    // =========================================================

    @Override
    public String toString() {

        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}


/*
 * The confusion is very common. Both lines use Comparator somewhere, but they are doing two different things.

The key is to understand what sorted() receives.

1. Comparator.reverseOrder() — actually uses Comparable
List<Employee> idDescending = employees.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

Your Employee implements:

class Employee implements Comparable<Employee> {

    @Override
    public int compareTo(Employee other) {

        int result = Integer.compare(this.id, other.id);

        System.out.println(
                "compareTo(): "
                        + this.id
                        + " vs "
                        + other.id
                        + " => "
                        + result
        );

        return result;
    }
}

So Employee has a natural ordering based on ID.

Employee
   |
   | implements
   ↓
Comparable<Employee>
   |
   | compareTo()
   ↓
ID ASC

Normally:

.sorted()

means:

"Use Employee's compareTo()."

Therefore:

employees.stream()
        .sorted()

uses:

Employee.compareTo()

and gives:

101
102
103
104
105
106
Then what does Comparator.reverseOrder() do?

This is the important part.

.sorted(Comparator.reverseOrder())

Comparator.reverseOrder() creates a Comparator that reverses the natural ordering.

Its conceptual behavior is:

Employee's Comparable
       ↓
compareTo()
       ↓
Natural order: ID ASC
       ↓
reverseOrder()
       ↓
ID DESC

So:

.sorted(Comparator.reverseOrder())

is still based on the Comparable implementation of Employee.

It does not mean you have created a completely new comparison rule such as salary or name.

Think of it like this:
.sorted()

➡️ Use Comparable.compareTo() directly.

.sorted(Comparator.reverseOrder())

➡️ Use Comparable.compareTo(), but reverse its result.

For example:

compareTo():

101 vs 102
result = -1

Natural ordering:
101 comes before 102


reverseOrder():

result becomes +1

Therefore:
102 comes before 101
2. Comparator.comparing(Employee::getSalary) — Comparator

Now look at:

List<Employee> salaryAsc = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary))
        .collect(Collectors.toList());

This is different.

Here we are explicitly saying:

"Don't use Employee's natural ordering. Compare employees using their salary."

Comparator.comparing() creates a Comparator.

Conceptually:

Employee
   |
   | getSalary()
   ↓
salary
   |
   | compare salary
   ↓
Comparator<Employee>

So:

Comparator.comparing(Employee::getSalary)

is basically creating a comparator that behaves conceptually like:

(e1, e2) -> {
    return Double.compare(
        e1.getSalary(),
        e2.getSalary()
    );
}

Therefore:

.sorted(Comparator.comparing(Employee::getSalary))

uses a Comparator, not the Employee's compareTo().

The easiest comparison

Your Employee has:

@Override
public int compareTo(Employee other) {

    int result = Integer.compare(
            this.id,
            other.id
    );

    return result;
}

Therefore its natural ordering is:

Employee
   ↓
Comparable
   ↓
compareTo()
   ↓
ID
Case 1 — Natural ordering
.sorted()

Uses:

Employee.compareTo()

Result:

ID ASC
Case 2 — Reverse natural ordering
.sorted(Comparator.reverseOrder())

Uses:

Employee.compareTo()
        ↓
reverse

Result:

ID DESC

So this is Comparable + reverse.

Case 3 — Custom ordering
.sorted(Comparator.comparing(Employee::getSalary))

Uses:

Comparator
    ↓
getSalary()
    ↓
compare salaries

Result:

Salary ASC

So this is Comparator.

Very important interview point

Don't get confused by the word Comparator in:

Comparator.reverseOrder()

The fact that the method returns a Comparator<Employee> does not mean the Employee itself is using Comparator.

Comparator.reverseOrder() is a Comparator object/adaptor that reverses the natural ordering defined by Comparable.

Whereas:

Comparator.comparing(Employee::getSalary)

creates a comparator based on a specific property, salary.

Visual difference
                    Employee
                       |
             implements Comparable
                       |
                       ↓
                 compareTo()
                       |
                    ID ASC
                       |
          ┌────────────┴────────────┐
          ↓                         ↓
     sorted()            Comparator.reverseOrder()
          ↓                         ↓
       ID ASC                    ID DESC

Whereas:

                 Employee
                    |
                    |
             getSalary()
                    |
                    ↓
       Comparator.comparing(...)
                    |
                    ↓
                Salary ASC
One-line interview answer

Comparable defines the natural ordering inside the Employee class through compareTo(). Comparator defines an external/custom ordering. Comparator.reverseOrder() is special because it reverses the object's natural Comparable ordering, while Comparator.comparing(Employee::getSalary) creates a custom comparator based on salary.

can we use Comparator.naturalOrder() in place of sorted()

Yes, you can use Comparator.naturalOrder() instead of .sorted(), provided that the element type implements Comparable.

For your Employee class:

class Employee implements Comparable<Employee> {

    @Override
    public int compareTo(Employee other) {
        int result = Integer.compare(this.id, other.id);
        return result;
    }
}
These two are equivalent

Option 1 — simplest:

List<Employee> result = employees.stream()
        .sorted()
        .collect(Collectors.toList());

Option 2 — explicitly specify natural ordering:

List<Employee> result = employees.stream()
        .sorted(Comparator.naturalOrder())
        .collect(Collectors.toList());

Both use:

Employee
   ↓
Comparable<Employee>
   ↓
compareTo()
   ↓
ID ASC

So the result is:

101
102
103
104
105
106
Why does Comparator.naturalOrder() work?

Comparator.naturalOrder() returns a Comparator<T> that uses the object's Comparable.compareTo().

Conceptually:

Comparator.naturalOrder()
        ↓
use compareTo()

So:

.sorted(Comparator.naturalOrder())

means:

Sort using the object's natural ordering.

Compare all three
// 1. Natural ordering
.sorted()

// 2. Explicit natural ordering
.sorted(Comparator.naturalOrder())

// 3. Reverse natural ordering
.sorted(Comparator.reverseOrder())
Code	Uses	Result in your Employee
.sorted()	Comparable.compareTo()	ID ASC
.sorted(Comparator.naturalOrder())	Comparable.compareTo()	ID ASC
.sorted(Comparator.reverseOrder())	Comparable.compareTo() reversed	ID DESC
.sorted(Comparator.comparing(Employee::getSalary))	Custom Comparator	Salary ASC
One important distinction

Comparator.naturalOrder() does not replace Comparable.

Your class still needs:

implements Comparable<Employee>

and:

@Override
public int compareTo(Employee other) {
    return Integer.compare(this.id, other.id);
}

Otherwise Java doesn't know what the natural order of Employee is.

Interview answer

Yes. sorted() and sorted(Comparator.naturalOrder()) can produce the same result when the elements implement Comparable. sorted() implicitly uses natural ordering, while Comparator.naturalOrder() explicitly provides a comparator that delegates to compareTo().

 * * /
 */