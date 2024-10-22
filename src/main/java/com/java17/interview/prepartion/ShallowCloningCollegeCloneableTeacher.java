package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class ShallowCloningCollegeCloneableTeacher {
    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(ShallowCloningCollegeCloneableTeacher.class, args);


        College college = new College("College", 1, new Teacher("Miranda Teacher", 1));

        College clonedCollege = null;
        if(college instanceof College){} {
            clonedCollege = college.clone();
        }
        //College clonedCollege = college.clone();
        System.out.println(college);
        System.out.println(clonedCollege.toString());


        System.out.println(college.getId() == clonedCollege.getId());// true
        System.out.println(college.equals(clonedCollege));// true if equals is overridden, false if equals not overridden
        System.out.println(college == clonedCollege);// false, guarantees that cloned object will have separate memory address assignment. x.clone()!= x
        System.out.println(college.hashCode() == clonedCollege.hashCode());// true if hashCode is overridden, false if hashCode not overridden


        clonedCollege.getTeacher().setTeacherName("Jenny Teacher");
        System.out.println(college);
        System.out.println(clonedCollege);





    }
}

class College implements Cloneable{

    private String name;
    private int id;
    private Teacher teacher;

    public College(String name, int id, Teacher teacher) {
        this.name = name;
        this.id = id;
        this.teacher = teacher;
    }

    @Override
    protected College clone() throws CloneNotSupportedException {

        College college = (College) super.clone();
        return college;
    }

    public Teacher getTeacher() {
        return teacher;
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

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        College college = (College) o;
        return id == college.id && name.equals(college.name) && teacher.equals(college.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, teacher);
    }

    @Override
    public String toString() {
        return "College{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", teacher=" + teacher +
                '}';
    }
}
class Teacher {

    String teacherName;
    int teacherId;

    public Teacher(String teacherName, int teacherId) {
        this.teacherName = teacherName;
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId && teacherName.equals(teacher.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherName, teacherId);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherName='" + teacherName + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }

    public String getTeacherName() {
        return teacherName;
    }
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
