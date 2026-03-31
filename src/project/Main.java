package project;

import project.model.User;
import project.presentation.AdminUI;
import project.presentation.AuthUI;
import project.presentation.CustomerUI;
import project.presentation.StaffUI;

public class Main {

    public static void main(String[] args) {

        AuthUI authUI = new AuthUI();

        while (true) {

            User user = authUI.showMenu();

            if (user == null) {
                System.out.println("Thoát chương trình!");
                break;
            }

            switch (user.getRole()) {

                case "ADMIN":
                    System.out.println("\n=== ĐĂNG NHẬP VỚI QUYỀN ADMIN ===");
                    new AdminUI().showMenu();
                    break;

                case "STAFF":
                    new StaffUI().showMenu();
                    break;

                case "CUSTOMER":
                    new CustomerUI().showMenu(user);
                    break;

                default:
                    System.out.println("Role không hợp lệ!");
            }
        }
    }
}