package session7.ex2;

public class FixedDiscount implements DiscountStrategy {
    private double amount;
    public FixedDiscount(double amount) {
        this.amount = amount;
    }
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - amount;
    }
}