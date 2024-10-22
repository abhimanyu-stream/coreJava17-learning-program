package com.java17.interview.prepartion;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		
		System.out.println("enter digit");
		
		Scanner scan = new Scanner(System.in);
		int count = scan.nextInt();
		int factorialsum = 1;
		for(int i= 1; i <= count; i++) {
			
			
			factorialsum = factorialsum * i;
		}
		System.out.println(factorialsum);//5->120

	}

}
