package session7.ex5;

public class WebsiteDiscount implements DiscountStrategy {
    public double apply(double total) {
        System.out.println("Áp dụng giảm giá 10% cho đơn hàng website");
        return total * 0.9;
    }
}