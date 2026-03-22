package session11.ex2;
import session11.ex1.DBContext;

import java.sql.*;

public class PharmacyService {

    public static void printAllMedicine() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT medicine_name, stock FROM Pharmacy";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock");

                System.out.println("Thuốc: " + name + " | Tồn kho: " + stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DBContext.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

//    Phần 1 – Phân tích
//
//    Code lỗi:
//            if (rs.next()) {
//            System.out.println(...);
//    }
//
//    Cách hoạt động của ResultSet:
//
//    Ban đầu con trỏ ở trước dòng đầu
//    rs.next():
//    Lần 1 → dòng 1
//    Lần 2 → dòng 2
//    ...
//    Hết → false
//
//    Với if
//    Chỉ đọc 1 dòng đầu
//    Không lặp → không in hết