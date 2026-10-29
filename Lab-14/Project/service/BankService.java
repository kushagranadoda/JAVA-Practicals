
package service;

import java.util.ArrayList;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import repository.Repository;

import exception.BankException;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class BankService {

    private final Repository<Account> repository;

    public BankService() {
        repository = new Repository<>();
    }

    // Open a new account
    public synchronized Account openAccount(
            String type, String owner, long openingBalance)
            throws InvalidAmountException {

        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Owner name cannot be empty."
            );
        }

        if (openingBalance < 0) {
            throw new InvalidAmountException(
                "Opening balance cannot be negative."
            );
        }

        Account account;

        switch (type.toLowerCase()) {
            case "savings":
                account = new SavingsAccount(
                    owner, openingBalance, 0
                );
                break;

            case "current":
                account = new CurrentAccount(
                    owner, openingBalance, 1000
                );
                break;

            case "fixed":
                account = new FixedDepositAccount(
                    owner, openingBalance
                );
                break;

            default:
                throw new IllegalArgumentException(
                    "Invalid account type."
                );
        }

        repository.save(account);
        return account;
    }

    // Deposit money
    public Account deposit(String id, long amount)
            throws AccountNotFoundException,
                   InvalidAmountException {

        Account account = findAccount(id);
        account.deposit(amount);

        return account;
    }

    // Withdraw money
    public Account withdraw(String id, long amount)
            throws AccountNotFoundException,
                   InvalidAmountException,
                   InsufficientFundsException {

        Account account = findAccount(id);
        account.withdraw(amount);

        return account;
    }

    // Transfer money
    public synchronized void transfer(
            String fromId, String toId, long amount)
            throws BankException {

        Account from = findAccount(fromId);
        Account to = findAccount(toId);

        if (from.isSame(to)) {
            throw new IllegalArgumentException(
                "Source and destination must be different."
            );
        }

        from.transfer(to, amount);
    }

    // Find an account
    public Account findAccount(String id)
            throws AccountNotFoundException {

        Account account = repository.findById(id);

        if (account == null) {
            throw new AccountNotFoundException(
                "Account not found: " + id
            );
        }

        return account;
    }

    // Display one account's statement
    public String statement(String id)
            throws AccountNotFoundException {

        Account account = findAccount(id);

        return "========== ACCOUNT STATEMENT ==========\n"
             + "Account Number: " + account.getAccountNumber() + "\n"
             + "Owner: " + account.getOwnerName() + "\n"
             + "Balance: " + account.getBalance() + "\n"
             + "Active: " + account.isActive() + "\n"
             + "=======================================";
    }

    // List all accounts
    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    // Save an existing account object, useful during loading
    public void addExistingAccount(Account account) {
        repository.save(account);
    }
}
