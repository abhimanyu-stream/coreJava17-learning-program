package com.java17.interview.prepartion;

import java.time.LocalDate;
import java.util.*;

public class CustomImmutableClass {

	public static void main(String[] args) throws CloneNotSupportedException {


		//Declare class as final.
		//Make all properties as private final.
		//Do not declare setters. Only getters.
		//Declare all args constructor.
		//If there are custom nested objects in the class as properties,, implement clone.
		//If there are other types of nested objects as properties, perform a deep copy.

		Address address1 = new Address("s1", "c1");
		List<String> phoneNumbers = new ArrayList<>();
		phoneNumbers.add("123345");
		phoneNumbers.add("456789");
		Map<String, String> metadata = new HashMap<>();
		metadata.put("hobby", "Watching Movies");
		Date dateOfBirth = new Date("01/01/2020");
		// dateOfBith = LocalDate.of(2020,01,02);
		Employee e = new Employee("Mindranda", 23, address1, phoneNumbers, metadata, dateOfBirth);

		// trying to Employee Object modifications, but not able to do so.Thus Immutable. achieved by Cloneble Interface, clone() method., final keywords, priavte access modifiers.

		// class is final, fields are final, user-defined class is final but can have mutable fields,both constructor are public , in top level class not setter, only getter are allowed. But in
		// refernced user defined class can have setter method as well.
		// referenced class must be cloneable and override clone method with super keyword
		// in top level class getter return clone of user defined class, date object, and new object of collection type.

		e.getAddress().setCity("c3");
		e.getAddress().setStreet("s3");

		e.getPhoneNumbers().add("1");

		e.getMetadata().put("skill", "Java");
		e.getMetadata().put("designation", "HR");


		System.out.println(e.getEmpName());
		System.out.println(e.getAge());

		System.out.println(e.getAddress());
		System.out.println(e.getPhoneNumbers());
		System.out.println(e.getMetadata());
		System.out.println(e.getDateOfBirth());

		System.out.println(e);


	}

	static final class Employee{
		//here class Employee is final, its fields are  final and also only getters are present, clone of Address and Date in getters and new Object for List, Map or Collection or Map type
		private final String name;
		private final int age;
		private final Address address;// mutable clone typecasted
		private final Date dateOfBirth;// mutable clone typecasted
		private final List<String> phoneNumbers;// Collection type new class type ()
		private final Map<String, String> metadata;// Collection type new class type ()




		public Employee(String name, int age, Address address, List<String> phoneNumbers, Map<String, String> metadata, Date dateOfBirth){
			this.name = name;
			this.age = age;
			this.address = address;
			this.phoneNumbers = phoneNumbers;
			this.metadata = metadata;
			this.dateOfBirth = dateOfBirth;
		}

		public String getEmpName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String getName() {
			return name;
		}

		public Date getDateOfBirth() {
			// clone of Date object
			return (Date) dateOfBirth.clone();
		}


		public Address getAddress() throws CloneNotSupportedException {
			// clone the address object
			return (Address) address.clone();
		}


		public List<String> getPhoneNumbers() {
			// deep copy the list of phone numbers
			return new ArrayList<>(phoneNumbers);
		}


		public Map<String, String> getMetadata() {
			// deep copy the map of metadata
			return new java.util.HashMap<>(metadata);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Employee employee = (Employee) o;
			return age == employee.age && name.equals(employee.name) && address.equals(employee.address) && phoneNumbers.equals(employee.phoneNumbers) && metadata.equals(employee.metadata) && dateOfBirth.equals(employee.dateOfBirth);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, age, address, phoneNumbers, metadata, dateOfBirth);
		}

		@Override
		public String toString() {
			return "Employee{" +
					"name='" + name + '\'' +
					", age=" + age +
					", address=" + address +
					", phoneNumbers=" + phoneNumbers +
					", metadata=" + metadata +
					", dateOfBirth=" + dateOfBirth +
					'}';
		}
	}

	static final class Address implements Cloneable {

		//here class Address is final but its fields are not final and also setter are present, clone is present due to Cloneable

		private String street;
		private String city;

		public Address(String street, String city) {
			this.street = street;
			this.city = city;
		}

		@Override
		public String toString() {
			return "Address{" +
					"street='" + street + '\'' +
					", city='" + city + '\'' +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Address address = (Address) o;
			return street.equals(address.street) && city.equals(address.city);
		}

		@Override
		public int hashCode() {
			return Objects.hash(street, city);
		}
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}
	}

}
