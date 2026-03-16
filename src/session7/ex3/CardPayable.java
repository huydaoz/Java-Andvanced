package session7.ex3;

public interface CardPayable extends PaymentMethod {
    void processCard(double amount);
}