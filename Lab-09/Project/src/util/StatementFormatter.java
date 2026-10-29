package util;

import model.Account;

public class StatementFormatter {

    public static String buildStatement(Account acc) {
        StringBuilder sb = new StringBuilder();

        sb.append("---- Account Statement ----\n");
        sb.append("Account Number: ")
                .append(acc.getAccountNumber()).append("\n");
        sb.append("Owner: ")
                .append(acc.getOwnerName()).append("\n");
        sb.append("Balance: Rs. ")
                .append(acc.getBalance()).append("\n");
        sb.append("----------------------------\n");

        return sb.toString();
    }
}
