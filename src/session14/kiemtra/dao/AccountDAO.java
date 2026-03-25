package session14.kiemtra.dao;


import session14.kiemtra.config.DBConnection;
import session14.kiemtra.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    public Account getAccountById(String id) {
        String sql = "SELECT * FROM Accounts WHERE AccountId = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getString("AccountId"),
                        rs.getString("FullName"),
                        rs.getDouble("Balance")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void printAccount(String id) {
        String sql = "SELECT * FROM Accounts WHERE AccountId = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.printf("%-10s %-20s %-10.2f\n",
                        rs.getString("AccountId"),
                        rs.getString("FullName"),
                        rs.getDouble("Balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}