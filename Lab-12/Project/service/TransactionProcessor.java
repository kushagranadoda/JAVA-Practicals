
package service;

import java.util.concurrent.ConcurrentHashMap;

import model.Account;

public class TransactionProcessor {

    private ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
    }

    public void showAccounts() {
        for (Account account : accounts.values()) {
            System.out.println(account.print());
        }
    }
}
