package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class EmployeeCloneUsingCloneable {

    /**
     * 1. What is Cloning in Java?
     * In simple words, cloning is about creating a copy of the original object. Its dictionary meaning is: “make an identical copy of”.
     *
     * By default, Java cloning is ‘field by field copy’ because the Object class does not have any idea about the structure of the class on which the clone() method will be invoked.
     *
     * So, JVM when called for cloning, does the following things:
     *
     * If the class has only primitive data type members then a completely new copy of the object will be created and the reference to the new object copy will be returned.
     * If the class contains members of any class type then only the object references to those members are copied and hence the member references in both the original object as well as the cloned object refer to the same object.
     * Apart from the above default behavior, we can always override this behavior and specify your own. This is done by overriding the clone() method. Let’s see how it is done.
     *
     *
     * */

    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(EmployeeCloneUsingCloneable.class, args);

        SchoolEmployee schoolEmployee = new SchoolEmployee("Miranda", 1);
        System.out.println(schoolEmployee);

        SchoolEmployee clonedObject = null;
        if(schoolEmployee instanceof SchoolEmployee){
            clonedObject = schoolEmployee.clone();
        }
        //SchoolEmployee clonedObject =  schoolEmployee.clone();
        System.out.println(clonedObject.toString());
        System.out.println(schoolEmployee.getId() == clonedObject.getId());// true
        System.out.println(schoolEmployee.equals(clonedObject));//true if equals is overridden, false if equals not overridden
        System.out.println(schoolEmployee == clonedObject);//false, guarantees that cloned object will have separate memory address assignment.  x.clone() != x will be true
        System.out.println(schoolEmployee.hashCode() == clonedObject.hashCode());// true if hashCode is overridden, false if hashCode not overridden


    }
}

class SchoolEmployee implements Cloneable{

    private final String name;
    private final int id;

    public SchoolEmployee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    protected SchoolEmployee clone() throws CloneNotSupportedException {

        SchoolEmployee schoolEmployee = (SchoolEmployee) super.clone();
        return schoolEmployee;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolEmployee that = (SchoolEmployee) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
