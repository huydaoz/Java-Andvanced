package project.dao;

import project.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderDAO {

    // ================= TẠO ORDER =================
    public int createOrder(int bookingId) {

        String sql = "INSERT INTO orders(booking_id, total_price) VALUES (?, 0)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, bookingId);

            int affected = ps.executeUpdate();

            if (affected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    // ================= TÍNH TIỀN ĐỒ ĂN =================
    public double calculateFoodPrice(int bookingId) {

        String sql = "SELECT SUM(od.quantity * od.price) AS total " +
                "FROM orders o " +
                "JOIN order_details od ON o.id = od.order_id " +
                "WHERE o.booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double total = rs.getDouble("total");

                // Nếu NULL (không có món nào)
                if (rs.wasNull()) {
                    return 0;
                }

                return total;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}