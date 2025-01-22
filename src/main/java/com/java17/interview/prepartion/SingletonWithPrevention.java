package com.java17.interview.prepartion;

import java.io.Serial;
import java.io.Serializable;

public class SingletonWithPrevention implements Serializable, Cloneable {


    private static SingletonWithPrevention singletonWithPrevention;

    public SingletonWithPrevention(){
        if(singletonWithPrevention != null){
            throw new IllegalStateException("Instance already created !");
        }
    }
    public static SingletonWithPrevention getInstance(){

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
    protected Object readResolve(){
        return getInstance();
    }
}
