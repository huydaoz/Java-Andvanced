package session7.ex5;

public class PushNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Gửi push notification: Đơn hàng thành công");
    }
}
