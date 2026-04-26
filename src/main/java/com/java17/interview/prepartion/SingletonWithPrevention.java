package com.java17.interview.prepartion;

import java.io.Serial;
import java.io.Serializable;

public final class SingletonWithPrevention implements Serializable, Cloneable {


    /**
     * If you must use class-based Singleton:
     *
     * Use:
     *
     * final class
     * private constructor
     *
     * volatile instance
     *
     * double-checked locking
     *
     * readResolve()
     *
     * clone() prevention
     *
     * But remember:
     *
     * ❗ Still not as safe as Enum
     */


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

    public enum SingletonWithPrevention2 {
        INSTANCE;
    }
}
