
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import repository.Repository;

public class Main {
    public static void main(String[] args) {

        // Create a generic repository for Account objects
        Repository<Account> repo = new Repository<>();

        // Create accounts
        Account a1 = new SavingsAccount("Alice", 10000, 0);
        Account a2 = new CurrentAccount("Bob", 5000, 1000);
        Account a3 = new FixedDepositAccount("Charlie", 20000);

        // Save accounts individually
        System.out.println("===== SAVING ACCOUNTS =====");

        repo.save(a1);
        repo.save(a2);
        repo.save(a3);

        System.out.println("All accounts saved successfully.");

        // Find one account using its ID
        System.out.println("\n===== FIND ACCOUNT BY ID =====");

        Account found = repo.findById(a1.getAccountNumber());

        if (found != null) {
            System.out.println(found.print());
        } else {
            System.out.println("Account not found.");
        }

        // Display all accounts
        System.out.println("\n===== ALL ACCOUNTS =====");

        for (Account account : repo.findAll()) {
            System.out.println(account.print());
        }

        // Demonstrate saveAll()
        System.out.println("\n===== SAVE ALL =====");

        Account a4 = new SavingsAccount("David", 15000, 0);
        Account a5 = new CurrentAccount("Emma", 8000, 2000);

        repo.saveAll(a4, a5);

        System.out.println("Accounts saved using saveAll().");

        for (Account account : repo.findAll()) {
            System.out.println(account.print());
        }

        // Demonstrate delete()
        System.out.println("\n===== DELETE ACCOUNT =====");

        boolean deleted = repo.delete(a2.getAccountNumber());

        if (deleted) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }

        // Demonstrate bounded generic average()
        System.out.println("\n===== GENERIC AVERAGE =====");

        Integer[] numbers = {10, 20, 30, 40, 50};

        double result = Repository.average(numbers);

        System.out.println("Average = " + result);
    }
}
