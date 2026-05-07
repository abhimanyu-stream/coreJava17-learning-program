package com.java17.interview.prepartion;

public class EvenOddThreadPrinter {

    public static void main(String[] args) throws InterruptedException {

        Printer printer = new Printer();
        Thread even = new Thread(()->{
            printer.printEven();
        }, "EvenThead");

        Thread odd = new Thread(()->{
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "OddThead");
        odd.start();
        even.start();
        even.join();
        odd.join();

    }
}
class Printer{


    private int number = 1;
    private final int LIMIT = 10;


    public synchronized void printOdd() throws InterruptedException {

        while(number <= LIMIT){

            //// wait if number is even
            while (number % 2 == 0){
                wait();
            }
            if(number <= LIMIT){
                System.out.println(Thread.currentThread().getName() + " " + number);
            }
            number++;
            notify();
        }


    }

    public synchronized  void printEven(){

        while (number <= LIMIT){
            //wait if number is odd
            while (number % 2 != 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(number <= LIMIT){
                System.out.println(Thread.currentThread().getName() +  " " +number);
            }
            number++;
            notify();
        }

    }
}