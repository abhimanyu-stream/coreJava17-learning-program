package com.java17.interview.prepartion;

public class SingletonClassLazyInitializationThreadSafe {

    private static SingletonClassLazyInitializationThreadSafe singletonClassLazyInitializationThreadSafe = null;
    //private SingletonClassLazyInitializationThreadSafe(){}

    public static  SingletonClassLazyInitializationThreadSafe getInstance(){

            // due to static  variable  synchronized (SingletonClassLazyInitializationThreadSafe.class)
            synchronized (SingletonClassLazyInitializationThreadSafe.class){
                if(singletonClassLazyInitializationThreadSafe == null){
                    singletonClassLazyInitializationThreadSafe = new SingletonClassLazyInitializationThreadSafe();
                }
            }

        return singletonClassLazyInitializationThreadSafe;
    }

    public static void main(String[] args) {
        SingletonClassLazyInitializationThreadSafe s1 = SingletonClassLazyInitializationThreadSafe.getInstance();
        SingletonClassLazyInitializationThreadSafe s2 = SingletonClassLazyInitializationThreadSafe.getInstance();
        System.out.println(s1 == s2);//ture
        System.out.println(s1.hashCode() == s2.hashCode());//true
        System.out.println(s1.equals(s2));//true

    }

}
