package com.java17.interview.prepartion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCLockCondition {

    private final Lock lock = new ReentrantLock();
    private final Condition a = lock.newCondition();
    private final Condition b = lock.newCondition();
    private final Condition c = lock.newCondition();

    private char turn = 'A';

    public static void main(String[] args) {

        PrintABCLockCondition printer = new PrintABCLockCondition();

        Thread t1 = new Thread(() -> printer.print2('A', printer.a, printer.b, "A"));
        Thread t2 = new Thread(() -> printer.print2('B', printer.b, printer.c, "B"));
        Thread t3 = new Thread(() -> printer.print2('C', printer.c, printer.a,"C"));

        t1.start();
        t2.start();
        t3.start();
    }

    void print(char ch, Condition self, Condition next) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (turn != ch) {
                    self.await();
                }
                System.out.print(ch);
                turn = (ch == 'A') ? 'B' : (ch == 'B') ? 'C' : 'A';
                next.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    void print2(char ch, Condition self, Condition next, String label) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (turn != ch) {
                    self.await();
                }

                // Print with arrow formatting
                if (ch == 'C') {
                    System.out.print(label);
                    System.out.println();
                } else {
                    System.out.print(label + " → ");
                }

                turn = (ch == 'A') ? 'B' : (ch == 'B') ? 'C' : 'A';
                next.signal();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}

