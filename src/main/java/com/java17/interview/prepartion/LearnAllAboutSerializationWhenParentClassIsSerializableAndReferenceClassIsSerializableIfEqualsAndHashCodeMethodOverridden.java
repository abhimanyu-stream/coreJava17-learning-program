package com.java17.interview.prepartion;

import java.io.*;
import java.util.Objects;

public class LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden implements Serializable{


    private String name;
    private int age;
    private Addrezzzz addrezzzz;// Addrezz is Serializable

    public LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden(String name, int age, Addrezzzz addrezzzz) {
        this.name = name;
        this.age = age;
        this.addrezzzz = addrezzzz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden that = (LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden) o;

        if (age != that.age) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(addrezzzz, that.addrezzzz);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (addrezzzz != null ? addrezzzz.hashCode() : 0);
        return result;
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

    public Addrezzzz getAddrezzzz() {
        return addrezzzz;
    }

    public void setAddrezzzz(Addrezzzz addrezzzz) {
        this.addrezzzz = addrezzzz;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Addrezzzz addrezzzz = new Addrezzzz("some message");
        LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden learnAllAboutSerialization = new LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden("nameone", 33, addrezzzz);


        System.out.println(learnAllAboutSerialization);


        FileOutputStream fileOut = new FileOutputStream("D:\\tmp\\learnAllAboutSerialization.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        // Serialize the LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializable object
        out.writeObject(learnAllAboutSerialization);

        out.close();
        fileOut.close();
        System.out.println(learnAllAboutSerialization.hashCode());


        FileInputStream fileIn = new FileInputStream("D:\\tmp\\learnAllAboutSerialization.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden deserializedLearnAllAboutSerialization = (LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodOverridden) in.readObject(); // Deserialize
        System.out.println(deserializedLearnAllAboutSerialization);
        in.close();
        fileIn.close();
        System.out.println(deserializedLearnAllAboutSerialization.hashCode());
        System.out.println(learnAllAboutSerialization.hashCode() == deserializedLearnAllAboutSerialization.hashCode());//true
        System.out.println(learnAllAboutSerialization.equals(deserializedLearnAllAboutSerialization));//true
        System.out.println(learnAllAboutSerialization == deserializedLearnAllAboutSerialization);//false


    }

}
class Addrezzzz implements Serializable {

    String message;

    public Addrezzzz() {
        this.message="Default value";
    }// this super class must have a default constructor otherwise //runtime exception: InvalidClassException thrown
    public Addrezzzz(String str){
        this.message= str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addrezzzz addrezzzz = (Addrezzzz) o;

        return Objects.equals(message, addrezzzz.message);
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Addrezzzz{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
