package session7.ex1;

public class Main {
    public static void main(String[] args) {

        // tạo sản phẩm
        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        System.out.println("Đã thêm sản phẩm SP01, SP02");

        // tạo khách hàng
        Customer customer = new Customer(
                "Nguyễn Văn A",
                "a@example.com",
                "Hà Nội"
        );

        System.out.println("Đã thêm khách hàng");

        // tạo đơn hàng
        Order order = new Order("ORD001", customer);

        order.addProduct(p1, 1);
        order.addProduct(p2, 2);

        System.out.println("Đơn hàng ORD001 được tạo");

        // tính tiền
        OrderCalculator calculator = new OrderCalculator();

        double total = calculator.calculateTotal(order);

        System.out.println("Tổng tiền: " + total);

        // lưu đơn
        OrderRepository repository = new OrderRepository();

        repository.save(order);

        // gửi email
        EmailService emailService = new EmailService();

        emailService.sendEmail(customer, order);
    }
}