package com.java17.interview.prepartion;

import java.io.*;

public class LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsNotSerializable implements Serializable {


    private final String name;
    private final int age;
    private final Addrezz addrezz;// Addrezz is Not Serializable, so it will produce exception
    // Exception in thread "main" java.io.NotSerializableException: com.java17.interview.prepartion.Addrezz

    public LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsNotSerializable(String name, int age, Addrezz addrezz) {
        this.name = name;
        this.age = age;
        this.addrezz = addrezz;
    }

    public static void main(String[] args) throws IOException {
        Addrezz addrezz = new Addrezz("some message");
        LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsNotSerializable learnAllAboutSerialization = new LearnAllAboutSerializationWhenParentClassIsSerializableAndReferenceClassIsNotSerializable("nameone", 33, addrezz);
        FileOutputStream fileOut = new FileOutputStream("D:\\tmp\\learnAllAboutSerialization.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        // Serialize the LearnAllAboutSerialization object
        out.writeObject(learnAllAboutSerialization);//Exception in thread "main" java.io.NotSerializableException: com.java17.interview.prepartion.Addrezz

        out.close();
        fileOut.close();
    }


}

class Addrezz{

    String message;

    public Addrezz() {
        this.message="Default value";
    }// this super class must have a default constructor otherwise //runtime exception: InvalidClassException thrown
    public Addrezz(String str){
        this.message= str;
    }

}