
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import model.Account;
import exception.BankException;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class TransactionProcessor {

    private final ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();

    // Add an account
    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException(
                "Account cannot be null."
            );
        }

        accounts.put(account.getAccountNumber(), account);
    }

    // Get an account
    public Account getAccount(String id) {
        return accounts.get(id);
    }

    // Remove an account
    public boolean removeAccount(String id) {
        return accounts.remove(id) != null;
    }

    // Display accounts
    public void showAccounts() {
        for (Account account : accounts.values()) {
            System.out.println(account.print());
        }
    }

    // Supported batch transaction types
    public enum Type {
        DEPOSIT,
        WITHDRAW
    }

    // Represents one transaction
    public static class Transaction {

        private final Type type;
        private final String accountId;
        private final long amount;

        public Transaction(
                Type type, String accountId, long amount) {
            this.type = type;
            this.accountId = accountId;
            this.amount = amount;
        }

        public Type getType() {
            return type;
        }

        public String getAccountId() {
            return accountId;
        }

        public long getAmount() {
            return amount;
        }
    }

    // Process a batch of transactions safely
    public synchronized ArrayList<String> processBatch(
            List<Transaction> transactions) {

        ArrayList<String> results = new ArrayList<>();

        for (Transaction transaction : transactions) {
            try {
                Account account =
                    accounts.get(transaction.getAccountId());

                if (account == null) {
                    throw new AccountNotFoundException(
                        "Account not found: "
                        + transaction.getAccountId()
                    );
                }

                switch (transaction.getType()) {
                    case DEPOSIT:
                        account.deposit(transaction.getAmount());
                        results.add(
                            "Deposit successful: "
                            + transaction.getAccountId()
                        );
                        break;

                    case WITHDRAW:
                        account.withdraw(transaction.getAmount());
                        results.add(
                            "Withdrawal successful: "
                            + transaction.getAccountId()
                        );
                        break;
                }

            } catch (BankException e) {
                results.add(
                    "Transaction failed: " + e.getMessage()
                );
            }
        }

        return results;
    }
}
