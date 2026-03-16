package session7.ex5;

public class CashPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán tiền mặt tại quầy");
    }
}