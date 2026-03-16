package session7.ex3;

public class Main {
    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor();

        // COD
        PaymentMethod cod = new CODPayment();
        processor.processPayment(cod, 500000);

        // Credit Card
        PaymentMethod card = new CreditCardPayment();
        processor.processPayment(card, 1000000);

        // Momo
        PaymentMethod momo = new MomoPayment();
        processor.processPayment(momo, 750000);

        // Kiểm tra LSP
        PaymentMethod payment = new CreditCardPayment();
        processor.processPayment(payment, 200000);

        payment = new MomoPayment();
        processor.processPayment(payment, 200000);
    }
}