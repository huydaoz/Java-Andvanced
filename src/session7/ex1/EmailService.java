package session7.ex1;

public class EmailService {

    public void sendEmail(Customer customer, Order order) {

        System.out.println("Đã gửi email đến "
                + customer.getEmail()
                + ": Đơn hàng "
                + order.getOrderId()
                + " đã được tạo");
    }
}