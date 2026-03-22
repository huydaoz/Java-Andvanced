package session11.ex4;
import session11.ex1.DBContext;

import java.sql.*;

public class PatientService {

    public static void findPatientByName(String name) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stmt = conn.createStatement();

            // lọc input
            String safeName = sanitizeInput(name);

            String sql = "SELECT * FROM Patients WHERE full_name = '" + safeName + "'";
            rs = stmt.executeQuery(sql);

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id")
                        + " | Name: " + rs.getString("full_name"));
            }

            if (!found) {
                System.out.println("Không tìm thấy bệnh nhân!");
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

    public static String sanitizeInput(String input) {
        if (input == null) return null;

        input = input.replace("'", "");
        input = input.replace("--", "");
        input = input.replace(";", "");

        return input;
    }
}

//    PHẦN 1
//    SQL Injection xảy ra do nối chuỗi trực tiếp
//    Hacker chèn điều kiện luôn đúng (OR '1'='1')
//    Làm cho mệnh đề WHERE luôn TRUE
//    Dẫn đến truy xuất toàn bộ dữ liệu