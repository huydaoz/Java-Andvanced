package session12.ex1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ex1 {
    public void login(Connection conn, String code, String pass) {
        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, code);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid credentials!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB hoặc SQL: " + e.getMessage());
        }
    }
}
//    Phan 1
//    PreparedStatement sử dụng cơ chế pre-compiled SQL
//    Câu lệnh SQL được biên dịch trước, tách biệt khỏi dữ liệu đầu vào
//    Các tham số (?) được bind sau → chỉ được coi là dữ liệu
//    Điều này ngăn chặn hoàn toàn việc chèn mã SQL độc hại (SQL Injection)