package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ValidateCreditCardNumberAndMapProperty {

    public static void main(String[] args) {


        Map<String, String> hashMap = new HashMap<>();

//or
        hashMap.put(null, null);
        System.out.println(hashMap);
        hashMap.put(null, null);
        System.out.println(hashMap);
        hashMap.put(null, null);
        System.out.println(hashMap);
        hashMap.put("abc", null);
        System.out.println(hashMap);
        hashMap.put("abc", null);// when key are same then value gets updated
        System.out.println(hashMap);
        hashMap.put(null, null);
        System.out.println(hashMap);
        hashMap.put("nullkey", null);
        System.out.println(hashMap);

        hashMap.put("abc", "abc");
        System.out.println(hashMap);
        hashMap.put("abc", "abce");// value part of map object gets overridden
        System.out.println(hashMap);
        hashMap.put("abc", "abced");// value part of map object gets overridden
        System.out.println(hashMap);


        // The following is a credit card check algorithm:
        //From the last digit moving backwards, double every second digit
        //If the doubled number is greater than 9, add its two digits together
        //Sum all card digits together
        //If the total ends in a zero, then the number is a valid credit card

/*
* Validating Credit Card Numbers
Get the app


Copilot
Your everyday AI companion

Create a portrait of a dog in a bold impressionistic painted style

Summarize this week's top news stories

Compose a country song about me and my best friend
SunoTerms | Privacy

Help me understand the rules of pickleball and any other helpful tips

Compose an electronic song about camping
SunoTerms | Privacy

Compose a pop song about an astronaut returning to Earth
SunoTerms | Privacy

What are some ways to overcome procrastination

What are some of the best ways to save money

Compose a blues song that laments the loss of time
SunoTerms | Privacy

Coach me through an upcoming job interview with some mock questions

How do you prioritize which items to keep, donate or throw away during the organization pr…

Compose a folk song about a day in the life of a roaming cloud
SunoTerms | Privacy

Help me find furniture for my small apartment

Write JavaScript code to check if a submitted value for phone number field is in a common fo…

Compose a folk song about legendary adventures on the high seas
SunoTerms | Privacy
Hi abhimanyu, ask me anything or pick a suggestion to get started!


Start a focus session

Find best practices for remote team collaboration

Provide tips for maintaining a healthy work-life balance

Take a screenshot
Copilot uses AI. Check for mistakes. Terms Privacy
Get Copilot Pro
You
// The following is a credit card check algorithm:
        //From the last digit moving backwards, double every second digit
        //If the doubled number is greater than 9, add its two digits together
        //Sum all card digits together
        //If the total ends in a zero, then the number is a valid credit card


        String str = "4242424242426742";
Copilot
Certainly! The given algorithm checks the validity of a credit card number. Here’s how it works:

Double every second digit from the right (moving backwards):
Starting from the last digit (2), double it (2 * 2 = 4).
Move to the second-to-last digit (4), double it (4 * 2 = 8).
Continue this process for all even-indexed digits.
If the doubled number is greater than 9, add its two digits together:
For example, if the doubled digit is 14, add 1 + 4 = 5.
Sum all the digits together:
Add up all the original digits and the modified even-indexed digits.
Check if the total ends in zero:
If the sum modulo 10 equals zero, the credit card number is valid.
For the given credit card number “4242424242426742”:

Doubled digits: 8, 4, 4, 4, 8, 4, 4, 2
Sum of all digits: 8 + 2 + 4 + 2 + 4 + 2 + 4 + 2 = 28
Since 28 modulo 10 equals 0, this credit card number is valid.
*
*
* */
        String str = "4242424242426742";
        String cardNumber = "4242424242426742";
        boolean isValid = isValidCreditCard(cardNumber);
        System.out.println("Is the credit card valid? " + isValid);
    }

    public static boolean isValidCreditCard(String cardNumber) {
        int sum = 0;
        boolean doubleDigit = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit % 10 + digit / 10;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return sum % 10 == 0;
    }

    public static boolean isValidCreditCardUsingStreamAPI(String cardNumber) {
        int sum = IntStream.range(0, cardNumber.length())
                .map(i -> Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1 - i)))
                .map(i -> (i % 2 == 0) ? i * 2 : i)
                .map(i -> (i > 9) ? i % 10 + i / 10 : i)
                .sum();

        return sum % 10 == 0;
    }
}