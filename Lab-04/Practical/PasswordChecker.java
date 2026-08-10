public class PasswordChecker {

    public static boolean len(String s){
        return s.length() >= 8;
    }

    public static boolean upper(String s){
        return s.matches(".*[A-Z].*");
    }

    public static boolean digit(String s){
        return s.matches(".*[0-9].*");
    }

    public static boolean special(String s){
        return s.matches(".*[^a-zA-Z0-9].*");
    }

    public static String strength(String s){
        int n = 0;
        if (len(s)) n++;
        if (upper(s)) n++;
        if (digit(s)) n++;
        if (special(s)) n++;

        if (n <= 1){
            return "Weak";
        } else if (n <= 3){
            return "Medium";
        } else{
            return "Strong";
        }
    }
}