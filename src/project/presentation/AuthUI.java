package project.presentation;

import project.model.User;
import project.service.UserService;

import java.util.Scanner;

public class AuthUI {

    private UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);

    public User showMenu() {

        while (true) {
            System.out.println("\n===== MENU XÁC THỰC =====");
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng");
                continue;
            }

            switch (choice) {
                case 1:
                    handleRegister();
                    break;
                case 2:
                    User user = handleLogin();
                    if (user != null) {
                        return user;
                    }
                    break;
                case 0:
                    return null;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    // ================= ĐĂNG KÝ =================
    private void handleRegister() {

        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine();

        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();

        if (password.length() < 6) {
            System.out.println("Mật khẩu phải ít nhất 6 ký tự");
            return;
        }

        System.out.print("Họ tên: ");
        String fullName = scanner.nextLine();

        System.out.print("Số điện thoại: ");
        String phone = scanner.nextLine();

        boolean result = userService.register(username, password, fullName, phone);

        if (result) {
            System.out.println("Đăng ký thành công");
        } else {
            System.out.println("Đăng ký thất bại (có thể trùng username)");
        }
    }

    // ================= ĐĂNG NHẬP =================
    private User handleLogin() {

        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine();

        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);

        if (user == null) {
            System.out.println("Đăng nhập thất bại");
        } else {
            System.out.println("Đăng nhập thành công - Quyền: " + user.getRole());
        }
        return user;
    }
}