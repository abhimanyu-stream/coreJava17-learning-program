package com.java17.interview.prepartion;
public class BuilderPattern {
    public static void main(String[] args) {
        CollegeStudent s = CollegeStudent.builder()
                .setName("Rahul")
                .setAge(20)
                .build();
        System.out.println(s);
        System.out.println();
    }

}

class CollegeStudent {
    private String name;
    private int age;


    //private constructor with static class arguments
    private CollegeStudent(CollegeStudentBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }


    @Override
    public String toString() {
        return "CollegeStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // static factory method

    public static CollegeStudentBuilder builder() {
        return new CollegeStudentBuilder();
    }
    static class CollegeStudentBuilder {
        private String name;
        private int age;

        CollegeStudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        CollegeStudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        CollegeStudent build() {
            return new CollegeStudent(this);
        }
    }
}