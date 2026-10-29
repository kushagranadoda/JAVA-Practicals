package util;

import model.Command;
import model.TransactionType;

public class CommandParser {

    public static Command parse(String line) {
        String[] p = line.split(" ");

        if (p.length != 3) {
            throw new IllegalArgumentException(
                    "Expected 3 parts, got " + p.length);
        }

        TransactionType type =
                TransactionType.valueOf(p[0].toUpperCase());

        String acc = p[1];
        long amt = Long.parseLong(p[2]);

        return new Command(type, acc, amt);
    }
}
