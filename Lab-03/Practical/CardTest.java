public class CardTest {
    public static void main(String[] args) {
        Card[] cards = new Card[5];
        int size = 0;

        Card[] newCards = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("Queen", "Hearts"),
            new Card("Ace", "Spades"),
            new Card("Jack", "Clubs")
        };

        for (Card c : newCards) {
            boolean dup = false;
            for (int i = 0; i < size; i++) {
                if (cards[i].isSame(c)) {
                    dup = true;
                    break;
                }
            }
            if (dup) {
                System.out.print("Duplicate found: ");
                c.print();
                System.out.println();
                break;
            }
            cards[size] = c;
            size++;
        }
    }
}