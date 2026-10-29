package model;

public class CurrentAccount extends Account {

    public long overdraftLimit;

    public CurrentAccount(String owner, long openingBal,
                          long overdraftLimit) {
        super(owner, openingBal);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {
        return 0.0;
    }

    @Override
    protected boolean canWithdraw(long amt) {
        return getBalance() - amt >= -overdraftLimit;
    }
}
