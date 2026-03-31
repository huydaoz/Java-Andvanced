package project.dao;

import project.model.Food;
import project.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

    // ================= LẤY TẤT CẢ MÓN =================
    public List<Food> getAllFoods() {

        List<Food> list = new ArrayList<Food>();

        String sql = "SELECT * FROM foods";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Food food = new Food(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );

                list.add(food);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= THÊM MÓN =================
    public boolean addFood(Food food) {

        String sql = "INSERT INTO foods(name, description, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, food.getName());
            ps.setString(2, food.getDescription());
            ps.setDouble(3, food.getPrice());
            ps.setInt(4, food.getStock());

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Thêm món thất bại");
        }

        return false;
    }

    // ================= CẬP NHẬT MÓN =================
    public boolean updateFood(Food food) {

        String sql = "UPDATE foods SET name=?, description=?, price=?, stock=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, food.getName());
            ps.setString(2, food.getDescription());
            ps.setDouble(3, food.getPrice());
            ps.setInt(4, food.getStock());
            ps.setInt(5, food.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= XÓA MÓN =================
    public boolean deleteFood(int id) {

        String sql = "DELETE FROM foods WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Xóa món thất bại");
        }

        return false;
    }
    public boolean decreaseStock(int foodId, int quantity) {

        String sql = "UPDATE foods SET stock = stock - ? WHERE id = ? AND stock >= ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, foodId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}