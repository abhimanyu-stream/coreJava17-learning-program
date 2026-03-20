package com.java17.interview.prepartion;

public class RecordsAndSealed {
    public static void main(String[] args) {
        UserRecord u = new UserRecord(1, "Abhimanyu");
        System.out.println(u.name());   // Abhimanyu



        Success r = new Success("OK");

        Success s = r;
        System.out.println(s.data());


    }
    static void process(Paymentz payment) {
        switch (payment) {
            case CreditCardPaymens c -> System.out.println("Card");
            case UpiPayments u -> System.out.println("UPI");

            case CashPayments c -> System.out.println("Cash");
           // case VoucherPayment c -> System.out.println("v"); if missing then error
            case VoucherPayment c -> System.out.println("v");



        }
    }
}

 record UserRecord(int id, String name) {
    public String display(){
        return "UserRecord :"+ id + "-" + name;
    }
 }
sealed abstract class Paymentz
        permits CashPayments, CreditCardPaymens, UpiPayments, VoucherPayment {
}

 final class CreditCardPaymens extends Paymentz {
}

 final class UpiPayments extends Paymentz {
}

 final class CashPayments extends Paymentz {
}
// error compile time class BitcoinPayment extends Payment {}


 non-sealed class VoucherPayment extends Paymentz {
}
class NewPays extends  VoucherPayment{
    //ok
}
 sealed interface Result
        permits Success, Failure {
}

 record Success(String data) implements Result {}
 record Failure(String error) implements Result {}



/**
 *
 * class User {
 *     private final int id;
 *     private final String name;
 *
 *     public User(int id, String name) {
 *         this.id = id;
 *         this.name = name;
 *     }
 *
 *     public int getId() { return id; }
 *     public String getName() { return name; }
 *
 *     @Override
 *     public boolean equals(Object o) {

    @Override
 *public int hashCode()
        *
        *@Override
 *public String toString()

 */