package project.model;

public class User {

    private int id;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private double balance;
    private String role;

    // ================= CONSTRUCTOR =================
    public User() {
    }

    public User(int id, String username, String password,
                String fullName, String phone,
                double balance, String role) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.balance = balance;
        this.role = role;
    }

    public User(String username, String password,
                String fullName, String phone, String role) {

        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
    }

    // ================= GETTER =================
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public double getBalance() {
        return balance;
    }

    public String getRole() {
        return role;
    }

    // ================= SETTER =================
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setRole(String role) {
        this.role = role;
    }
}