package session7.ex5;

public class MobileDiscount implements DiscountStrategy {
    public double apply(double total) {
        System.out.println("Áp dụng giảm giá 15% cho lần đầu");
        return total * 0.85;
    }
}