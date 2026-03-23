package session12.ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex2 {
    public void updateVitals(Connection conn, double temp, int heartRate, int patientId) {
        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, temp);
            ps.setInt(2, heartRate);
            ps.setInt(3, patientId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful!");
            } else {
                System.out.println("Patient not found!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB hoặc SQL: " + e.getMessage());
        }
    }
}
//    Phan 1
//    PreparedStatement sử dụng các phương thức như setDouble(), setInt()
//    Dữ liệu được truyền dưới dạng kiểu dữ liệu nguyên thủy, không phải chuỗi
//    JDBC Driver tự động chuyển đổi sang định dạng SQL chuẩn (dùng dấu chấm)
//    Không phụ thuộc vào Locale của hệ điều hành
//    Tránh hoàn toàn lỗi cú pháp do sai định dạng số