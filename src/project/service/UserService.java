package project.service;

import project.dao.UserDAO;
import project.model.User;
import project.util.HashUtil;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // ================= REGISTER =================
    public boolean register(String username, String password, String fullName, String phone) {

        // Validate password
        if (password == null || password.length() < 6) {
            System.out.println("Mật khẩu phải có ít nhất 6 ký tự");
            return false;
        }

        // Check username tồn tại
        if (userDAO.isUsernameExists(username)) {
            System.out.println("Tên đăng nhập đã tồn tại");
            return false;
        }

        // Hash password
        String hashedPassword = HashUtil.hashPassword(password);

        // Tạo user
        User user = new User(username, hashedPassword, fullName, phone, "CUSTOMER");

        // Lưu DB
        boolean result = userDAO.registerUser(user);

        if (result) {
            System.out.println("Đăng ký thành công");
        } else {
            System.out.println("Đăng ký thất bại");
        }

        return result;
    }

    // ================= LOGIN =================
    public User login(String username, String password) {

        // Hash password
        String hashedPassword = HashUtil.hashPassword(password);

        User user = userDAO.login(username, hashedPassword);

        if (user == null) {
            System.out.println("Sai tài khoản hoặc mật khẩu");
        }

        return user;
    }
}