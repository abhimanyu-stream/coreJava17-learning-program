package com.java17.interview.prepartion;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		
		System.out.println("enter digit");


		Scanner scanner = new Scanner(System.in);
		int factorialOf = scanner.nextInt();
		int sum = 1;//as Factorial of 0! is 1 and no factorial for -ve number
		for(int i = 1; i <= factorialOf; i++){
			sum =sum * i;

		}
		System.out.println("Factorial of "+factorialOf+ " is "+sum);

	}

}
