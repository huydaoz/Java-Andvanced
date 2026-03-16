package session7.ex4;

public class OrderService {
    private OrderRepository repository;
    private NotificationService notificationService;

    public OrderService(OrderRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void createOrder(String orderId, String customerEmail) {
        Order order = new Order(orderId);
        repository.save(order);
        notificationService.send("Đơn hàng " + orderId + " đã được tạo", customerEmail);
    }
}