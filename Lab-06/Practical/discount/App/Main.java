package Discount.App;

import Discount.engine.DiscountEngine;
import Discount.rules.DiscountRule;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[] prices = {100, 250, 500, 999};

        DiscountRule flat10 = p -> p - 10;
        DiscountRule percent20 = p -> p * 0.8;
        DiscountRule bulk = p -> p > 500 ? p * 0.7 : p;

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a discount rule:");
        System.out.println("1. Flat Rs.10 off");
        System.out.println("2. 20% off");
        System.out.println("3. 30% off if price > 500");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        DiscountRule rule = switch (choice) {
            case 1 -> flat10;
            case 2 -> percent20;
            case 3 -> bulk;
            default -> p -> p;
        };

        DiscountEngine engine = new DiscountEngine();
        double[] result = engine.applyAll(prices, rule);

        System.out.println("Original: " + Arrays.toString(prices));
        System.out.println("Discounted: " + Arrays.toString(result));
    }
}