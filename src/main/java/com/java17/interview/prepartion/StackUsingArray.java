package com.java17.interview.prepartion;

import java.util.Arrays;

public class StackUsingArray {

    public static void main(String[] args) {

        Stackk stack = new Stackk(5);

        stack.push(1);//index 0
        stack.push(2);//index 1
        stack.push(3);//index 2
        stack.push(4);//index 3
        stack.push(5);//index 4

        System.out.println(stack);

        stack.push(6);   //Stack is full. Cannot push element.

        Object popped = stack.pop();
        System.out.println(popped);

        System.out.println(stack);

        stack.push(6);

        System.out.println(stack);

    }
}

class Stackk{
    private int maxSize;
    private Object[] stackArray;
    private int top;


    public Stackk(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new Object[maxSize];
        this.top = -1;
    }

    //push
    public void push(Object value){
        //check for overflow/full
        if(isFull()){

            return ;
        }
            top++;
            stackArray[top] = value;

    }

    //pop

    public Object pop(){
        // check for stack underflow/empty
        if(isEmpty()){
            System.out.println("stack is empty now");
        }


            Object popped = stackArray[top];
            int oldTop = top;
            top--;
            stackArray[oldTop] = null;
            return popped;


    }
    public Object peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot peek element.");
            return -1;
        }
        return stackArray[top];
    }
    private boolean isFull() {
        if(top == maxSize - 1){
            return true;
        }
        return false;
    }
    private boolean isEmpty() {
        if(top == -1){
            return true;
        }
        return false;

    }
    //peek
    //isEmpty
    //isFull












    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Object[] getStackArray() {
        return stackArray;
    }

    public void setStackArray(Object[] stackArray) {
        this.stackArray = stackArray;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Stackk{" +
                "maxSize=" + maxSize +
                ", stackArray=" + Arrays.toString(stackArray) +
                ", top=" + top +
                '}';
    }
}