package com.java17.interview.prepartion;

public class MindTest {

    public static void main(String[] args) {


        MindTest o = null;
        System.out.println(o);//null
        //System.out.println(o instanceof null);// Not Statement, compile time error
        Object obj = null;
        System.out.println("" + o instanceof String);// true
        //System.out.println("" + obj instanceof null);// Not Statement, compile time error

        Literature l = new Literature();
        l.readBook();
        Books.bookInformation();

    }
}


interface  Books{

    public void addBook();

    public default void readBook(){
        System.out.println("default read book");
    }
    public static void bookInformation(){
        System.out.println("all books info");
    }
}

class Literature implements  Books{

    //The subclass Literature is implementing interface Books, and it has the default method.
    // As method is default, so this subclass need not override. Subclass is not forced to override the default method.
    // Subclass can use it as it is or override it by own logic for implementation
    //also the implementation provided in the interface remains as it was and any other class can still use it with that implementation


    @Override
    public void addBook() {

    }

    // cannot use default in subclass
    public void readBook(){
        System.out.println("literature read book");
    }
}