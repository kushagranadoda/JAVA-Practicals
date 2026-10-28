package app;

import discount.DiscountRule;
import java.util.Scanner;

public class DiscountEngine {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // List of prices
        double[] prices = {500, 1000, 1500, 2000, 2500};

        System.out.println("Choose Discount Rule:");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat Rs. 100 Discount");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        // User selects the lambda at runtime
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

            default:
                System.out.println("Invalid choice");
                sc.close();
                return;
        }

        System.out.println("\nPrices after discount:");

        for (double price : prices) {

            double finalPrice = rule.apply(price);

            System.out.println(
                "Original: Rs. " + price +
                " -> Final: Rs. " + finalPrice
            );
        }

        sc.close();
    }
}