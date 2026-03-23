//package session12.ex4;
//
//public class ex4 {
//    String sql = "INSERT INTO Results (data) VALUES(?)";
//
//    PreparedStatement ps = conn.prepareStatement(sql);
//
//    for (TestResult tr : list) {
//        ps.setString(1, tr.getData());
//        ps.executeUpdate();
//    }
//}
//    Phan 1
//    Việc sử dụng Statement trong vòng lặp khiến mỗi câu lệnh SQL phải được phân tích và lập kế hoạch thực thi lại từ đầu
//    Do câu lệnh khác nhau về giá trị nên Database không thể tái sử dụng Execution Plan
//    Điều này gây lãng phí CPU và làm giảm hiệu năng nghiêm trọng khi xử lý số lượng lớn dữ liệu