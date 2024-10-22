package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class DeepCloningSchoolCloneableDepartmentCloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(DeepCloningSchoolCloneableDepartmentCloneable.class, args);


        Schooll schooll = new Schooll("KVS", 1, new Departmentt("HR Department", 1));

        Schooll clonedSchooll = null;
        if(schooll instanceof Cloneable){} {
            clonedSchooll = schooll.clone();
            clonedSchooll.getDepartment().setDepartmentName("Finance Department");
            System.out.println(schooll);
            System.out.println(clonedSchooll);
        }
    }

    /**@author */

}


class Schooll implements Cloneable{

    private String name;
    private int id;
    private Departmentt department;

    public Schooll(String name, int id, Departmentt department) {
        this.name = name;
        this.id = id;
        this.department = department;
    }

    @Override
    protected Schooll clone() throws CloneNotSupportedException {

        Schooll schoollCloned = (Schooll) super.clone();
        schoollCloned.setDepartment((Departmentt) this.department.clone());

       //schoollCloned.department = this.department;

        return schoollCloned;
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

    public void setDepartment(Departmentt department) {
        this.department = department;
    }

    public Departmentt getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schooll schooll = (Schooll) o;
        return id == schooll.id && name.equals(schooll.name) && department.equals(schooll.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, department);
    }

    @Override
    public String toString() {
        return "Schooll{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", department=" + department +
                '}';
    }
}
class Departmentt implements Cloneable {

    private String departmentName;
    private int departmentId;

    public Departmentt(String departmentName, int departmentId) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departmentt that = (Departmentt) o;
        return departmentId == that.departmentId && departmentName.equals(that.departmentName);
    }

  @Override
  protected Object clone() throws CloneNotSupportedException {
      return super.clone();
  }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName, departmentId);
    }

    @Override
    public String toString() {
        return "Departmentt{" +
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

