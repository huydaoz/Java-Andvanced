package session7.ex3;

public interface CODPayable extends PaymentMethod {
    void processCOD(double amount);
}