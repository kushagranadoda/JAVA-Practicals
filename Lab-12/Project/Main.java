import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import service.AccountStore;

public class Main {
    public static void main(String[] args) {

        AccountStore store = new AccountStore();

        Account a1 = new SavingsAccount("Alice", 10000, 0);
        Account a2 = new CurrentAccount("Bob", 5000, 1000);
        Account a3 = new FixedDepositAccount("Charlie", 20000);

        store.add(a1);
        store.add(a2);
        store.add(a3);

        System.out.println("===== ALL ACCOUNTS =====");

        for (Account account : store.getAll()) {
            System.out.println(account.print());
        }

        System.out.println("\n===== ACCOUNTS SORTED BY BALANCE =====");

        for (Account account : store.sortedByBalance()) {
            System.out.println(account.print());
        }

        System.out.println("\n===== SEARCH ACCOUNT =====");

        Account found = store.get(a1.getAccountNumber());

        if (found != null) {
            System.out.println(found.print());
        } else {
            System.out.println("Account not found.");
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        store.addTransaction("Alice deposited 1000");
        store.addTransaction("Bob withdrew 500");

        for (String transaction : store.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}