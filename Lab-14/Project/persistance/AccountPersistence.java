
package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;

import model.Account;

public class AccountPersistence {

    private static final String FILE_PATH = "data/accounts.dat";

    // Save accounts to a file
    public static void saveAccounts(
            ArrayList<Account> accounts) throws IOException {

        File file = new File(FILE_PATH);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()
                && !parent.mkdirs()) {
            throw new IOException(
                "Could not create data directory."
            );
        }

        try (ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream(file))) {

            output.writeObject(accounts);
        }
    }

    // Load accounts from a file
    @SuppressWarnings("unchecked")
    public static ArrayList<Account> loadAccounts()
            throws IOException, ClassNotFoundException {

        File file = new File(FILE_PATH);

        // First run: no saved data exists yet
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(file))) {

            Object data = input.readObject();

            if (!(data instanceof ArrayList<?>)) {
                throw new IOException(
                    "Invalid account data format."
                );
            }

            ArrayList<?> loaded = (ArrayList<?>) data;

            for (Object item : loaded) {
                if (!(item instanceof Account)) {
                    throw new IOException(
                        "Saved data contains an invalid account."
                    );
                }
            }

            return (ArrayList<Account>) loaded;

        } catch (InvalidClassException e) {
            throw new IOException(
                "Saved data is incompatible with the current classes.",
                e
            );
        }
    }
}
