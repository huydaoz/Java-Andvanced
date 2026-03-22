package session11.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//    Phần 1 – Phân tích
//
//    Vấn đề của code hiện tại:
//
//            return DriverManager.getConnection(...);
//
//    Nhìn thì đơn giản, nhưng lỗi cực nguy hiểm:
//
//    1. Không đóng kết nối (Connection leak)
//
//    Mỗi lần gọi getHospitalConn() → tạo 1 connection mới
//    Nhưng không có close()
//    Sau vài tiếng:
//    DB hết connection
//    App treo → đúng lỗi Communications link failure
//
//    Đây gọi là: Connection Leak
//
//    2. Không quản lý tập trung
//    Hard-code:  "jdbc:mysql://192.168.1.10:3306/Hospital_DB"
//    Nếu đổi IP / DB → sửa toàn bộ code
//
//    3. Hệ thống y tế = 24/7
//    Không được phép:
//    Treo
//    Mất kết nối
//    Nếu lỗi: Không truy xuất được bệnh án

public class DBContext {

    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    Connection conn = null;
    public static void close(Connection conn) {
        try {
            conn = DBContext.getConnection();

            System.out.println("Kết nối thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBContext.close(conn);
        }
    }
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Loaded MySQL Driver!");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy MySQL Driver!");
        }
    }
}
