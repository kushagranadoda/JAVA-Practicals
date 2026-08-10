import java.util.Scanner;

enum Coin {
    ONE, TWO, FIVE, TEN
}

public class Vending {
    int price = 15;
    int total = 0;

    void addCoin(int value) {
        total += value;
        System.out.println("Total so far: " + total);
    }

    boolean isPaid() {
        return total >= price;
    }

    int getChange() {
        return total - price;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vending vm = new Vending();

        while (!vm.isPaid()) {
            Coin coin = Coin.valueOf(sc.next().toUpperCase());

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            vm.addCoin(value);
        }

        System.out.println("Paid. Change: " + vm.getChange());
    }
}