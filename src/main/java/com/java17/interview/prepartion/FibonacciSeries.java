package com.java17.interview.prepartion;

import java.util.Scanner;

public class FibonacciSeries {

	public static void main(String[] args) {

		System.out.println("enter the digit");
		Scanner scan = new Scanner(System.in);
		int count= scan.nextInt();
		
		int n1 = 0;
		int n2 = 1;
		int n3;
		
		
		System.out.println(n1);
		System.out.println(n2);
		for(int i =0; i < count; i++) {
			n3 = n1+ n2;
			System.out.println(n3);
			
			n1 = n2;
			n2 = n3;
			
			
		}

	}

}
