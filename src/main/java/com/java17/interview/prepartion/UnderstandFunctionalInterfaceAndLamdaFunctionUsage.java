package com.java17.interview.prepartion;

public class UnderstandFunctionalInterfaceAndLamdaFunctionUsage {

	public static void main(String[] args) {
		
		
		FindSquareRoot f = (n) -> { return Math.sqrt(n);};
		System.out.println(f.findsqrt(23));

		System.out.println(f.findsqrt(4));
		System.out.println(FindSquareRoot.helloMessage());
		System.out.println(new Labour().defaultMethod());


	}

}

interface FindSquareRoot{
	
	double findsqrt(int n);
	// static methods ok
	// default methods ok
	// public abstract internal access specifier
	static String helloMessage(){

		return "Hello";
	}
	default String defaultMethod(){
		return "default";
	}


}
class Labour implements  FindSquareRoot{

	@Override
	public double findsqrt(int n) {
		return 0;
	}

	//@Override
	//public String defaultMethod() {
		//return FindSquareRoot.super.defaultMethod();
	//}

	@Override
	public String defaultMethod() {
		return "Labour defaultMethod()";
	}
}
