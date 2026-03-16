package session7.ex5;

public class EmailNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Gửi email xác nhận");
    }
}