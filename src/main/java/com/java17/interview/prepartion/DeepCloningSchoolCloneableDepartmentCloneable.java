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

        /**
         * 
         * Deep vs Shallow Clone Explained
Looking at your code, here's the difference:

Shallow Clone (Commented out in your code)
// This line would create a SHALLOW clone:
// schoollCloned.department = this.department;
What happens:

Creates a new Schooll object
Copies primitive fields (id, name) to the new object
Copies the reference of department object (both original and clone point to the same Departmentt object)
Problem: Modifying the department in the clone affects the original!
Memory visualization:

Original School → [name="KVS", id=1] ──┐
                                        ├──→ Same Department Object [name="HR"]
Cloned School   → [name="KVS", id=1] ──┘
Deep Clone (What your code does)
// Line 50-51: This creates a DEEP clone:
Schooll schoollCloned = (Schooll) super.clone();
schoollCloned.setDepartment((Departmentt) this.department.clone());
What happens:

Creates a new Schooll object
Copies primitive fields (id, name)
Creates a new copy of the Departmentt object
Both objects are completely independent
Memory visualization:

Original School → [name="KVS", id=1] ──→ Department [name="HR"]

Cloned School   → [name="KVS", id=1] ──→ Department [name="Finance"] (separate copy)
Your Code in Action
// Original
Schooll schooll = new Schooll("KVS", 1, new Departmentt("HR Department", 1));

// Deep clone
Schooll clonedSchooll = schooll.clone();

// Modify the cloned department
clonedSchooll.getDepartment().setDepartmentName("Finance Department");

// Output shows they're independent:
// Original: Schooll{name='KVS', id=1, department=Departmentt{departmentName='HR Department', departmentId=1}}
// Cloned:   Schooll{name='KVS', id=1, department=Departmentt{departmentName='Finance Department', departmentId=1}}
Key Differences Summary
Aspect	Shallow Clone	Deep Clone
Primitive fields	Copied	Copied
Object references	Reference copied (same object)	New object created
Independence	Changes affect both	Completely independent
Implementation	Just super.clone()	super.clone() + clone nested objects
Use case	Objects with no mutable fields	Objects with mutable nested objects
Why Both Classes Implement Cloneable?
Schooll implements Cloneable to clone itself
Departmentt implements Cloneable so Schooll can clone it during deep cloning
Without Departmentt implementing Cloneable, you'd get CloneNotSupportedException
This is a perfect example of deep cloning where nested objects are also cloned to achieve complete independence between original and cloned objects!

         */
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

