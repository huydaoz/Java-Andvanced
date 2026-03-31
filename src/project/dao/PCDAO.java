package project.dao;

import project.model.PC;
import project.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PCDAO {

    // ================= LẤY DANH SÁCH PC =================
    public List<PC> getAllPCs() {

        List<PC> list = new ArrayList<PC>();

        String sql = "SELECT * FROM pcs";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                PC pc = new PC(
                        rs.getInt("id"),
                        rs.getString("pc_name"),
                        rs.getInt("category_id"),
                        rs.getString("config"),
                        rs.getDouble("price_per_hour"),
                        rs.getString("status")
                );

                list.add(pc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= THÊM PC =================
    public boolean addPC(PC pc) {

        String sql = "INSERT INTO pcs(pc_name, category_id, config, price_per_hour) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pc.getPcName());
            ps.setInt(2, pc.getCategoryId());
            ps.setString(3, pc.getConfig());
            ps.setDouble(4, pc.getPricePerHour());

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Thêm máy thất bại");
        }

        return false;
    }

    // ================= CẬP NHẬT PC =================
    public boolean updatePC(PC pc) {

        String sql = "UPDATE pcs SET pc_name=?, category_id=?, config=?, price_per_hour=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pc.getPcName());
            ps.setInt(2, pc.getCategoryId());
            ps.setString(3, pc.getConfig());
            ps.setDouble(4, pc.getPricePerHour());
            ps.setInt(5, pc.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= XÓA PC =================
    public boolean deletePC(int id) {

        String sql = "DELETE FROM pcs WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Xóa máy thất bại");
        }

        return false;
    }
}