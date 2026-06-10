package com.java17.interview.prepartion;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeContract {
    /**
     * 
In Java, if two objects are equal according to equals(), they must return the same hash code from hashCode(). This is called the contract between equals() and hashCode().

Rules of the contract
If a.equals(b) == true → a.hashCode() == b.hashCode() must be true
If a.hashCode() == b.hashCode() → a.equals(b) may be true or false (collision possible)
If you override equals(), you should also override hashCode()

     */



   public static void main(String[] args) {
         Phone e1 = new Phone(1, "Nokia");
        Phone e2 = new Phone(2, "Philips");

        System.out.println("" + e1.equals(e2));
        System.out.println(e1.hashCode() == e2.hashCode());

        HashSet<Phone> hashSetPhone = new HashSet<>();
        hashSetPhone.add(e1);
        hashSetPhone.add(e2);
        hashSetPhone.add(e1);
        System.out.println(hashSetPhone);


        String s1 = "nokia";
        String s2 = "nokia";
        String s3 = "philips";
           System.out.println(s1.equals(s2));
              System.out.println(s1.hashCode() == s2.hashCode());

               System.out.println(s1.equals(s3));
              System.out.println(s1.hashCode() == s3.hashCode());


        
   }
        

    
}


class Phone{
    private int id;
    private String name;


    public Phone(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name);

    }
     public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        Phone p = (Phone)obj;

        return p.id == this.id && p.name.equals(this.name);


     }

     public String toString(){
        return "Phone[ "+ this.id + " " + this.name +" ]";
     }
    
}
    

    
