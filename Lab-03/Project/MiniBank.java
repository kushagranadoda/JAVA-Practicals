import java.util.Scanner;

public class MiniBank {
    record BankInfo(String name, String branch) {}

    enum MenuOption {
        OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankInfo bank = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bank);

        Account[] accounts = new Account[3];
        accounts[0] = new Account("Alice", 1000);
        accounts[1] = new Account("Bob");
        accounts[2] = new Account("Charlie", 500);

        accounts[0].deposit(200);
        accounts[1].deposit(300);
        accounts[2].withdraw(100);
        accounts[2].withdraw(1000); 

        for (Account acc : accounts) {
            System.out.println(acc.getAccountNumber() + " (" + acc.getOwnerName() + "): Rs. " + acc.getBalance());
        }

        // Practical 3 additions: print(), isSame(), instanceof
        System.out.println(accounts[0].print());
        System.out.println(accounts[1].print());

        boolean same = accounts[0].isSame(accounts[1]);
        System.out.println("accounts[0] and accounts[1] are the same account? " + same);

        Object obj = accounts[0];
        if (obj instanceof Account) {
            System.out.println("obj is an Account object");
        }

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            MenuOption option = MenuOption.values()[choice - 1];

            String message = switch (option) {
                case OPEN_ACCOUNT -> "Open Account — to be implemented in a later lab";
                case DEPOSIT -> "Deposit — to be implemented in a later lab";
                case WITHDRAW -> "Withdraw — to be implemented in a later lab";
                case TRANSFER -> "Transfer — to be implemented in a later lab";
                case EXIT -> "Goodbye!";
            };

            System.out.println(message);

            if (option == MenuOption.EXIT) {
                running = false;
            }
        }
    }
}

class Customer {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private Address address;

    private static long customerCounter = 100;

    private static String generateCustomerId() {
        customerCounter++;
        return "CUST" + customerCounter;
    }

    public Customer(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
    }

    private Customer(String customerId, String name, String email, String mobile, Address address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getCustomerId() { return customerId; }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Customer copy() {
        return new Customer(this.customerId, this.name, this.email, this.mobile, this.address);
    }

    public static class Address {
        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }
    }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountCounter = 0;

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public boolean withdraw(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }

    public String print() {
        return "Account[" + accountNumber + ", " + ownerName + ", Balance: " + balance + "]";
    }

    public boolean isSame(Account other) {
        if (other == null) {
            return false;
        }
        return this.accountNumber.equals(other.accountNumber);
    }
}