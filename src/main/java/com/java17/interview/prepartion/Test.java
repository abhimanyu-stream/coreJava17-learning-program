package com.java17.interview.prepartion;


public class Test {
    public static void main (String args[])
    {
        Employeez e = new Employeez();
    }

}
class Personz
{
    public Personz()
    {
        System.out.println("Person class constructor called");
    }
}
class Employeez extends Personz
{
    public Employeez()
    {
        System.out.println("Employee class constructor called");
    }

}
/**
 * Person class constructor called
 * Employee class constructor called
 */