package model;

public class SavingsAccount extends Account implements Premium {

    public long minBalance;

    public SavingsAccount(String owner, long openingBal,
                          long minBalance) {
        super(owner, openingBal);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    protected boolean canWithdraw(long amt) {
        return getBalance() - amt >= minBalance;
    }
}
