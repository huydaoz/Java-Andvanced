package session11.ex3;
import session11.ex1.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BedService {
    public static void updateBedStatus(int bedId) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBContext.getConnection();

            String sql = "UPDATE Beds SET bed_status = ? WHERE bed_id = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, "Occupied");
            ps.setInt(2, bedId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật giường thành công!");
            } else {
                System.out.println("Lỗi: Mã giường không tồn tại!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                DBContext.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

//    PHẦN 1 – Phân tích
//    executeUpdate() trả về số dòng bị ảnh hưởng
//    Nếu = 0 → không có bản ghi phù hợp
//    Cần dùng giá trị này để kiểm tra và thông báo lỗi chính xác
