package service;

import model.Account;
import model.SavingsAccount;

public class Lab09Demo {

    public static void main(String[] args)
            throws InterruptedException {

        Account account =
                new SavingsAccount("Shared Account", 0, 0);

        int numberOfThreads = 10;
        int times = 100000;
        long amount = 1;

        Thread[] threads = new Thread[numberOfThreads];

        System.out.println("Starting balance: "
                + account.getBalance());

        // Create and start threads
        for (int i = 0; i < numberOfThreads; i++) {
            AccountWorker worker =
                    new AccountWorker(account, times, amount);

            threads[i] = new Thread(worker, "Worker-" + (i + 1));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        long expectedBalance =
                (long) numberOfThreads * times * amount;

        System.out.println("\nExpected balance: "
                + expectedBalance);
        System.out.println("Actual balance: "
                + account.getBalance());
    }
}
