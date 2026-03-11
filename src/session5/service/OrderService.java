package session5.service;
import session5.model.Order;
import session5.model.OrderStatus;
import session5.repository.OrderRepository;

import java.util.List;

public class OrderService {

    private OrderRepository repository = new OrderRepository();

    public void createOrder(Order order) {
        repository.addOrder(order);
    }

    public List<Order> getOrders() {
        return repository.getAllOrders();
    }

    public double calculateRevenue() {

        return repository.getAllOrders()
                .stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID)
                .mapToDouble(Order::calculateTotal)
                .sum();

    }

    public void addOrder(Order order) {
    }
}