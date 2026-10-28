package Discount.Rules;

@FunctionalInterface
public interface DiscountRule {
    double apply(double price);
}