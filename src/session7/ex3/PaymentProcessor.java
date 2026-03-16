package session7.ex3;

public class PaymentProcessor {
    public void processPayment(PaymentMethod method, double amount) {
        if (method instanceof CODPayable) {
            ((CODPayable) method).processCOD(amount);
        }
        else if (method instanceof CardPayable) {
            ((CardPayable) method).processCard(amount);
        }
        else if (method instanceof EWalletPayable) {
            ((EWalletPayable) method).processEWallet(amount);
        }
        else {
            System.out.println("Phương thức thanh toán không hợp lệ");
        }
    }
}