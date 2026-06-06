package com.java17.interview.prepartion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserWithMaxTotalTransactionAmountPerDay {

    public static void main(String[] args) {



        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("101", 5000.00, LocalDate.of(2024,4, 17)));
        transactions.add(new Transaction("101", 6000.00, LocalDate.of(2024,4, 17)));
        transactions.add(new Transaction("103", 7000.00, LocalDate.of(2024,4, 17)));
        transactions.add(new Transaction("104", 8000.00, LocalDate.of(2024,4, 17)));




        Map<LocalDate, String> maxUserPerDay =
                transactions.stream()
                        .collect(Collectors.groupingBy(
                                Transaction::getDate,
                                Collectors.collectingAndThen(
                                        Collectors.groupingBy(
                                                Transaction::getUserId,
                                                Collectors.summingDouble(Transaction::getAmount)
                                        ),
                                        userTotalMap ->
                                                userTotalMap.entrySet().stream()
                                                        .max(Map.Entry.comparingByValue())
                                                        .get()
                                                        .getKey()
                                )
                        ));
        System.out.println(maxUserPerDay);


    }
}
class Transaction {
    String userId;
    double amount;
    LocalDate date;


    public Transaction(String userId, double amount, LocalDate date) {
        this.userId = userId;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(userId, that.userId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, amount, date);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
