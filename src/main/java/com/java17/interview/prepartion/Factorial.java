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

		//Enter a positive integer: 7982
		int number = 7982;
		int digit = number;


		 sum = 0;

		while (digit != 0) {
			sum += digit % 10; // take last digit
			digit = digit / 10; // remove last digit
		}

		System.out.println(sum); // 26


	}

}
