package session7.ex1;
import java.util.Map;

public class OrderCalculator {

    public double calculateTotal(Order order) {

        double total = 0;

        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            total += product.getPrice() * quantity;
        }

        order.setTotalAmount(total);

        return total;
    }
}