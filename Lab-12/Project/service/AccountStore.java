
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

import model.Account;

public class AccountStore {

    private HashMap<String, Account> accounts = new HashMap<>();
    private ArrayList<String> transactionHistory = new ArrayList<>();

    public void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public ArrayList<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> sortedAccounts = getAll();

        sortedAccounts.sort(
            Comparator.comparingDouble(Account::getBalance)
        );

        return sortedAccounts;
    }
}
