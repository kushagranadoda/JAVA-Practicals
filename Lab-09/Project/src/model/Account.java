package model;

import model.annotation.Id;
import model.annotation.Positive;
import model.annotation.MaxLength;

import exception.BankException;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public abstract class Account
        implements Transactable, InterestBearing {

    @Id
    private final String accountNumber;

    @MaxLength(30)
    private String owner;

    @Positive
    private long bal;

    private boolean active;

    private static int counter = 0;

    private static String genAccNum() {
        counter++;
        return String.format("AC%04d", counter);
    }

    public Account(String owner, long openingBal) {
        this.accountNumber = genAccNum();
        this.owner = owner;
        this.bal = openingBal;
        this.active = true;
    }

    public Account(String owner) {
        this(owner, 0);
    }

    @Override
public synchronized void deposit(long amt) throws InvalidAmountException {
    if (amt <= 0) {
        throw new InvalidAmountException(
            "Deposit amount must be greater than zero."
        );
    }

    if (!active) {
        throw new InvalidAmountException(
            "Cannot deposit into an inactive account."
        );
    }

    bal += amt;
}

    @Override
    public synchronized boolean withdraw(long amt)
        throws InsufficientFundsException, InvalidAmountException {

    if (amt <= 0) {
        throw new InvalidAmountException(
            "Withdrawal amount must be greater than zero."
        );
    }

    if (!active) {
        throw new InvalidAmountException(
            "Cannot withdraw from an inactive account."
        );
    }

    if (!canWithdraw(amt)) {
        long shortfall = Math.max(0, amt - bal);

        throw new InsufficientFundsException(shortfall);
    }

    bal -= amt;
    return true;
}

    @Override
    public abstract double interestRate();

    protected abstract boolean canWithdraw(long amt);

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return owner;
    }

    @Override
    public long getBalance() {
        return bal;
    }

    public boolean isActive() {
        return active;
    }

    public String print() {
        return "Account[" + accountNumber + ", " + owner
                + ", Balance: " + bal + "]";
    }

    public boolean isSame(Account other) {
        if (other == null) {
            return false;
        }
        return this.accountNumber.equals(other.accountNumber);
    }

    public void transfer(Account to, long amount)
        throws BankException {

    try {
        if (to == null) {
            throw new AccountNotFoundException(
                    "Destination account not found."
            );
        }

        withdraw(amount);

       to.deposit(amount);

        System.out.println("Transfer successful.");

    } catch (BankException e) {
        System.out.println("Transfer failed: " + e.getMessage());
        throw e;

    } finally {
        System.out.println("Transfer attempt completed.");
    }
}
}
