
package service;

import java.util.Scanner;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.Command;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import util.Validator;
import util.CommandParser;
import util.AnnotationValidator;
import util.WithdrawRule;

import static util.BankUtils.printLine;

public class MiniBank {

    record BankInfo(String name, String branch) {}

    enum MenuOption {
        OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "Main Branch");
        printLine(bank.toString());

        // Create accounts
        Account[] arr = {
            new SavingsAccount("Alice", 1000, 200),
            new CurrentAccount("Bob", 0, 500),
            new FixedDepositAccount("Charlie", 500)
        };

        // Deposit with exception handling
        try {
            arr[0].deposit(200);
            printLine("Deposit successful.");

        } catch (InvalidAmountException e) {
            printLine("Deposit failed: " + e.getMessage());

        } finally {
            printLine("Deposit operation completed.");
        }

        // Test invalid deposit
        try {
            arr[0].deposit(-100);

        } catch (InvalidAmountException e) {
            printLine("Invalid deposit: " + e.getMessage());
        }

        // Withdrawal with exception handling
        try {
            arr[0].withdraw(5000);
            printLine("Withdrawal successful.");

        } catch (InsufficientFundsException e) {
            printLine("Withdrawal failed: " + e.getMessage());
            printLine("Shortfall: Rs. " + e.getShortfall());

        } catch (InvalidAmountException e) {
            printLine("Invalid withdrawal: " + e.getMessage());

        } finally {
            printLine("Withdrawal operation completed.");
        }

        // Test transfer
        try {
            arr[0].transfer(arr[1], 100);

        } catch (BankException e) {
            printLine("Banking error: " + e.getMessage());
        }

        // Test custom annotations and reflection
        Account invalidAccount =
                new SavingsAccount(
                        "This owner name is much too long",
                        -500,
                        0
                );

        String[] errors =
                AnnotationValidator.validate(invalidAccount);

        printLine("\nAnnotation Validation:");

        if (errors.length == 0) {
            printLine("No validation errors.");
        } else {
            for (String error : errors) {
                printLine(error);
            }
        }

        // Display account information
        printLine("\nAccount Details:");

        for (Account a : arr) {
            printLine(a.print());
            printLine("Interest rate: " + a.interestRate() + "%");
            printLine("Yearly interest: Rs. " + a.yearlyInterest());
        }

        // Test account comparison
        printLine("Are first and second accounts the same? "
                + arr[0].isSame(arr[1]));

        // Anonymous class example
        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return amount > 0
                        && amount <= account.getBalance();
            }
        };

        printLine("\nAnonymous class result: "
                + rule1.allow(arr[0], 100));

        // Lambda expression example
        WithdrawRule rule2 = (account, amount) ->
                amount > 0
                && amount <= account.getBalance();

        printLine("Lambda expression result: "
                + rule2.allow(arr[0], 100));

        // Test Validator
        printLine("Valid mobile number: "
                + Validator.isValidMobile("9876543210"));

        // Test CommandParser
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");

        if (cmd != null) {
            printLine("Command type: " + cmd.type());
            printLine("Account number: " + cmd.accountNumber());
            printLine("Amount: " + cmd.amount());
        }

        // Try-with-resources
        printLine("\nTry-with-resources demonstration:");

        try (BankSession session = new BankSession()) {
            session.performOperation();
        }

        // Menu
        boolean running = true;

        while (running) {
            printLine("\n===== MiniBank Menu =====");
            printLine("1. Open Account");
            printLine("2. Deposit");
            printLine("3. Withdraw");
            printLine("4. Transfer");
            printLine("5. Exit");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                printLine("Please enter a valid number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                printLine("Invalid choice. Try again.");
                continue;
            }

            MenuOption option = MenuOption.values()[choice - 1];

            switch (option) {
                case OPEN_ACCOUNT ->
                    printLine("Open account selected.");

                case DEPOSIT ->
                    printLine("Deposit selected.");

                case WITHDRAW ->
                    printLine("Withdraw selected.");

                case TRANSFER ->
                    printLine("Transfer selected.");

                case EXIT -> {
                    printLine("Thank you for using MiniBank!");
                    running = false;
                }
            }
        }

        sc.close();
    }
}
