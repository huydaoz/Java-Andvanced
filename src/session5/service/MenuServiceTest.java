package session5.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import session5.model.Food;
import session5.model.MenuItem;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {



    @Test
    void addItem() {

        MenuService menuService = new MenuService();

        MenuItem burger = new Food("F1", "Burger", 50000);

        menuService.addItem(burger);

        assertEquals(1, menuService.getMenu().size());

    }
}