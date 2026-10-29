
package service;

import model.Account;
import model.SavingsAccount;

public class TransactionProcessorDemo {

    public static void main(String[] args)
            throws InterruptedException {

        Account account =
                new SavingsAccount("Alice", 10000, 0);

        TransactionProcessor processor =
                new TransactionProcessor();

        for (int i = 1; i <= 10; i++) {
            processor.submit(
                    new TransactionTask(account, "deposit", 100)
            );

            processor.submit(
                    new TransactionTask(account, "withdraw", 50)
            );
        }

        processor.stop();

        System.out.println("Final balance: Rs. "
                + account.getBalance());
    }
}
