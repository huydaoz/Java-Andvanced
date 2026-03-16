package session7.ex3;

public class CreditCardPayment implements CardPayable {
    @Override
    public void processCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + amount + " - Thành công");
    }
}