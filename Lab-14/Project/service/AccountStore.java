
package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Account;

public class AccountStore {

    private final ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();

    private final List<String> transactionHistory =
            new CopyOnWriteArrayList<>();

    // Add an account
    public void add(Account account) {
        if (account == null) {
            throw new IllegalArgumentException(
                "Account cannot be null."
            );
        }

        accounts.put(account.getAccountNumber(), account);
    }

    // Find an account
    public Account get(String id) {
        return accounts.get(id);
    }

    // Return all accounts
    public ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    // Add a transaction record
    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    // Get transaction history
    public ArrayList<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    // Return accounts sorted by balance
    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> result = getAll();

        result.sort(
            Comparator.comparingLong(Account::getBalance)
        );

        return result;
    }

    // Delete an account
    public boolean delete(String id) {
        return accounts.remove(id) != null;
    }

    // Print all accounts
    public void showAccounts() {
        for (Account account : sortedByBalance()) {
            System.out.println(account.print());
        }
    }
}
