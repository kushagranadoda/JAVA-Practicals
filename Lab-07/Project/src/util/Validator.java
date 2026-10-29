package util;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern MOBILE =
            Pattern.compile("^[6-9][0-9]{9}$");

    private static final Pattern EMAIL =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PAN =
            Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");

    private static final Pattern IFSC =
            Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    private static final Pattern AMOUNT =
            Pattern.compile("^[1-9][0-9]*$");

    public static boolean isValidMobile(String s) {
        return MOBILE.matcher(s).matches();
    }

    public static boolean isValidEmail(String s) {
        return EMAIL.matcher(s).matches();
    }

    public static boolean isValidPan(String s) {
        return PAN.matcher(s).matches();
    }

    public static boolean isValidIfsc(String s) {
        return IFSC.matcher(s).matches();
    }

    public static boolean isValidAmount(String s) {
        return AMOUNT.matcher(s).matches();
    }
}
