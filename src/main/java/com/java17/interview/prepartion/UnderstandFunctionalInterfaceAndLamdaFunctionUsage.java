package com.java17.interview.prepartion;

public class UnderstandFunctionalInterfaceAndLamdaFunctionUsage {

	public static void main(String[] args) {

		//FindSquareRoot f = (n) -> { return Math.sqrt(n);};
		FindSquareRoot f = Math::sqrt;
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
	//double findsqrt5(int n);
	static String helloMessage(){

		return "Hello";
	}
	static String helloMessage1(){

		return "Hello1";
	}
	static String helloMessage2(){

		return "Hello2";
	}
	default String defaultMethod(){
		return "default";
	}// its backward compatibility support

	default String defaultMethod2(){
		return "default2";
	}
	default String defaultMethod3(){
		return "default3";
	}
}
class Labour implements  FindSquareRoot{

	@Override
	public double findsqrt(int n) {
		return Math.sqrt(n);
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
