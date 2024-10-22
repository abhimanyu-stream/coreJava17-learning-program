package com.java17.interview.prepartion;

public class TwoDArrayStudentMarksFindAvgofEachStudent {

	public static void main(String[] args) {
		
		
		String[][] studentMarks = {
				
				{"Abhimanyu","88","90","77","60","86"},
		
				{"Callista","58","50","57","60","86"},
				{"Jeniffer","48","40","47","40","86"},
				{"Cally","38","30","37","30","36"},
				{"Miranda","82","20","27","20","26"},
				{"Puck","88","10","17","10","16"}
		
		};
		
		
		int count=0, sum = 0;
		double avg;
		for(int i = 0; i < studentMarks.length; i++) {
			String studentName = studentMarks[i][0];
			for(int j = 1; j < studentMarks[i].length; j++) {
				
				sum += Integer.parseInt(studentMarks[i][j]);
				
				count++;
				
				
				
			}
			
			avg = sum/count;
			
			System.out.println(studentName + "  " + avg);
		}

	}

}
