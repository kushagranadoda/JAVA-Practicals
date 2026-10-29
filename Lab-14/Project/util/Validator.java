
package util;

public class Validator {

    private Validator() {
        // Utility class
    }

    public static void validateOwner(String owner) {
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Owner name cannot be empty."
            );
        }

        if (owner.length() > 30) {
            throw new IllegalArgumentException(
                "Owner name cannot exceed 30 characters."
            );
        }
    }

    public static void validateOpeningBalance(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Opening balance cannot be negative."
            );
        }
    }

    public static void validateAmount(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero."
            );
        }
    }

    public static void validateAccountId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Account ID cannot be empty."
            );
        }
    }
}
