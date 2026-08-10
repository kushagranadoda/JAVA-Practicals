public class PasswordCheckerDemo {
    public static void main(String[] args){
        String[] pw = {"abc", "abcdefgh", "Abcdefgh", "Abcdefg1", "Abcd1234!"};

        for (String s : pw){
            System.out.println("Password: " + s);
            System.out.println("Length >= 8: " + PasswordChecker.len(s));
            System.out.println("Has uppercase: " + PasswordChecker.upper(s));
            System.out.println("Has digit: " + PasswordChecker.digit(s));
            System.out.println("Has special char: " + PasswordChecker.special(s));
            System.out.println("Strength: " + PasswordChecker.strength(s));
            System.out.println();
        }
    }
}