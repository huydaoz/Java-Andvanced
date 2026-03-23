package session12.ex5.dao;

import session12.ex5.config.DBConnection;
import session12.ex5.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // 1. Lấy danh sách
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Patients";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 2. Thêm bệnh nhân
    public void addPatient(Patient p) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Patients(name, age, department) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getDepartment());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Update bệnh án
    public void updateDisease(int id, String disease) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Patients SET disease = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, disease);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Gọi stored procedure
    public double calculateFee(int id) {
        double fee = 0;

        try (Connection conn = DBConnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{call CALCULATE_DISCHARGE_FEE(?, ?)}");

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();

            fee = cs.getDouble(2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fee;
    }
}