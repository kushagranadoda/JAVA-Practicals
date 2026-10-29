package service;

import java.util.Scanner;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
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

        Account[] arr = {
            new SavingsAccount("Alice", 1000, 200),
            new CurrentAccount("Bob", 0, 500),
            new FixedDepositAccount("Charlie", 500)
        };

        arr[0].deposit(200);
        arr[1].deposit(300);
        arr[2].withdraw(100);

        // Test custom annotations using reflection
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
        double total = 0;

        for (Account a : arr) {
            double rate = a.interestRate();
            total += rate;

            printLine(a.print()
                    + ", Interest rate: " + rate + "%");

            printLine("Yearly interest: Rs. "
                    + a.yearlyInterest());

            if (a instanceof SavingsAccount sa) {
                printLine("Min balance rule: " + sa.minBalance);
            }
        }

        printLine("Sum of interest rates: " + total);

        printLine("Same account? " + arr[0].isSame(arr[1]));

        // Anonymous class
        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return amount > 0
                        && amount <= account.getBalance();
            }
        };

        printLine("\nAnonymous class: "
                + rule1.allow(arr[0], 100));

        // Lambda expression
        WithdrawRule rule2 = (account, amount) ->
                amount > 0
                && amount <= account.getBalance()
                && amount <= 500;

        printLine("Lambda expression: "
                + rule2.allow(arr[0], 600));

        // Test validator
        printLine("Valid mobile: "
                + Validator.isValidMobile("9876543210"));

        // Test command parser
        var cmd = CommandParser.parse("DEPOSIT AC0001 500");

        printLine("Command type: " + cmd.type());
        printLine("Account number: " + cmd.accountNumber());
        printLine("Amount: " + cmd.amount());

        // Menu
        boolean running = true;

        while (running) {
            printLine("\n1. Open Account");
            printLine("2. Deposit");
            printLine("3. Withdraw");
            printLine("4. Transfer");
            printLine("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                printLine("Invalid choice. Try again.");
                continue;
            }

            MenuOption option = MenuOption.values()[choice - 1];

            String message = switch (option) {
                case OPEN_ACCOUNT ->
                        "Open Account — to be implemented in a later lab";
                case DEPOSIT ->
                        "Deposit — to be implemented in a later lab";
                case WITHDRAW ->
                        "Withdraw — to be implemented in a later lab";
                case TRANSFER ->
                        "Transfer — to be implemented in a later lab";
                case EXIT -> "Goodbye!";
            };

            printLine(message);

            if (option == MenuOption.EXIT) {
                running = false;
            }
        }

        sc.close();
    }
}
