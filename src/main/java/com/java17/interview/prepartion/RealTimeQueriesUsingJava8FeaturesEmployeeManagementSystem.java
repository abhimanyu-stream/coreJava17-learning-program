package com.java17.interview.prepartion;


import org.apache.logging.log4j.util.PropertySource;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class RealTimeQueriesUsingJava8FeaturesEmployeeManagementSystem {

    public static void main(String[] args) {



        List<EmployeeOfMicrosoft> employeeOfMicrosoftList = new ArrayList<>();

        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeOfMicrosoftList.add(new EmployeeOfMicrosoft(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));






        // Sort Employee by Date of Joining
        Comparator<EmployeeOfMicrosoft> dojComparator = new Comparator<>() {
            @Override
            public int compare(EmployeeOfMicrosoft o1, EmployeeOfMicrosoft o2) {
                return o2.getYearOfJoining()-o1.getYearOfJoining();
            }
        };
        List<EmployeeOfMicrosoft> sortedByDOJEmployeeList = employeeOfMicrosoftList.stream().sorted(dojComparator).toList();
        System.out.println(sortedByDOJEmployeeList);


        // How many employees are there in the organization Department wise ?
        // For queries such as above where you need to group the input elements, use the Collectors.groupingBy() method.
        // In this query, we use Collectors.groupingBy() method which takes two arguments.
        // We pass Employee::getGender as first argument which groups the input elements based on gender and Collectors.counting() as second argument which counts the number of entries in each group.

        Map<String, Long> noOfEmployeesDepartmentWiseInTheOrganization  = employeeOfMicrosoftList.stream().collect(Collectors.groupingBy(EmployeeOfMicrosoft::getDepartment, Collectors.counting()));

        System.out.println(noOfEmployeesDepartmentWiseInTheOrganization);


        // How many male and female employees are there in the organization?
        Map<String, Long>  noOfMaleFemaleInOrganization = employeeOfMicrosoftList.stream().collect(Collectors.groupingBy(EmployeeOfMicrosoft::getGender, Collectors.counting()));
        System.out.println(noOfMaleFemaleInOrganization);


        // Print the name of all departments in the organization?


       // Stream<String> streamOfDepartments = employeeOfMicrosoftList.stream().map(EmployeeOfMicrosoft::getDepartment).distinct();
        employeeOfMicrosoftList.stream().map(EmployeeOfMicrosoft::getDepartment).distinct().forEach(System.out::println);


        //  What is the average age of male and female employees?

        Map<String, Double>  averageAgeOfMaleFemaleInOrganization = employeeOfMicrosoftList.stream().collect(Collectors.groupingBy(EmployeeOfMicrosoft::getGender, Collectors.averagingInt(EmployeeOfMicrosoft::getAge)));


        System.out.println(averageAgeOfMaleFemaleInOrganization);



        // Get the details of highest paid employee in the organization?


        EmployeeOfMicrosoft higestSalaryPaidEmployee = employeeOfMicrosoftList.stream().max(Comparator.comparingDouble(EmployeeOfMicrosoft :: getSalary)).get();


        System.out.println(higestSalaryPaidEmployee);


        // Get the names of all employees who have joined after 2015?

        List<EmployeeOfMicrosoft> listOfEmployeeJoingDateGT2015 =employeeOfMicrosoftList.stream().filter(microsoft -> microsoft.getYearOfJoining() > 2015).collect(Collectors.toList());

        System.out.println(listOfEmployeeJoingDateGT2015);

        // Count the number of employees in each department?

        Map<String, Long> numberOfEmployeeDepartmentWise = employeeOfMicrosoftList.stream().collect(Collectors.groupingBy(EmployeeOfMicrosoft::getDepartment, Collectors.counting()));

        System.out.println(numberOfEmployeeDepartmentWise);

        // What is the average salary of each department?
       Map<String, Double> departmentWiseAverageSalary = employeeOfMicrosoftList.stream().collect(Collectors.groupingBy(EmployeeOfMicrosoft::getDepartment, Collectors.averagingDouble(EmployeeOfMicrosoft::getSalary)));
       System.out.println(departmentWiseAverageSalary);

       //  Get the details of youngest male employee in the product development department?
       EmployeeOfMicrosoft FemaleInProductDepartmentMinAge = employeeOfMicrosoftList.stream().filter(employeeOfMicrosoft -> employeeOfMicrosoft.getDepartment() == "Product Development" && employeeOfMicrosoft.getGender() == "Female").min(Comparator.comparingDouble(EmployeeOfMicrosoft::getAge)).get();
       System.out.println(FemaleInProductDepartmentMinAge);

        // What is the average salary of each department?

        Map<String, Double> averageSalaryDepartmentWise = employeeOfMicrosoftList.stream().collect(Collectors.groupingBy(EmployeeOfMicrosoft::getDepartment, Collectors.averagingDouble(EmployeeOfMicrosoft::getSalary)));
        System.out.println(averageSalaryDepartmentWise);


        // Who has the most working experience in the organization?


        List<EmployeeOfMicrosoft> listOfEmployeeOfMicrosoft = employeeOfMicrosoftList.stream().filter(f-> f.getDepartment() == "HR").collect(Collectors.toList());
        System.out.println(listOfEmployeeOfMicrosoft);








    }


}

class EmployeeOfMicrosoft {

    private int id;

    private String name;

    private int age;

    private String gender;

    private String department;

    private int yearOfJoining;

    private double salary;


    public EmployeeOfMicrosoft(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeOfMicrosoft that = (EmployeeOfMicrosoft) o;
        return id == that.id && age == that.age && yearOfJoining == that.yearOfJoining && Double.compare(that.salary, salary) == 0 && name.equals(that.name) && gender.equals(that.gender) && department.equals(that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender, department, yearOfJoining, salary);
    }

    @Override
    public String toString() {
        return "EmployeeOfMicrosoft{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", yearOfJoining=" + yearOfJoining +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setYearOfJoining(int yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
