
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class UniqueVisitors {
    public static void main(String[] args) {

        // Login names with repeated visitors
        List<String> logins = Arrays.asList(
            "Amit", "Riya", "Karan", "Amit",
            "Neha", "Riya", "Jay", "Karan"
        );

        HashSet<String> uniqueUsers = new HashSet<>(logins);

        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>(logins);

        System.out.println("Unique visitors: " + uniqueUsers.size());

        System.out.println("First three unique visitors:");

        int count = 0;

        for (String name : orderedUsers) {
            System.out.println(name);
            count++;

            if (count == 3) {
                break;
            }
        }
    }
}
