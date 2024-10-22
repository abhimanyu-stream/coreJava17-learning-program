package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StackCustomEmplementationUsingArray {


    public static void main(String[] args) {

        SpringApplication.run(StackCustomEmplementationUsingArray.class, args);

        Stack<Character> stack = new Stack<>();

        System.out.println(stack.isEmpty());
        stack.push(11);
        int size = stack.size();
        System.out.println(size);
        stack.push(33);
        System.out.println(stack.size());
        stack.push(44);
        stack.push(55);
        stack.push(66);


        int popped = stack.pop();
        System.out.println(popped);

        stack.push(77);

        int peeked = stack.peek();


        stack.display();
        System.out.println(stack.isEmpty());



    }
}

class Stack<C> {

    int top;
    int max_size = 5;
    int[] stackArray = new int[max_size];

    public Stack(){
        this.top = -1;

    }



    // push

    public void  push(int input){

        // check overflow
        if(top > max_size -1){
            System.out.println("Stack is Full");

        }else{
            top++;
            stackArray[top] = input;

            System.out.println("Element added in Stack is : "+ input);
        }



    }


    // pop

    public Character pop(){
        int popped = -1;
        // check underflow

        if(top == -1){
            System.out.println("Stack is empty");
        }else{

            popped = stackArray[top--];


        }
        return (Character.valueOf((char) popped));
    }

    // peek

    public int peek(){


        // check underflow

        if(top == -1){
            System.out.println("Stack is empty");
        }

        int  popped = stackArray[top];
        return popped;
    }

    // display

    public void display(){

        for(int i = top; i >= 0; i--){
            System.out.println(stackArray[i]);

        }
    }

    public int size(){

        int size = top + 1;
        return size;

    }
    public boolean isEmpty(){

        return top < 0;
    }
}
