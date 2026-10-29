package model;

import model.annotation.Id;
import model.annotation.Positive;
import model.annotation.MaxLength;

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
    public void deposit(long amt) {
        if (active && amt > 0) {
            bal += amt;
        }
    }

    @Override
    public boolean withdraw(long amt) {
        if (active && amt > 0 && canWithdraw(amt)) {
            bal -= amt;
            return true;
        }
        return false;
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
}
