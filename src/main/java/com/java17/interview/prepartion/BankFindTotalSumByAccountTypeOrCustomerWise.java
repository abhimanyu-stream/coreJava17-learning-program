package com.java17.interview.prepartion;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class BankFindTotalSumByAccountTypeOrCustomerWise {

    public static void main(String[] args) {

        List<Customer> customers = Arrays.asList(
                new Customer("Alice", Arrays.asList(
                        new Account(AccountType.SAVINGS, new BigDecimal("1500.00")),
                        new Account(AccountType.CHECKING, new BigDecimal("250.75"))
                )),
                new Customer("Bob", Arrays.asList(
                        new Account(AccountType.SAVINGS, new BigDecimal("2300.50")),
                        new Account(AccountType.LOAN, new BigDecimal("5000.00"))
                )),
                new Customer("Carol", Arrays.asList(
                        new Account(AccountType.CHECKING, new BigDecimal("125.25")),
                        new Account(AccountType.LOAN, new BigDecimal("750.00"))
                ))
        );


        // -----------------------------------------
        // Group by AccountType & sum of balances
        // -----------------------------------------

        Map<AccountType, BigDecimal> totalAmountByAccountTypeInThisBank =
                customers.stream()
                        .flatMap(c -> c.getAccounts().stream())   // flatten all accounts
                        .collect(Collectors.groupingBy(
                                Account::getAccountType,
                                Collectors.reducing(
                                        BigDecimal.ZERO,
                                        Account::getBalance,
                                        BigDecimal::add
                                )
                        ));

        System.out.println("Map<AccountType, BigDecimal> totalAmountByAccountTypeInThisBank:" +totalAmountByAccountTypeInThisBank);

        // Print Output
        totalAmountByAccountTypeInThisBank.forEach((type, total) ->
                System.out.println(type + " : " + total));



        Map<String, BigDecimal> customerNameWiseTotalBalanceInItsAccount =
                customers.stream()
                        .collect(Collectors.toMap(
                                Customer::getName,
                                c -> c.getAccounts().stream()
                                        .map(Account::getBalance)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        ));

        System.out.println(" Map<String, BigDecimal> customerNameWiseTotalBalanceInItsAccount :"+customerNameWiseTotalBalanceInItsAccount);

        customerNameWiseTotalBalanceInItsAccount.forEach((name, total) ->
                System.out.println(name + " : " + total));


        Map<Customer, BigDecimal> totals =
                customers.stream()
                        .collect(Collectors.toMap(
                                c -> c,
                                c -> c.getAccounts().stream()
                                        .map(Account::getBalance)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        ));


        System.out.println("Map<Customer, BigDecimal> totals :"+ totals);
        totals.forEach((customer, amount) ->
                System.out.println(customer.getName() + " ::: " + amount)
        );



    }
}


// --------------------------------------------------------
// Supporting classes
// --------------------------------------------------------

enum AccountType {
    SAVINGS,
    CHECKING,
    LOAN
}

class Account {
    private AccountType accountType;
    private BigDecimal balance;

    public Account(AccountType accountType, BigDecimal balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountType=" + accountType +
                ", balance=" + balance +
                '}';
    }
}

class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name, List<Account> accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
