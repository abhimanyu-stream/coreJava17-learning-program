package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ValidParenthesesCheck {

    public static void main(String[] args) {

        SpringApplication.run(ValidParenthesesCheck.class, args);

        String str1 = "()";
        System.out.println(isValidParentheses2(str1));
        String str2 = "(]";
        System.out.println(isValidParentheses2(str2));
        String str3 = "[{()}]";
        System.out.println(isValidParentheses2(str3));
        String str4 = "()[]{}";
        System.out.println(isValidParentheses2(str4));

        String str5 = "(-}";
        System.out.println(isValidParentheses2(str5));





    }


    public static boolean isValidParentheses(String str) {


        Stack<Character> stack = new Stack<>();

        for(Character c:str.toCharArray()){
            //open-closed
            if(c == '(' || c == '{' || c == '['){

                stack.push(c);

            }else if(c == ')' || c == '}' || c == ']'){

                if(stack.isEmpty())
                    return  false;
                // pop
                Character top = stack.pop();

                // then  c-closed && !top-open
                if(c == ')' && top != '('){
                    return false;
                }
                if(c == '}' && top != '{'){
                    return false;
                }
                if(c == ']' && top != '['){
                    return false;
                }

            }
}

        return  stack.isEmpty();
    }

    public static boolean isValidParentheses2(String str) {

        Stack<Character> stack = new Stack<>();
        char[] charArray = str.toCharArray();

        for(int i = 0; i < charArray.length; i++){

            if(charArray[i] == '(' || charArray[i] == '{' || charArray[i] == '['){
                stack.push(charArray[i]);
            }else if(charArray[i] == ')' || charArray[i] == '}' || charArray[i] == ']'){
                if(stack.isEmpty())
                    return false;

                Character topValue = stack.pop();
                if(charArray[i] == ')'  && topValue != '('){

                    return false;
                }
                if(charArray[i] == '}'  && topValue != '{'){

                    return false;
                }
                if(charArray[i] == ']'  && topValue != '['){

                    return false;
                }
            }

        }


        return stack.isEmpty();
    }

}
