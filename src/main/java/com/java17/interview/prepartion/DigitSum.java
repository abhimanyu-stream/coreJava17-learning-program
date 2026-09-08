package com.java17.interview.prepartion;

import java.util.Scanner;

public class DigitSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        scanner.close();

        //Enter a positive integer: 7982
        int digit = number;


        int sum = 0;

        while (digit != 0) {
            sum += digit % 10; // take last digit
            digit = digit / 10; // remove last digit
        }

        System.out.println(sum); // 26
        /***
         * Walk it once to see the gears turn:
         *
         * 7982 → 2 → sum = 2
         *
         * 798 → 8 → sum = 10
         *
         * 79 → 9 → sum = 19
         *
         * 7 → 7 → sum = 26
         *
         * 0 → stop
         *
         * Why digit != 0 matters:
         */
        
        
        
        System.out.println("-------------------------------------------");
        //Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer: ");

        int number2 = scanner.nextInt();

        scanner.close();

        // 7982 -> 7 + 9 + 8 + 2 = 26

        int sumUsingStream =
                String.valueOf(number2)
                        .chars()
                        .map(Character::getNumericValue)
                        .sum();

        System.out.println("sumUsingStream = " + sumUsingStream);

    }
}

/**
 * 
 * Output:

Enter a positive integer: 7982
Sum = 26
How the Stream works

For:

7982

First:

String.valueOf(number)

produces:

"7982"

Then:

.chars()

produces the Unicode/ASCII integer values of the characters:

'7' → 55
'9' → 57
'8' → 56
'2' → 50

Then:

.map(Character::getNumericValue)

converts them to:

7
9
8
2

Finally:

.sum()

does:

7 + 9 + 8 + 2 = 26

So the pipeline is:

7982
  │
  ▼
"7982"
  │
  │ chars()
  ▼
55, 57, 56, 50
  │
  │ map(Character::getNumericValue)
  ▼
7, 9, 8, 2
  │
  │ sum()
  ▼
26
Another Stream approach

You can also use map() with subtraction:

int sum =
        String.valueOf(number)
                .chars()
                .map(c -> c - '0')
                .sum();

Here:

c - '0'

converts:

'7' - '0' = 7
'9' - '0' = 9
'8' - '0' = 8
'2' - '0' = 2

For interviews, I would remember this compact version:

int sum = String.valueOf(number)
        .chars()
        .map(c -> c - '0')
        .sum();

Important: this String/Stream solution is excellent for interview demonstration, but your original % 10 / / 10 approach is the fundamental arithmetic solution and does not require converting the number to a string.

 */
