package session7.ex3;

public interface EWalletPayable extends PaymentMethod {
    void processEWallet(double amount);
}