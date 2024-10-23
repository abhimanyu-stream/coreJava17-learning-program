package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SortListObjectUsingComparator {

	public static void main(String[] args) {
		
		
		List<Employee> listOfEmployee = new ArrayList<>();
		Employee employee1 = new Employee("abhisri", 40);
		Employee employee2 = new Employee("febecca", 10);
		Employee employee3 = new Employee("eallista", 80);
		Employee employee4 = new Employee("bam", 30);
		Employee employee5 = new Employee("Cally", 10);
		Employee employee6 = new Employee("diranda", 50);
		listOfEmployee.add(employee1);
		listOfEmployee.add(employee2);
		listOfEmployee.add(employee3);
		listOfEmployee.add(employee4);
		listOfEmployee.add(employee5);
		listOfEmployee.add(employee6);
		
		Collections.sort(listOfEmployee, new AgeComparator());
		System.out.println(listOfEmployee);
		
		System.out.println(listOfEmployee.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).collect(Collectors.toList()));
		

		//using stream api
		listOfEmployee.stream().sorted(new AgeComparator()).collect(Collectors.toList());
		//static problem
		//listOfEmployee.stream().sorted(AgeComparator::compare).collect(Collectors.toList());
	}
	 
	
	

}
class AgeComparator implements Comparator<Employee>{


	public int compare(Employee o1, Employee o2) {
		
		if(o1.getAge() > o2.getAge())
			return -1;
		else if(o1.getAge() < o2.getAge())
			return 1;
		
		return 0;
	}
	
	
	
}

class Employee{
	
	
	String name;
	int age;
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}
	
}
