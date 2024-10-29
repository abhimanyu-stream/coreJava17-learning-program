package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class ListMapAndPeek {


    public static void main(String[] args) {

        // list of Student

        List<Student> listOfStudent = new ArrayList<>();


        listOfStudent.add(new Student("Abhi",50.5f, 50));
        listOfStudent.add(new Student("Cally",60.5f, 60));
        listOfStudent.add(new Student("March",70f, 70));
        listOfStudent.add(new Student("Oracle",80f, 80));

        // stream api add marks where attendene is gr 60

       // System.out.println(listOfStudent.stream().filter(f->f.getAttendence() > 60).map(m->m.getMarks() + 10).collect(Collectors.toMap(Collectors.groupingBy(Student::new), Collectors.)));

        Map<Student, Float>  mapOfStudent = new HashMap<>();
        for(Student s:listOfStudent){
            mapOfStudent.put(s, s.getAttendence());

        }

     //  System.out.println( mapOfStudent.entrySet().stream().filter(f->f.getKey().getAttendence()>60).map(m->m.getKey().getMarks()+10).collect(Collectors.toList()));

        List<Student> modifiedStudents = listOfStudent.stream()
                .filter(student -> student.getAttendence() > 60)
                .peek(student ->  student.setMarks(student.getMarks() + 10) )
                .collect(Collectors.toList());

        // Print modified student objects

        System.out.println("modifiedStudents "+modifiedStudents);



        modifiedStudents.forEach(student ->
                System.out.println("Name: " + student.getName() +
                        ", Marks: " + student.getMarks() +
                        ", Attendance: " + student.getAttendence()));
    }

    /*
    complie time error method already defined

    public int add(int x, int y){
        return x + y;
    }
    public long add(int x, int y){
        return x + y;
    }*/




}




class Student {


    private String name;

    private float attendence;

    private int marks;

    public Student(String name, float attendence, int marks) {
        this.name = name;
        this.attendence = attendence;
        this.marks = marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttendence(float attendence) {
        this.attendence = attendence;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public float getAttendence() {
        return attendence;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("attendence=" + attendence)
                .add("marks=" + marks)
                .toString();
    }
}
