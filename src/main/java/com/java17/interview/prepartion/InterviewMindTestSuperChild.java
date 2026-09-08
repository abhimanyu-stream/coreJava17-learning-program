package com.java17.interview.prepartion;

public class InterviewMindTestSuperChild {

    public static void main(String[] args) {


        Base b = new Derived();
        b.baseMethod();




        EmployeeN e = new EmployeeN();



    }

}
class Base
{
    public void baseMethod()
    {
        System.out.println("BaseMethod called ...");
    }
}
class Derived extends Base
{
    public void baseMethod()
    {
        System.out.println("Derived method called ...");
    }
}
/**
 * output:--
 *
 * Derived method called ...
 */

class PersonK
{
    public PersonK()
    {
        System.out.println("Person class constructor called");
    }
}
class EmployeeN extends PersonK
{
    public EmployeeN()
    {
        System.out.println("Employee class constructor called");
    }

}
/**
 * Person class constructor called
 * Employee class constructor called
 */