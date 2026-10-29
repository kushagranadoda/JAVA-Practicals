
package service;

import java.util.Scanner;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.Customer;
import model.Command;

import util.Validator;
import util.CommandParser;
import util.StatementFormatter;
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

        double total = 0;

        for (Account a : arr) {
            double rate = a.interestRate();
            total += rate;

            printLine(a.print()
                    + ", Interest rate: " + rate + "%");

            printLine("  Yearly interest: Rs. "
                    + a.yearlyInterest());

            if (a instanceof SavingsAccount sa) {
                printLine("  Min balance rule: "
                        + sa.minBalance);
            }
        }

        printLine("Sum of interest rates: " + total);

        boolean same = arr[0].isSame(arr[1]);
        printLine("Same account? " + same);

        // Marker interface demonstration
        printLine("Alice has Premium status: "
                + (arr[0] instanceof model.Premium));

        // Anonymous class
        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return amount > 0
                        && amount <= account.getBalance();
            }
        };

        printLine("\nAnonymous class:");
        printLine("Can withdraw Rs. 100? "
                + rule1.allow(arr[0], 100));

        // Lambda expression
        WithdrawRule rule2 = (account, amount) -> amount > 0 && amount <= account.getBalance() && amount <= 500;

        printLine("\nLambda expression:");
        printLine("Can withdraw Rs. 600? "
                + rule2.allow(arr[0], 600));

        // Existing validator tests
        printLine("\nisValidMobile(\"9876543210\"): "
                + Validator.isValidMobile("9876543210"));

        printLine("isValidMobile(\"12345\"): "
                + Validator.isValidMobile("12345"));

        printLine("isValidEmail(\"abc@xyz.com\"): "
                + Validator.isValidEmail("abc@xyz.com"));

        printLine("isValidEmail(\"abc@xyz\"): "
                + Validator.isValidEmail("abc@xyz"));

        printLine("isValidPan(\"ABCDE1234F\"): "
                + Validator.isValidPan("ABCDE1234F"));

        printLine("isValidPan(\"ABCDE1234\"): "
                + Validator.isValidPan("ABCDE1234"));

        printLine("isValidIfsc(\"SBIN0001234\"): "
                + Validator.isValidIfsc("SBIN0001234"));

        printLine("isValidIfsc(\"SBIN1234\"): "
                + Validator.isValidIfsc("SBIN1234"));

        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");

        printLine("type=" + cmd.type()
                + ", accountNumber=" + cmd.accountNumber()
                + ", amount=" + cmd.amount());

        printLine("\n"
                + StatementFormatter.buildStatement(arr[0]));

        boolean running = true;

        while (running) {
            printLine("1. Open Account");
            printLine("2. Deposit");
            printLine("3. Withdraw");
            printLine("4. Transfer");
            printLine("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice < 1 || choice > MenuOption.values().length) {
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
