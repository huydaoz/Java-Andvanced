package session7.ex5;

public class POSDiscount implements DiscountStrategy {
    public double apply(double total) {
        System.out.println("Áp dụng giảm giá cho thành viên tại cửa hàng");
        return total * 0.95;
    }
}