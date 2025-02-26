package com.java17.interview.prepartion;

import java.util.Arrays;

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
		for(int i = 0; i < studentMarks.length; i++) { // studentMarks.length is all row count
			String studentName = studentMarks[i][0]; //  at each i, the column studentMarks[i][0] retrieved
			for(int j = 1; j < studentMarks[i].length; j++) { // at i = 0 ; studentMarks[i].length is column count

				sum += Integer.parseInt(studentMarks[i][j]);

				count++;



			}

			avg = (double) sum /count;

			System.out.println(studentName + "  " + avg);
		}
		// enhanced for loop
		for (String[] studentMark : studentMarks) { // studentMarks.length is all row count
			String studentName = studentMark[0]; //  at each i, the column studentMarks[i][0] retrieved
			for (int j = 1; j < studentMark.length; j++) { // at i = 0 ; studentMarks[i].length is column count

				sum += Integer.parseInt(studentMark[j]);

				count++;


			}

			avg = (double) sum / count;

			System.out.println(studentName + "  " + avg);
		}


		// Using Streams to calculate average for each student
		Arrays.stream(studentMarks)
				.forEach(student -> {
					String studentName = student[0];
					double avgg = Arrays.stream(student, 1, student.length)  // Skip the name and parse marks
							.mapToInt(Integer::parseInt)   // Convert marks to integers
							.average()                     // Calculate average
							.orElse(0.0);                  // In case of no marks, default to 0.0

					System.out.println(studentName + "  " + avgg);
				});


		Arrays.stream(studentMarks)
				.forEach(student->{
					String name = student[0];
					double v = Arrays.stream(student, 1, student.length)
							.mapToInt(Integer::parseInt)
							.average()
							.orElse(0.0);
					System.out.println(name +" "+v);
				});

	}

}
