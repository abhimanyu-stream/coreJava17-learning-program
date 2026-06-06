package com.java17.interview.prepartion;

public class SingletonDesignPatternStaticBlockEagerInitialization {

    private static SingletonDesignPatternStaticBlockEagerInitialization instance = null;

    private SingletonDesignPatternStaticBlockEagerInitialization() {

        if(instance != null)
            throw new RuntimeException("You are tring to breaking Singleton Design Pattern");
    }

    static {

        try {
            if (instance == null)
                instance = new SingletonDesignPatternStaticBlockEagerInitialization();


        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }
    public static SingletonDesignPatternStaticBlockEagerInitialization getInstance(){
        return instance;
    }

    public static void main(String[] args) {

        SingletonDesignPatternStaticBlockEagerInitialization s1 = SingletonDesignPatternStaticBlockEagerInitialization.getInstance();
        SingletonDesignPatternStaticBlockEagerInitialization s2 = SingletonDesignPatternStaticBlockEagerInitialization.getInstance();
        System.out.println(s1 == s2);//ture
        System.out.println(s1.hashCode() == s2.hashCode());//true
        System.out.println(s1.equals(s2));//true


    }
}
