package session7.ex2;

public class OrderCalculator {
    private DiscountStrategy discountStrategy;
    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    public double calculateFinalAmount(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}
