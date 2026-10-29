package model;

import java.io.Serializable;
public class SavingsAccount extends Account implements Premium, Serializable {

    private long minBalance;

    public SavingsAccount(String owner, long balance, long minBalance) {
        super(owner, balance);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    protected boolean canWithdraw(long amount) {
        return getBalance() - amount >= minBalance;
    }

    public long getMinBalance() {
        return minBalance;
    }
}
