package app;

import discount.DiscountRule;
import java.util.Scanner;

public class DiscountEngine {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] prices = {
            500,
            1000,
            1500,
            2000,
            2500
        };

        System.out.println("===== DISCOUNT ENGINE =====");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat Rs. 100 Discount");
        System.out.println("4. 30% Discount for prices above Rs. 1000");

        System.out.print("Choose a discount rule: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        switch (choice) {

            case 1:
                rule = price -> price * 0.90;
                break;

            case 2:
                rule = price -> price * 0.80;
                break;

            case 3:
                rule = price -> price - 100;
                break;

            case 4:
                rule = price -> {
                    if (price > 1000) {
                        return price * 0.70;
                    }
                    return price;
                };
                break;

            default:
                System.out.println("Invalid choice.");
                sc.close();
                return;
        }

        System.out.println("\n===== FINAL PRICES =====");

        for (double price : prices) {

            double finalPrice = rule.apply(price);

            System.out.println(
                "Original Price: Rs. " + price +
                " | Final Price: Rs. " + finalPrice
            );
        }

        sc.close();
    }
}