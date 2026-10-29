
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import model.Account;
import service.BankService;
import service.TransactionProcessor;
import persistence.AccountPersistence;
import util.StatementReport;
import exception.BankException;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final BankService bankService =
            new BankService();

    private static final TransactionProcessor processor =
            new TransactionProcessor();

    public static void main(String[] args) {

        loadAccounts();

        boolean running = true;

        while (running) {
            showMenu();

            System.out.print("Enter your choice: ");
            int choice = readInt();

            try {
                switch (choice) {

                    case 1:
                        openAccount();
                        break;

                    case 2:
                        deposit();
                        break;

                    case 3:
                        withdraw();
                        break;

                    case 4:
                        transfer();
                        break;

                    case 5:
                        showStatement();
                        break;

                    case 6:
                        listAccounts();
                        break;

                    case 7:
                        processBatch();
                        break;

                    case 8:
                        showReport();
                        break;

                    case 9:
                        saveAccounts();
                        running = false;
                        System.out.println(
                            "Thank you for using MiniBank!"
                        );
                        break;

                    default:
                        System.out.println(
                            "Invalid choice. Try again."
                        );
                }

            } catch (Exception e) {
                System.out.println(
                    "Operation failed: " + e.getMessage()
                );
            }
        }

        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n========== MINIBANK ==========");
        System.out.println("1. Open Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Account Statement");
        System.out.println("6. List All Accounts");
        System.out.println("7. Process Batch Transactions");
        System.out.println("8. Generate Report");
        System.out.println("9. Save and Exit");
        System.out.println("==============================");
    }

    private static void openAccount() throws Exception {

        System.out.println(
            "Account types: savings, current, fixed"
        );

        System.out.print("Enter account type: ");
        String type = sc.nextLine().trim();

        System.out.print("Enter owner name: ");
        String owner = sc.nextLine().trim();

        System.out.print("Enter opening balance: ");
        long balance = readLong();

        Account account = bankService.openAccount(
            type, owner, balance
        );

        processor.addAccount(account);

        System.out.println("Account opened successfully!");
        System.out.println(account.print());
    }

    private static void deposit() throws Exception {

        System.out.print("Enter account ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter deposit amount: ");
        long amount = readLong();

        Account account = bankService.deposit(id, amount);

        System.out.println("Deposit successful!");
        System.out.println("New balance: "
                + account.getBalance());
    }

    private static void withdraw() throws Exception {

        System.out.print("Enter account ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter withdrawal amount: ");
        long amount = readLong();

        Account account = bankService.withdraw(id, amount);

        System.out.println("Withdrawal successful!");
        System.out.println("New balance: "
                + account.getBalance());
    }

    private static void transfer() throws BankException {

        System.out.print("Enter source account ID: ");
        String fromId = sc.nextLine().trim();

        System.out.print("Enter destination account ID: ");
        String toId = sc.nextLine().trim();

        System.out.print("Enter transfer amount: ");
        long amount = readLong();

        bankService.transfer(fromId, toId, amount);
    }

    private static void showStatement() throws Exception {

        System.out.print("Enter account ID: ");
        String id = sc.nextLine().trim();

        System.out.println(bankService.statement(id));
    }

    private static void listAccounts() {

        ArrayList<Account> accounts =
                bankService.listAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("\n===== ALL ACCOUNTS =====");

        for (Account account : accounts) {
            System.out.println(account.print());
        }
    }

    private static void processBatch() {

        ArrayList<Account> accounts =
                bankService.listAccounts();

        // Register the current accounts in the processor.
        for (Account account : accounts) {
            processor.addAccount(account);
        }

        System.out.print("Number of transactions: ");
        int count = readInt();

        if (count <= 0) {
            System.out.println(
                "Enter a positive number of transactions."
            );
            return;
        }

        ArrayList<TransactionProcessor.Transaction> batch =
                new ArrayList<>();

        for (int i = 0; i < count; i++) {

            System.out.println(
                "\nTransaction " + (i + 1)
            );

            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");

            System.out.print("Choose transaction type: ");
            int typeChoice = readInt();

            TransactionProcessor.Type type;

            if (typeChoice == 1) {
                type = TransactionProcessor.Type.DEPOSIT;
            } else if (typeChoice == 2) {
                type = TransactionProcessor.Type.WITHDRAW;
            } else {
                System.out.println(
                    "Invalid type; transaction skipped."
                );
                continue;
            }

            System.out.print("Enter account ID: ");
            String id = sc.nextLine().trim();

            System.out.print("Enter amount: ");
            long amount = readLong();

            batch.add(
                new TransactionProcessor.Transaction(
                    type, id, amount
                )
            );
        }

        ArrayList<String> results =
                processor.processBatch(batch);

        System.out.println("\n===== BATCH RESULTS =====");

        for (String result : results) {
            System.out.println(result);
        }
    }

    private static void showReport() {

        ArrayList<Account> accounts =
                bankService.listAccounts();

        System.out.println(
            StatementReport.generate(accounts)
        );
    }

    private static void loadAccounts() {

        try {
            ArrayList<Account> accounts =
                    AccountPersistence.loadAccounts();

            for (Account account : accounts) {
                bankService.addExistingAccount(account);
                processor.addAccount(account);
            }

            System.out.println(
                accounts.size() + " account(s) loaded."
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                "Could not load accounts: " + e.getMessage()
            );
        }
    }

    private static void saveAccounts() {

        try {
            AccountPersistence.saveAccounts(
                bankService.listAccounts()
            );

            System.out.println("Accounts saved successfully.");

        } catch (IOException e) {
            System.out.println(
                "Could not save accounts: " + e.getMessage()
            );
        }
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(
                    sc.nextLine().trim()
                );
                return value;
            } catch (NumberFormatException e) {
                System.out.print(
                    "Please enter a valid integer: "
                );
            }
        }
    }

    private static long readLong() {
        while (true) {
            try {
                return Long.parseLong(
                    sc.nextLine().trim()
                );
            } catch (NumberFormatException e) {
                System.out.print(
                    "Please enter a valid whole number: "
                );
            }
        }
    }
}
