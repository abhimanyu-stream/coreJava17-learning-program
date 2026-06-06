package com.java17.interview.prepartion;

public class SingletonClassEagerInitializationThreadSafe {

    /**
     *
     * Eager-Initialized Thread-Safe Singleton
     * The eager-initialized thread-safe singleton creates an instance of the class at the time of class loading.
     * It initializes the instance eagerly, even before it is requested. Additionally, it ensures thread safety by using synchronization (making the getInstance() method
     * synchronized).
     * */
    
    private static final SingletonClassEagerInitializationThreadSafe singletonClassEagerInitializationThreadSafe = new SingletonClassEagerInitializationThreadSafe();
    private SingletonClassEagerInitializationThreadSafe(){}

    public static synchronized SingletonClassEagerInitializationThreadSafe getInstance(){
        return singletonClassEagerInitializationThreadSafe;
    }


    public static void main(String[] args) {
        SingletonClassEagerInitializationThreadSafe s1 = SingletonClassEagerInitializationThreadSafe.getInstance();
        SingletonClassEagerInitializationThreadSafe s2 = SingletonClassEagerInitializationThreadSafe.getInstance();
        System.out.println(s1 == s2);//ture
        System.out.println(s1.hashCode() == s2.hashCode());//true
        System.out.println(s1.equals(s2));//true

    }

}
