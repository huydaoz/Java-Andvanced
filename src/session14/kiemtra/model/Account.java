package session14.kiemtra.model;

public class Account {

    private String accountId;
    private String fullName;
    private double balance;

    public Account(String accountId, String fullName, double balance) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }
}