package model;

public class FixedDepositAccount extends Account {

    public FixedDepositAccount(String owner, long openingBal) {
        super(owner, openingBal);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    protected boolean canWithdraw(long amt) {
        return false;
    }
}
