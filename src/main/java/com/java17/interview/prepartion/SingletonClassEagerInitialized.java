package com.java17.interview.prepartion;

public class SingletonClassEagerInitialized {
	/**
	 *
	 * Eager-Initialized Thread-Safe Singleton
	 * The eager-initialized thread-safe singleton creates an instance of the class at the time of class loading.
	 * It initializes the instance eagerly, even before it is requested. Additionally, it ensures thread safety by using synchronization (making the getInstance() method
	 * synchronized).
	 * */

	private static final SingletonClassEagerInitialized singletonClassEagerInitialized = new SingletonClassEagerInitialized();

	private SingletonClassEagerInitialized(){}
	public static  SingletonClassEagerInitialized getInstance(){
		// Non-Thread safe method
		return singletonClassEagerInitialized;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonClassEagerInitialized s1 = SingletonClassEagerInitialized.getInstance();
		SingletonClassEagerInitialized s2 = SingletonClassEagerInitialized.getInstance();
		System.out.println(s1 == s2);//ture
		System.out.println(s1.hashCode() == s2.hashCode());//true
		System.out.println(s1.equals(s2));//true


	}

}
