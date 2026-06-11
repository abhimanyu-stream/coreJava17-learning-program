package com.java17.interview.prepartion;

import java.util.*;

// ============================================================
//  PAYMENT TRANSACTION MONITORING SYSTEM – Karat Interview Style
//
//  Karat Pattern Covered:
//    Task 1 : addAccount(), addTransaction()  → validate entity exists
//    Task 2 : getAverageTransactionAmountByAccount() → sumMap + countMap
//    Task 3a: getTransactionFees()            → sort + frequency + fee rule
//    Task 3b: getTotalDebitPerAccount()       → filter + getOrDefault
//    Task 3c: getTopSpenderAccount()          → find max from debit map
//    Task 3d: getFlaggedAccounts()            → anomaly detection (>N txns)
// ============================================================

public class PaymentTransaction {

    // ── Core data stores ────────────────────────────────────
    private final List<BankAccount>  accounts     = new ArrayList<>();
    private final List<BankTxn>      transactions = new ArrayList<>();

    // ── Task 1: addAccount() ────────────────────────────────
    /**
     * Registers a new bank account.
     */
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    // ── Task 1: addTransaction() ────────────────────────────
    /**
     * Adds a transaction only if the accountId exists.
     * Returns false when accountId is not found (validation pattern).
     */
    public boolean addTransaction(BankTxn tx) {
        boolean accountExists = false;
        for (BankAccount account : accounts) {
            if (account.getAccountId() == tx.getAccountId()) {
                accountExists = true;
                break;
            }
        }
        if (!accountExists) {
            return false; // reject: account not found
        }
        transactions.add(tx);
        return true;
    }

    // ── Task 2: getAverageTransactionAmountByAccount() ──────
    /**
     * Returns accountId -> average transaction amount (absolute value).
     *
     * Pattern: two-map approach
     *   totalMap  : accountId -> sum of |amounts|
     *   countMap  : accountId -> number of transactions
     *   result    : totalMap[id] / countMap[id]
     *
     * Example:
     *   Account 1: [100, 50, 70] → avg = 220/3 = 73.33
     *   Account 2: [200]         → avg = 200/1 = 200.0
     */
    public Map<Integer, Double> getAverageTransactionAmountByAccount() {
        Map<Integer, Double>  totalMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        for (BankTxn tx : transactions) {
            // accumulate absolute amount
            totalMap.put(
                    tx.getAccountId(),
                    totalMap.getOrDefault(tx.getAccountId(), 0.0) + Math.abs(tx.getAmount())
            );
            // accumulate count
            countMap.put(
                    tx.getAccountId(),
                    countMap.getOrDefault(tx.getAccountId(), 0) + 1
            );
        }

        // Divide total by count for each account
        Map<Integer, Double> result = new HashMap<>();
        for (Integer accountId : totalMap.keySet()) {
            result.put(
                    accountId,
                    totalMap.get(accountId) / countMap.get(accountId)
            );
        }
        return result;
    }

    // ── Task 3a: getTransactionFees() ───────────────────────
    /**
     * Fee rule:
     *   - First 3 transactions per account: FREE
     *   - 4th+ CREDIT transaction: $1.0 fee
     *   - 4th+ DEBIT  transaction: $2.0 fee
     *
     * Pattern:
     *   1. Sort transactions by timestamp (chronological)
     *   2. Track txCount per account with HashMap
     *   3. Apply fee only when count > 3
     *
     * Example:
     *   Account 1 makes 4 transactions (txns 1-3 free, txn 4 = DEBIT → $2.0 fee)
     *   Result: {1=2.0}
     */
    public Map<Integer, Double> getTransactionFees() {
        Map<Integer, Double> fees = new HashMap<>();

        // Step 1: sort by timestamp to process in order
        List<BankTxn> sorted = new ArrayList<>(transactions);
        sorted.sort(Comparator.comparingLong(BankTxn::getTimestampSec));

        // Step 2: track how many transactions each account has made so far
        Map<Integer, Integer> txCount = new HashMap<>();

        for (BankTxn tx : sorted) {
            int count = txCount.getOrDefault(tx.getAccountId(), 0) + 1;
            txCount.put(tx.getAccountId(), count);

            if (count <= 3) {
                continue; // first 3 are free → skip fee
            }

            // 4th and beyond → compute fee by type
            double fee = (tx.getTxnType() == TxnType.CREDIT) ? 1.0 : 2.0;
            fees.put(
                    tx.getAccountId(),
                    fees.getOrDefault(tx.getAccountId(), 0.0) + fee
            );
        }
        return fees;
    }

    // ── Task 3b: getTotalDebitPerAccount() ──────────────────
    /**
     * Returns accountId -> total amount spent (DEBIT transactions only).
     *
     * Pattern: filter by txnType, then getOrDefault accumulation.
     *
     * Example:
     *   Account 1 debits: [50, 30] → total = 80.0
     *   Account 2 debits: [200]    → total = 200.0
     */
    public Map<Integer, Double> getTotalDebitPerAccount() {
        Map<Integer, Double> debitMap = new HashMap<>();

        for (BankTxn tx : transactions) {
            if (tx.getTxnType() == TxnType.DEBIT) {
                debitMap.put(
                        tx.getAccountId(),
                        debitMap.getOrDefault(tx.getAccountId(), 0.0) + tx.getAmount()
                );
            }
        }
        return debitMap;
    }

    // ── Task 3c: getTopSpenderAccount() ─────────────────────
    /**
     * Returns the accountId that has the highest total debit spending.
     *
     * Pattern: getTotalDebitPerAccount() → find max value entry.
     *
     * Example:
     *   {1=80.0, 2=200.0} → top spender = 2
     */
    public int getTopSpenderAccount() {
        Map<Integer, Double> debitMap = getTotalDebitPerAccount();

        int    topAccount = -1;
        double maxSpent   = -1;
        for (Map.Entry<Integer, Double> entry : debitMap.entrySet()) {
            if (entry.getValue() > maxSpent) {
                maxSpent   = entry.getValue();
                topAccount = entry.getKey();
            }
        }
        return topAccount;
    }

    // ── Task 3d: getFlaggedAccounts() ───────────────────────
    /**
     * Flags accounts that exceed a transaction threshold (anomaly detection).
     * Returns list of accountIds with more than `threshold` transactions.
     *
     * Pattern: frequency counting → filter by threshold.
     *
     * Example (threshold=3):
     *   Account 1: 4 transactions → flagged
     *   Account 2: 1 transaction  → not flagged
     */
    public List<Integer> getFlaggedAccounts(int threshold) {
        Map<Integer, Integer> txCount = new HashMap<>();

        for (BankTxn tx : transactions) {
            txCount.put(
                    tx.getAccountId(),
                    txCount.getOrDefault(tx.getAccountId(), 0) + 1
            );
        }

        List<Integer> flagged = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : txCount.entrySet()) {
            if (entry.getValue() > threshold) {
                flagged.add(entry.getKey());
            }
        }
        return flagged;
    }

    // ── Demo main() ─────────────────────────────────────────
    public static void main(String[] args) {

        PaymentTransaction ps = new PaymentTransaction();

        // Register accounts
        ps.addAccount(new BankAccount(1, "Alice"));
        ps.addAccount(new BankAccount(2, "Bob"));

        // Add transactions (Task 1)
        //                     txId  accountId  amount   type           timestamp
        ps.addTransaction(new BankTxn(1,  1,  100.0, TxnType.CREDIT,  1000L));
        ps.addTransaction(new BankTxn(2,  1,   50.0, TxnType.DEBIT,   2000L));
        ps.addTransaction(new BankTxn(3,  1,   70.0, TxnType.CREDIT,  3000L));
        ps.addTransaction(new BankTxn(4,  1,   30.0, TxnType.DEBIT,   4000L)); // 4th → $2 fee
        ps.addTransaction(new BankTxn(5,  2,  200.0, TxnType.DEBIT,   5000L));

        // Invalid: account 99 doesn't exist
        boolean added = ps.addTransaction(new BankTxn(6, 99, 500.0, TxnType.CREDIT, 6000L));
        System.out.println("Add txn for invalid account: " + added); // false

        // Task 2: Average amount per account
        System.out.println("\nAvg Txn Amount/Account : " + ps.getAverageTransactionAmountByAccount());
        // Account 1: (100+50+70+30)/4 = 62.5,  Account 2: 200/1 = 200.0

        // Task 3a: Transaction fees
        System.out.println("Transaction Fees       : " + ps.getTransactionFees());
        // Account 1's 4th txn is DEBIT → $2.0 fee  → {1=2.0}

        // Task 3b: Total debit per account
        System.out.println("Total Debit/Account    : " + ps.getTotalDebitPerAccount());
        // Account 1 debits: 50+30=80,  Account 2: 200  → {1=80.0, 2=200.0}

        // Task 3c: Top spender
        System.out.println("Top Spender Account    : " + ps.getTopSpenderAccount());
        // Account 2 spent 200 → 2

        // Task 3d: Flagged accounts (threshold = 3)
        System.out.println("Flagged Accounts (>3)  : " + ps.getFlaggedAccounts(3));
        // Account 1 has 4 txns → [1]
    }
}

// ═══════════════════════════════════════════════════════════
//  DOMAIN CLASSES
// ═══════════════════════════════════════════════════════════

/** Transaction type enum. */
enum TxnType { CREDIT, DEBIT }

/** Represents a bank account. */
class BankAccount {
    int    accountId;
    String ownerName;

    public BankAccount(int accountId, String ownerName) {
        this.accountId = accountId;
        this.ownerName = ownerName;
    }

    public int    getAccountId()  { return accountId;  }
    public String getOwnerName()  { return ownerName;  }

    @Override
    public String toString() {
        return "BankAccount{id=" + accountId + ", owner=" + ownerName + "}";
    }
}

/** Represents a single financial transaction. */
class BankTxn {
    int     txId;
    int     accountId;
    double  amount;
    TxnType txnType;
    long    timestampSec; // epoch seconds – used for chronological sort

    public BankTxn(int txId, int accountId, double amount,
                   TxnType txnType, long timestampSec) {
        this.txId         = txId;
        this.accountId    = accountId;
        this.amount       = amount;
        this.txnType      = txnType;
        this.timestampSec = timestampSec;
    }

    public int     getTxId()         { return txId;         }
    public int     getAccountId()    { return accountId;    }
    public double  getAmount()       { return amount;       }
    public TxnType getTxnType()      { return txnType;      }
    public long    getTimestampSec() { return timestampSec; }

    @Override
    public String toString() {
        return "BankTxn{id=" + txId + ", account=" + accountId +
               ", amount=" + amount + ", type=" + txnType + "}";
    }
}
