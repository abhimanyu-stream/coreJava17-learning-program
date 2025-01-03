public class InterviewMindTestSuperChild {

    public static void main(String[] args) {


        Base b = new Derived();
        b.baseMethod();




        Employee e = new Employee();



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

class Person
{
    public Person()
    {
        System.out.println("Person class constructor called");
    }
}
class Employee extends Person
{
    public Employee()
    {
        System.out.println("Employee class constructor called");
    }

}
/**
 * Person class constructor called
 * Employee class constructor called
 */