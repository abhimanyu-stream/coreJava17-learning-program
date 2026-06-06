package com.java17.interview.prepartion;

public class ExceptionHandling {
    public static void main(String[] args) {

        // we can create abstract class reference variable
        // we can create Interface reference variable

        ITSecurity cyberscurity = new CyberSecurity();
        cyberscurity.itsec();
    }

}

abstract class ITSecurity {

    public abstract void itsec();

}
class CyberSecurity extends ITSecurity {

    @Override
    public void itsec() {
        System.out.println("Cyber Security not enabled");
        throw new SecurityException();//unchecked exception
    }
}
