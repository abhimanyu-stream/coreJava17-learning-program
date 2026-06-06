package com.java17.interview.prepartion;

import org.slf4j.Logger;

import java.io.*;
import java.util.Comparator;

public class LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodNotOverridden implements Serializable{
    @Serial
    private static final long serialVersionUID = - 7892003146944576774L;
// static transient volatile
    // inheritance

    private String name;
    private int age;
    private Addrezzz addrezzz;// Addrezz is Serializable



    public LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodNotOverridden(String name, int age, Addrezzz addrezzz) {
        this.name = name;
        this.age = age;
        this.addrezzz = addrezzz;
    }

    @Override
    public String toString() {
        return "LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodNotOverridden{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addrezzz=" + addrezzz +
                '}';
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializable that = (LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializable) o;

        if (age != that.age) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(addrezzz, that.addrezzz);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (addrezzz != null ? addrezzz.hashCode() : 0);
        return result;
    }*/

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

    public Addrezzz getAddrezzz() {
        return addrezzz;
    }

    public void setAddrezzz(Addrezzz addrezzz) {
        this.addrezzz = addrezzz;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Addrezzz addrezzz = new Addrezzz("some message");
        LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodNotOverridden learnAllAboutSerialization = new LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodNotOverridden("nameone", 33, addrezzz);


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
        LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodNotOverridden deserializedLearnAllAboutSerialization = (LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsSerializableIfEqualsAndHashCodeMethodNotOverridden) in.readObject(); // Deserialize
        System.out.println(deserializedLearnAllAboutSerialization);
        in.close();
        fileIn.close();
        System.out.println(deserializedLearnAllAboutSerialization.hashCode());
        System.out.println(learnAllAboutSerialization.hashCode() == deserializedLearnAllAboutSerialization.hashCode());// false
        System.out.println(learnAllAboutSerialization.equals(deserializedLearnAllAboutSerialization));//false
        System.out.println(learnAllAboutSerialization == deserializedLearnAllAboutSerialization);//false



    }

}
class Addrezzz implements Serializable {

    @Serial
    private static final long serialVersionUID = 362224293496873302L;// On class name  put cursor and ALT Enter
    String message;

    public Addrezzz() {
        this.message="Default value";
    }// this super class must have a default constructor otherwise //runtime exception: InvalidClassException thrown
    public Addrezzz(String str){
        this.message= str;
    }

    @Override
    public String toString() {
        return "Addrezzz{" +
                "message='" + message + '\'' +
                '}';
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addrezzz addrezzz = (Addrezzz) o;

        return Objects.equals(message, addrezzz.message);
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
