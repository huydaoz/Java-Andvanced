package session5.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import session5.model.Food;
import session5.model.MenuItem;
import session5.model.Order;
import session5.model.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {




    @Test
    void calculateRevenue() {
        OrderService service = new OrderService();

        Order order = new Order("O1");

        MenuItem burger = new Food("F1","Burger",50000);

        order.addItem(burger,2);

        order.setStatus(OrderStatus.PAID);

        service.addOrder(order);

        double revenue = service.calculateRevenue();

        assertEquals(100000, revenue);
    }
}