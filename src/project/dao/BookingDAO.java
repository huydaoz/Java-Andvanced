package project.dao;

import project.model.Booking;
import project.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // ================= CHECK TRÙNG GIỜ =================
    public boolean isPCBooked(int pcId, LocalDateTime start, LocalDateTime end) {

        String sql = "SELECT * FROM bookings " +
                "WHERE pc_id = ? " +
                "AND status != 'COMPLETED' " +
                "AND ( " +
                "   (? BETWEEN start_time AND end_time) " +
                "   OR (? BETWEEN start_time AND end_time) " +
                "   OR (start_time BETWEEN ? AND ?) " + ")";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pcId);
            ps.setTimestamp(2, Timestamp.valueOf(start));
            ps.setTimestamp(3, Timestamp.valueOf(end));
            ps.setTimestamp(4, Timestamp.valueOf(start));
            ps.setTimestamp(5, Timestamp.valueOf(end));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= TẠO BOOKING =================
    public int createBooking(Booking booking) {

        String sql = "INSERT INTO bookings(user_id, pc_id, start_time, end_time) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getPcId());
            ps.setTimestamp(3, Timestamp.valueOf(booking.getStartTime()));
            ps.setTimestamp(4, Timestamp.valueOf(booking.getEndTime()));

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

    // ================= LẤY BOOKING THEO USER =================
    public List<Booking> getByUser(int userId) {

        List<Booking> list = new ArrayList<Booking>();

        String sql = "SELECT * FROM bookings WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking b = new Booking(
                        rs.getInt("user_id"),
                        rs.getInt("pc_id"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime()
                );

                b.setId(rs.getInt("id"));
                b.setStatus(rs.getString("status"));

                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= UPDATE STATUS =================
    public boolean updateStatus(int bookingId, String status) {

        String sql = "UPDATE bookings SET status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, bookingId);

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= LẤY TẤT CẢ BOOKING =================
    public List<Booking> getAll() {

        List<Booking> list = new ArrayList<Booking>();

        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking b = new Booking(
                        rs.getInt("user_id"),
                        rs.getInt("pc_id"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime()
                );

                b.setId(rs.getInt("id"));
                b.setStatus(rs.getString("status"));

                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= TÍNH TIỀN PC =================
    public double calculatePCPrice(int bookingId) {

        String sql = "SELECT TIMESTAMPDIFF(HOUR, b.start_time, b.end_time) AS hours, " +
                "p.price_per_hour " +
                "FROM bookings b " +
                "JOIN pcs p ON b.pc_id = p.id " +
                "WHERE b.id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int hours = rs.getInt("hours");
                double price = rs.getDouble("price_per_hour");

                return hours * price;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ================= LẤY USER ID =================
    public int getUserIdByBooking(int bookingId) {

        String sql = "SELECT user_id FROM bookings WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    // ================= LẤY STATUS =================
    public String getStatus(int bookingId) {

        String sql = "SELECT status FROM bookings WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("status");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}