package session7.ex4;
import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}