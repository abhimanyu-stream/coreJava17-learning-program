package com.java17.interview.prepartion;

public class SingletonClassLazyInitializationNonThreadSafe {

	private static SingletonClassLazyInitializationNonThreadSafe singletonClassLazyInitializationNonThreadSafe = null;
	private SingletonClassLazyInitializationNonThreadSafe(){}

	public static  SingletonClassLazyInitializationNonThreadSafe getInstance(){
		//no synchronized key word used so no threading concepts come in picture, hence its non thread safe

			if(singletonClassLazyInitializationNonThreadSafe == null){
				singletonClassLazyInitializationNonThreadSafe = new SingletonClassLazyInitializationNonThreadSafe();
			}


		return singletonClassLazyInitializationNonThreadSafe;
	}

	public static void main(String[] args) {
		SingletonClassLazyInitializationNonThreadSafe s1 = SingletonClassLazyInitializationNonThreadSafe.getInstance();
		SingletonClassLazyInitializationNonThreadSafe s2 = SingletonClassLazyInitializationNonThreadSafe.getInstance();
		System.out.println(s1 == s2);//ture
		System.out.println(s1.hashCode() == s2.hashCode());//true
		System.out.println(s1.equals(s2));//true

	}


}
