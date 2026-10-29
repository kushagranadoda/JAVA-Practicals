import java.util.List;

public class FormDemo {
    public static void main(String[] args) throws Exception {
        SignupForm bad = new SignupForm("", "a@b.com", "verylongpassword");
        SignupForm ok = new SignupForm("Riya", "r@b.com", "pass123");

        System.out.println("Bad form:");
        List<String> e1 = Checker.check(bad);
        for (String s : e1) {
            System.out.println("  " + s);
        }

        System.out.println("Good form:");
        List<String> e2 = Checker.check(ok);
        if (e2.isEmpty()) {
            System.out.println("  No errors");
        }
    }
}