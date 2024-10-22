package com.java17.interview.prepartion;

import java.io.IOException;

public class ExceptionHirarachyUnCheckedExceptionParentAndSubClass {

    public static void main(String[] args) {


        // Note:-
        // NullPointerException raised by BChild class need not to be propagated to the Aparent class because it will be automatically propagated
        //  upto calling method then main method and then up to the JVM

        AParent ap = new BChild();
        ap.methodA();
    }


}

class AParent {

    public void methodA() {

    }

}
class BChild extends AParent{
    public void methodA() {
        throw new NullPointerException();//UnChecked Exception
    }


}
