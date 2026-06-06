package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class ShallowCloningSchoolCloneableDepartmentCloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(ShallowCloningSchoolCloneableDepartmentCloneable.class, args);

        School school = new School("College", 1, new Department("HR Department", 1));
        School clonedSchool = null;
        if(school instanceof Cloneable){} {
            clonedSchool = school.clone();
        }

        //Department clonedDepartment;

        clonedSchool.getDepartment().setDepartmentName("Finance Department");

        System.out.println(school);
        System.out.println(clonedSchool);



    }
}

class School implements Cloneable{

    private String name;
    private int id;
    private Department department;

    public School(String name, int id, Department department) {
        this.name = name;
        this.id = id;
        this.department = department;
    }

    @Override
    protected School clone() throws CloneNotSupportedException {

        School school = (School) super.clone();
        return school;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id && name.equals(school.name) && department.equals(school.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, department);
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", department=" + department +
                '}';
    }
}
class Department implements Cloneable {

    String departmentName;
    int departmentId;

    public Department(String departmentName, int departmentId) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return departmentId == that.departmentId && departmentName.equals(that.departmentName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(departmentName, departmentId);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
