package session12.ex3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ex3 {
    public void calculateSurgeryFee(Connection conn, int surgeryId) {
        String sql = "{call GET_SURGERY_FEE(?, ?)}";
        try {
            CallableStatement cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, surgeryId);
            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            double cost = cstmt.getDouble(2);
            System.out.println("Total surgery cost: " + cost);

        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB hoặc SQL: " + e.getMessage());
        }
    }
}

//    Phan 1
//    registerOutParameter() dùng để khai báo tham số đầu ra cho CallableStatement
//    JDBC không tự suy ra tham số OUT nên lập trình viên phải đăng ký thủ công
//    Nếu không đăng ký, sẽ không thể lấy dữ liệu OUT → gây lỗi runtime
//    Với kiểu DECIMAL trong SQL → dùng Types.DECIMAL trong Java