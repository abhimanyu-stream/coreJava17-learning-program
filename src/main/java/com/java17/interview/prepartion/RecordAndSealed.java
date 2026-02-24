package com.java17.interview.prepartion;

public class RecordAndSealed {
    public static void main(String[] args) {
        Employeey e = new Employeey(1, "Alice", 75_000);

        System.out.println(e.name());     // Alice
        System.out.println(e.salary());   // 75000.0

    }


}
 record Employeey(int id, String name, double salary) {
}

 sealed interface Payment
        permits CashPayment, CardPayment, UpiPayment {
}
 record CashPayment(double amount) implements Payment {
}
 record CardPayment(double amount, String cardNumber)
        implements Payment {
}

 record UpiPayment(double amount, String upiId)
        implements Payment {
}
class PaymentProcessor {

    public static String process(Payment payment) {
        return switch (payment) {
            case CashPayment c ->
                    "Cash payment of " + c.amount();
            case CardPayment c ->
                    "Card payment of " + c.amount() +
                            " using card " + c.cardNumber();
            case UpiPayment u ->
                    "UPI payment of " + u.amount() +
                            " via " + u.upiId();
        };
    }
}

