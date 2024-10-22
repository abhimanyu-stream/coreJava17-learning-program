package com.java17.interview.prepartion;

import java.io.IOException;

public class ExceptionHirarachyCheckedExceptionParentAndSubClass {


    public static void main(String[] args) throws Exception {
        A a = new B();
        a.methodA();
    }

}

class A {

    public void methodA() throws Exception {

    }

}
class B extends A{
    public void methodA() throws IOException {
        throw new IOException();//Checked Exception

    }
}