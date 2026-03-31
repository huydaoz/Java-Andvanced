package project.dao;

import project.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDetailDAO {

    // ================= THÊM MÓN VÀO ORDER =================
    public boolean addFood(int orderId, int foodId, int quantity, double price) {

        String sql = "INSERT INTO order_details(order_id, food_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, foodId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Thêm món vào đơn thất bại");
        }

        return false;
    }
}