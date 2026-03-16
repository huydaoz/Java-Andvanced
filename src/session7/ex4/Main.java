package session7.ex4;

public class Main {
    public static void main(String[] args) {

//        OrderRepository repo = new FileOrderRepository();
//        NotificationService notify = new EmailService();
//        OrderService service = new OrderService(repo, notify);
//        service.createOrder("ORD001", "a@example.com");

        OrderRepository repo = new DatabaseOrderRepository();
        NotificationService notify = new SMSNotification();
        OrderService service = new OrderService(repo, notify);
        service.createOrder("ORD002", "a@example.com");
    }
}