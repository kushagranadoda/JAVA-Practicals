
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {
    public static void main(String[] args) {

        Map<String, Integer> scores = new HashMap<>();

        scores.put("Riya", 950);
        scores.put("Amit", 850);
        scores.put("Karan", 950);
        scores.put("Neha", 780);
        scores.put("Jay", 850);
        scores.put("Priya", 920);

        int topN = 3;

        List<Map.Entry<String, Integer>> players =
            new ArrayList<>(scores.entrySet());

        players.sort(
            Comparator
                .<Map.Entry<String, Integer>>comparingInt(
                    entry -> entry.getValue()
                )
                .reversed()
                .thenComparing(entry -> entry.getKey())
        );

        System.out.println("LEADERBOARD");
        System.out.println("--------------------");

        int rank = 1;

        for (Map.Entry<String, Integer> player : players) {
            if (rank > topN) {
                break;
            }

            System.out.println(
                rank + ". " + player.getKey()
                + " - " + player.getValue() + " points"
            );

            rank++;
        }
    }
}
