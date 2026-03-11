package session4.ex3;

public class UserProcessor {
    public String processEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        String[] parts = email.split("@");
        if (parts.length != 2 || parts[1].isEmpty()) {
            throw new IllegalArgumentException("Invalid domain");
        }
        return email.toLowerCase();
    }
}