package session7.ex5;

public class OrderService {

    private DiscountStrategy discount;
    private PaymentMethod payment;
    private NotificationService notification;

    public OrderService(SalesChannelFactory factory) {
        discount = factory.createDiscount();
        payment = factory.createPayment();
        notification = factory.createNotification();
    }

    public void processOrder(double total) {
        double finalAmount = discount.apply(total);
        payment.pay(finalAmount);
        notification.send("Order success");
    }
}