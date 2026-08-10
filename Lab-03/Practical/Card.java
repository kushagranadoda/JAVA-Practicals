public class Card {
    private String rank, suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public void print() {
        System.out.print(rank + " of " + suit);
    }

    public boolean isSame(Card other) {
        return this.rank.equals(other.rank) && this.suit.equals(other.suit);
    }
}