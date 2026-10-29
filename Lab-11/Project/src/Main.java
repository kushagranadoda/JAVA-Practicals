
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import service.StatePersister;
import service.TransactionLog;
import service.ReportGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        try {
            // Create file paths
            Path dataFile = Path.of("data", "accounts.dat");
            Path logsFolder = Path.of("logs");
            Path logFile = logsFolder.resolve("transactions.log");
            Path reportFile = Path.of(
                    "reports", "end-of-day-report.txt");

            // Create required folders
            Files.createDirectories(dataFile.getParent());
            Files.createDirectories(logsFolder);

            // 1. Create accounts
            Account[] accounts = {
                new SavingsAccount("Alice", 10000, 0),
                new CurrentAccount("Bob", 5000, 1000),
                new FixedDepositAccount("Charlie", 20000)
            };

            System.out.println("===== ORIGINAL ACCOUNTS =====");

            for (Account account : accounts) {
                System.out.println(account.print());
            }

            // 2. Save accounts to a file
            System.out.println("\n===== SAVING ACCOUNTS =====");

            StatePersister.save(accounts, dataFile);

            // 3. Load accounts into a new array
            System.out.println("\n===== LOADING ACCOUNTS =====");

            Account[] loadedAccounts =
                    StatePersister.load(dataFile);

            for (Account account : loadedAccounts) {
                System.out.println(account.print());
            }

            // 4. Perform transactions and log them
            System.out.println("\n===== TRANSACTION LOG =====");

            loadedAccounts[0].deposit(1000);

            TransactionLog.append(
                    logFile, "DEPOSIT,AC0001,1000");

            loadedAccounts[0].withdraw(500);

            TransactionLog.append(
                    logFile, "WITHDRAW,AC0001,500");

            loadedAccounts[1].deposit(2000);

            TransactionLog.append(
                    logFile, "DEPOSIT,AC0002,2000");

            System.out.println("Transactions appended successfully.");

            // 5. Generate the end-of-day report
            System.out.println("\n===== GENERATING REPORT =====");

            ReportGenerator.generate(logsFolder, reportFile);

        } catch (IOException e) {
            System.out.println("File operation failed: "
                    + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load account class: "
                    + e.getMessage());

        } catch (Exception e) {
            System.out.println("Transaction failed: "
                    + e.getMessage());
        }
    }
}
