package com.java17.interview.prepartion;

import java.io.Serial;
import java.io.Serializable;

public class SingletonWithPrevention implements Serializable, Cloneable {


    private static volatile  SingletonWithPrevention singletonWithPrevention;

    private SingletonWithPrevention(){
        if(singletonWithPrevention != null){
            throw new IllegalStateException("Instance already created !");
        }
    }
    public static SingletonWithPrevention getInstance(){// Factory method

        if(singletonWithPrevention == null){
            synchronized (SingletonWithPrevention.class){
                if(singletonWithPrevention == null){
                    singletonWithPrevention = new SingletonWithPrevention();
                }
            }

        }
        return singletonWithPrevention;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
       throw new CloneNotSupportedException("Cloning not supported");

    }
    @Serial
    protected Object readResolve(){
        return getInstance();
    }
}
