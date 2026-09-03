package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortListObjectUsingComparator {

	public static void main(String[] args) {
		
		
		List<EmployeeL> listOfEmployee = new ArrayList<>();
		EmployeeL employee1 = new EmployeeL("abhisri", 40);
		EmployeeL employee2 = new EmployeeL("febecca", 10);
		EmployeeL employee3 = new EmployeeL("eallista", 80);
		EmployeeL employee4 = new EmployeeL("bam", 30);
		EmployeeL employee5 = new EmployeeL("Cally", 10);
		EmployeeL employee6 = new EmployeeL("diranda", 50);
		listOfEmployee.add(employee1);
		listOfEmployee.add(employee2);
		listOfEmployee.add(employee3);
		listOfEmployee.add(employee4);
		listOfEmployee.add(employee5);
		listOfEmployee.add(employee6);
		
		Collections.sort(listOfEmployee, new AgeComparator());
		System.out.println(listOfEmployee);
		
		System.out.println(listOfEmployee.stream().sorted(Comparator.comparing(EmployeeL::getAge).reversed()).collect(Collectors.toList()));

		listOfEmployee.stream().sorted(Comparator.comparingInt(EmployeeL::getAge)).toList();

		//using stream api
		listOfEmployee.stream().sorted(new AgeComparator()).collect(Collectors.toList());
		//static problem
		//listOfEmployee.stream().sorted(AgeComparator::compare).collect(Collectors.toList());
	}
	 
	
	

}
class AgeComparator implements Comparator<EmployeeL>{


	public int compare(EmployeeL o1, EmployeeL o2) {
		
		if(o1.getAge() > o2.getAge())
			return -1;
		else if(o1.getAge() < o2.getAge())
			return 1;
		
		return 0;
	}
	
	
	
}

class EmployeeL{
	
	
	String name;
	int age;
	public EmployeeL(String name, int age) {
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
// sorted(Comparator.comparing(Employee::getAge).reversed())