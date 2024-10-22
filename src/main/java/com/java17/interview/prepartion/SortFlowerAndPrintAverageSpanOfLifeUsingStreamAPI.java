package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Flower {
	// Your Code Goes Here..

	private String name;
	private int span;

	public Flower() {
	}

	public Flower(String name, int span) {
		this.name = name;
		this.span = span;

	}

	public String getName() {

		return name;
	}

	public int getSpan() {

		return span;
	}
	// Double averageLifeSpan(List<Flower> list);

	// List<Flower> sortFlowers(List<Flower> list);

	@Override
	public String toString() {
		return "Flower{" + "name='" + name + '\'' + ", span=" + span + '}';
	}

}

class FlowerSmell extends Flower {
	// Your Code Goes Here..

	

	
	Double averageLifeSpan(List<Flower> listOfFlower) {

		
		for (int i = 0; i < listOfFlower.size(); i++) {
			int total = 0;
			int avg = 0;
			total += listOfFlower.get(i).getSpan();
			avg = total / listOfFlower.size();
			System.out.println("The Average IS:" + avg);

		}
		return listOfFlower.stream().mapToDouble(d -> d.getSpan()).average().orElse(0.0);

	}

	List<Flower> sortFlowers(List<Flower> listOfFlower) {

		return listOfFlower.stream().sorted(Comparator.comparing(Flower::getName).reversed())
				.collect(Collectors.toList());
	}

}

public class SortFlowerAndPrintAverageSpanOfLifeUsingStreamAPI {
	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT */

		FlowerSmell fs = new FlowerSmell();
		List<Flower> list = new ArrayList<>();
		list.add(new Flower("Mahogany", 1));
		list.add(new Flower("Dahlia", 2));

		System.out.println(fs.averageLifeSpan(list));
		System.out.println(fs.sortFlowers(list));
		
	}
}