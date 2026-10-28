package Discount.Engine;

import Discount.*;
iscount.rules.DiscountRule;

public class DiscountEngine {
    public double[] applyAll(double[] prices, DiscountRule rule) {
        double[] result = new double[prices.length];
        for (int i = 0; i < prices.length; i++) {
            result[i] = rule.apply(prices[i]);
        }
        return result;
    }
}