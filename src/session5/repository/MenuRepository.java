package session5.repository;
import session5.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private List<MenuItem> menuList = new ArrayList<>();

    public void add(MenuItem item) {
        menuList.add(item);
    }

    public List<MenuItem> getAll() {
        return menuList;
    }

}