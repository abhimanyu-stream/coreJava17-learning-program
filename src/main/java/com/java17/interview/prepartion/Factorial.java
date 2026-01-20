package com.java17.interview.prepartion;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		
		System.out.println("enter digit");

// factorial of 0, 1 is 1
		Scanner scanner = new Scanner(System.in);
		int factorialOf = scanner.nextInt();
		int sum = 1;//as Factorial of 0! is 1, 1! is 1 and no factorial for -ve number
		for(int i = 1; i <= factorialOf; i++){
			sum =sum * i;// 1 * 1 + 1 * 2 + 3 * 3 + 12 * 4 + () * 5 = 120                   //[ do not sum +=sum * i]
		}
		System.out.println("Factorial of "+factorialOf+ " is "+sum);

		//Factorial
		//sum = 1
		//1*1 = 1
		//1*2 = 2
		//2*3 = 6
		//6*4= 24
		//24*5= 120


	}

}
