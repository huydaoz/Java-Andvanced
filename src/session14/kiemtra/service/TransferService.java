package session14.kiemtra.service;

import session14.kiemtra.config.DBConnection;
import session14.kiemtra.dao.AccountDAO;
import session14.kiemtra.model.Account;

import java.sql.CallableStatement;
import java.sql.Connection;

public class TransferService {

    public void transfer(String fromId, String toId, double amount) {

        AccountDAO dao = new AccountDAO();

        Account from = dao.getAccountById(fromId);
        Account to = dao.getAccountById(toId);

        if (from == null || to == null) {
            System.out.println(" Tài khoản không tồn tại!");
            return;
        }

        if (from.getBalance() < amount) {
            System.out.println(" Không đủ tiền!");
            return;
        }

        try (
                Connection conn = DBConnection.getConnection()
        ) {
            conn.setAutoCommit(false);

            String call = "{CALL sp_UpdateBalance(?, ?)}";

            try (
                    CallableStatement cs1 = conn.prepareCall(call);
                    CallableStatement cs2 = conn.prepareCall(call)
            ) {

                cs1.setString(1, fromId);
                cs1.setDouble(2, -amount);
                cs1.execute();

                cs2.setString(1, toId);
                cs2.setDouble(2, amount);
                cs2.execute();

                conn.commit();
                System.out.println(" Chuyển khoản thành công!");

            } catch (Exception e) {
                conn.rollback(); // rollback nếu lỗi
                System.out.println(" Lỗi -> rollback!");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}