
package service;

import model.Account;
import exception.BankException;

public class TransactionTask implements Runnable {

    private Account account;
    private String type;
    private long amount;

    public TransactionTask(Account account, String type, long amount) {
        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        try {
            if (type.equalsIgnoreCase("deposit")) {
                account.deposit(amount);
                System.out.println(threadName
                        + " deposited Rs. " + amount);

            } else if (type.equalsIgnoreCase("withdraw")) {
                account.withdraw(amount);
                System.out.println(threadName
                        + " withdrew Rs. " + amount);

            } else {
                System.out.println("Invalid transaction type: " + type);
            }

        } catch (BankException e) {
            System.out.println(threadName
                    + " transaction failed: " + e.getMessage());
        }
    }
}
