
package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Account;

public class StatementReport {

    public static String generate(List<Account> accounts) {
        StringBuilder report = new StringBuilder();

        report.append("\n========== MINIBANK REPORT ==========\n");

        if (accounts == null || accounts.isEmpty()) {
            report.append("No accounts available.\n");
            report.append("=====================================\n");
            return report.toString();
        }

        ArrayList<Account> sortedAccounts =
                new ArrayList<>(accounts);

        sortedAccounts.sort(
            Comparator.comparing(Account::getAccountNumber)
        );

        long totalBalance = 0;

        for (Account account : sortedAccounts) {
            report.append("Account ID: ")
                  .append(account.getAccountNumber())
                  .append("\n");

            report.append("Owner: ")
                  .append(account.getOwnerName())
                  .append("\n");

            report.append("Balance: ")
                  .append(account.getBalance())
                  .append("\n");

            report.append("Active: ")
                  .append(account.isActive())
                  .append("\n");

            report.append("-------------------------------------\n");

            totalBalance += account.getBalance();
        }

        report.append("Total accounts: ")
              .append(sortedAccounts.size())
              .append("\n");

        report.append("Total balance: ")
              .append(totalBalance)
              .append("\n");

        report.append("=====================================\n");

        return report.toString();
    }
}
