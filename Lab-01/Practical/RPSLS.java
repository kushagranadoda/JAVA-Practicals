import java.util.Random;
import java.util.Scanner;

public class RPSLS {
    enum Move {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK;

        Move[] beats() {
            return switch (this) {
                case ROCK     -> new Move[]{SCISSORS, LIZARD};
                case PAPER    -> new Move[]{ROCK, SPOCK};
                case SCISSORS -> new Move[]{PAPER, LIZARD};
                case LIZARD   -> new Move[]{SPOCK, PAPER};
                case SPOCK    -> new Move[]{SCISSORS, ROCK};
            };
        }
    }

    static int winner(Move a, Move b) {
        if (a == b) {
            return 0;
        }
        for (Move m : a.beats()) {
            if (m == b) {
                return 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        Move[] moves = Move.values();

        int playerScore = 0;
        int computerScore = 0;

        for (int round = 1; round <= 5; round++) {
            Move computerMove = moves[rand.nextInt(moves.length)];

            System.out.print("Round " + round + " - Enter your move (ROCK/PAPER/SCISSORS/LIZARD/SPOCK): ");
            Move playerMove = Move.valueOf(sc.next().toUpperCase());

            int result = winner(playerMove, computerMove);

            System.out.println("You: " + playerMove + ", Computer: " + computerMove);

            if (result == 1) {
                System.out.println("You win this round!");
                playerScore++;
            } else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            } else {
                System.out.println("This round is a tie!");
            }
        }

        System.out.println();
        if (playerScore > computerScore) {
            System.out.println("You win " + playerScore + "-" + computerScore);
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins " + computerScore + "-" + playerScore);
        } else {
            System.out.println("It's a tie " + playerScore + "-" + computerScore);
        }
    }
}