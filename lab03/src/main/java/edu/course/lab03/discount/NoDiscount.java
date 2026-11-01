package edu.course.lab03.discount;

public class NoDiscount implements DiscountPolicy {
    @Override
    public double apply(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        return price;
    }

}
