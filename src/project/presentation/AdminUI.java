package project.presentation;

import project.dao.FoodDAO;
import project.dao.PCDAO;
import project.model.Food;
import project.model.PC;

import java.util.List;
import java.util.Scanner;

public class AdminUI {

    private PCDAO pcDAO = new PCDAO();
    private FoodDAO foodDAO = new FoodDAO();
    private Scanner scanner = new Scanner(System.in);

    // ================= MENU CHÍNH =================
    public void showMenu() {

        while (true) {
            System.out.println("\n===== MENU QUẢN TRỊ =====");
            System.out.println("1. Quản lý máy trạm");
            System.out.println("2. Quản lý menu đồ ăn");
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
                    managePC();
                    break;
                case 2:
                    manageFood();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    // ================= QUẢN LÝ PC =================
    private void managePC() {

        System.out.println("\n--- DANH SÁCH MÁY ---");

        List<PC> list = pcDAO.getAllPCs();

        for (PC pc : list) {
            System.out.printf("ID: %d | Tên: %s | Giá: %.0f | Trạng thái: %s\n",
                    pc.getId(),
                    pc.getPcName(),
                    pc.getPricePerHour(),
                    pc.getStatus());
        }

        int choice;
        while (true) {
            System.out.println("\n1. Thêm máy");
            System.out.println("2. Xóa máy");
            System.out.println("3. Sửa máy");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= 3) break;
            } catch (Exception e) {}

            System.out.println("Lựa chọn không hợp lệ!");
        }

        // ===== THÊM MÁY =====
        if (choice == 1) {

            String name;
            String config;
            int category;
            double price;

            // Tên máy
            while (true) {
                System.out.print("Tên máy: ");
                name = scanner.nextLine();

                if (!name.trim().isEmpty()) break;

                System.out.println("Không được để trống!");
            }

            // Khu vực
            while (true) {
                System.out.print("Khu vực (1-Standard, 2-VIP): ");
                try {
                    category = Integer.parseInt(scanner.nextLine());

                    if (category == 1 || category == 2) break;

                    System.out.println("Chỉ nhập 1 hoặc 2!");
                } catch (Exception e) {
                    System.out.println("Nhập số hợp lệ!");
                }
            }

            // Cấu hình
            while (true) {
                System.out.print("Cấu hình: ");
                config = scanner.nextLine();

                if (!config.trim().isEmpty()) break;

                System.out.println("Không được để trống!");
            }

            // Giá
            while (true) {
                System.out.print("Giá/giờ: ");
                try {
                    price = Double.parseDouble(scanner.nextLine());

                    if (price > 0) break;

                    System.out.println("Giá phải > 0!");
                } catch (Exception e) {
                    System.out.println("Nhập số hợp lệ!");
                }
            }

            PC pc = new PC(name, category, config, price);

            if (pcDAO.addPC(pc)) {
                System.out.println("Thêm máy thành công!");
            } else {
                System.out.println("Thêm máy thất bại!");
            }
        } else if (choice == 2) {

            int id;

            // ===== NHẬP ID =====
            while (true) {
                System.out.print("Nhập ID máy cần xóa: ");
                String input = scanner.nextLine();

                if (input.trim().isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                try {
                    id = Integer.parseInt(input);

                    if (id <= 0) {
                        System.out.println("ID phải > 0!");
                        continue;
                    }

                    break;
                } catch (Exception e) {
                    System.out.println("ID phải là số!");
                }
            }

            // ===== NHẬP XÁC NHẬN =====
            String confirm;

            while (true) {
                System.out.print("Xác nhận xóa (Y/N): ");
                confirm = scanner.nextLine();

                if (confirm.trim().isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("N")) {
                    break;
                }

                System.out.println("Chỉ được nhập Y hoặc N!");
            }

            // ===== XỬ LÝ =====
            if (confirm.equalsIgnoreCase("Y")) {

                boolean result = pcDAO.deletePC(id);

                if (result) {
                    System.out.println("Xóa thành công");
                } else {
                    System.out.println("Xóa thất bại (ID không tồn tại)");
                }

            } else {
                System.out.println("Đã hủy thao tác");
            }
        } else if (choice == 3) {

            System.out.print("Nhập ID máy cần sửa: ");
            int id;

            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("ID không hợp lệ");
                return;
            }

            PC oldPC = null;

            for (PC pc : list) {
                if (pc.getId() == id) {
                    oldPC = pc;
                    break;
                }
            }

            if (oldPC == null) {
                System.out.println("Không tìm thấy máy");
                return;
            }

            // HIỂN THỊ THÔNG TIN CŨ
            System.out.println("\n--- THÔNG TIN MÁY ---");
            System.out.println("Tên: " + oldPC.getPcName());
            System.out.println("Khu vực: " + oldPC.getCategoryId());
            System.out.println("Cấu hình: " + oldPC.getConfig());
            System.out.println("Giá: " + oldPC.getPricePerHour());

            String name;
            String config;
            int category;
            double price;

            // ===== TÊN =====
            while (true) {
                System.out.print("Tên mới: ");
                name = scanner.nextLine();

                if (!name.trim().isEmpty()) break;

                System.out.println("Không được để trống!");
            }

            // ===== KHU VỰC =====
            while (true) {
                System.out.print("Khu vực (1-Standard, 2-VIP): ");
                try {
                    category = Integer.parseInt(scanner.nextLine());

                    if (category == 1 || category == 2) break;

                    System.out.println("Chỉ nhập 1 hoặc 2");
                } catch (Exception e) {
                    System.out.println("Nhập số hợp lệ");
                }
            }

            // ===== CẤU HÌNH =====
            while (true) {
                System.out.print("Cấu hình mới: ");
                config = scanner.nextLine();

                if (!config.trim().isEmpty()) break;

                System.out.println("Không được để trống!");
            }

            // ===== GIÁ =====
            while (true) {
                System.out.print("Giá mới: ");
                try {
                    price = Double.parseDouble(scanner.nextLine());

                    if (price > 0) break;

                    System.out.println("Giá phải > 0!");
                } catch (Exception e) {
                    System.out.println("Nhập số hợp lệ!");
                }
            }

            PC pc = new PC(id, name, category, config, price, oldPC.getStatus());

            if (pcDAO.updatePC(pc)) {
                System.out.println("Cập nhật máy thành công");
            } else {
                System.out.println("Cập nhật thất bại");
            }
        }
    }
    // ================= QUẢN LÝ FOOD =================
    private void manageFood() {

        System.out.println("\n--- DANH SÁCH MÓN ---");

        List<Food> list = foodDAO.getAllFoods();

        for (Food f : list) {
            System.out.printf("ID: %d | Tên: %s | Giá: %.0f | Tồn: %d\n",
                    f.getId(),
                    f.getName(),
                    f.getPrice(),
                    f.getStock());
        }

        int choice;

        while (true) {
            System.out.println("\n1. Thêm món");
            System.out.println("2. Xóa món");
            System.out.println("3. Sửa món");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= 3) break;
            } catch (Exception e) {}

            System.out.println("Lựa chọn không hợp lệ!");
        }

        // ================= THÊM =================
        if (choice == 1) {

            String name;
            while (true) {
                System.out.print("Tên món: ");
                name = scanner.nextLine().trim();
                if (!name.isEmpty()) break;
                System.out.println("Không được để trống!");
            }

            String desc;
            while (true) {
                System.out.print("Mô tả: ");
                desc = scanner.nextLine().trim();
                if (!desc.isEmpty()) break;
                System.out.println("Không được để trống!");
            }

            double price;
            while (true) {
                System.out.print("Giá: ");
                try {
                    price = Double.parseDouble(scanner.nextLine());
                    if (price >= 0) break;
                } catch (Exception e) {}
                System.out.println("Giá không hợp lệ!");
            }

            int stock;
            while (true) {
                System.out.print("Số lượng: ");
                try {
                    stock = Integer.parseInt(scanner.nextLine());
                    if (stock >= 0) break;
                } catch (Exception e) {}
                System.out.println("Số lượng không hợp lệ!");
            }

            if (foodDAO.addFood(new Food(name, desc, price, stock))) {
                System.out.println("Thêm món thành công");
            } else {
                System.out.println("Thêm món thất bại");
            }
        }

        // ================= XÓA =================
        else if (choice == 2) {

            int id;

            while (true) {
                System.out.print("Nhập ID món cần xóa: ");
                try {
                    id = Integer.parseInt(scanner.nextLine());

                    int finalId = id;
                    boolean exists = list.stream().anyMatch(f -> f.getId() == finalId);
                    if (exists) break;

                } catch (Exception e) {}

                System.out.println("ID không tồn tại!");
            }

            if (foodDAO.deleteFood(id)) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
        }

        // ================= SỬA =================
        else if (choice == 3) {

            int id;
            Food oldFood = null;

            while (true) {
                System.out.print("Nhập ID món cần sửa: ");
                try {
                    id = Integer.parseInt(scanner.nextLine());

                    for (Food f : list) {
                        if (f.getId() == id) {
                            oldFood = f;
                            break;
                        }
                    }

                    if (oldFood != null) break;

                } catch (Exception e) {}

                System.out.println("ID không tồn tại!");
            }

            System.out.println("Thông tin cũ:");
            System.out.println("Tên: " + oldFood.getName());
            System.out.println("Mô tả: " + oldFood.getDescription());
            System.out.println("Giá: " + oldFood.getPrice());
            System.out.println("Tồn: " + oldFood.getStock());

//            String name;
//            while (true) {
//                System.out.print("Tên mới: ");
//                name = scanner.nextLine().trim();
//                if (!name.isEmpty()) break;
//                System.out.println("Không được để trống!");
//            }

            String desc;
            while (true) {
                System.out.print("Mô tả mới: ");
                desc = scanner.nextLine().trim();
                if (!desc.isEmpty()) break;
                System.out.println("Không được để trống!");
            }

            double price;
            while (true) {
                System.out.print("Giá mới: ");
                try {
                    price = Double.parseDouble(scanner.nextLine());
                    if (price >= 0) break;
                } catch (Exception e) {}
                System.out.println("Giá không hợp lệ!");
            }

            int stock;
            while (true) {
                System.out.print("Số lượng mới: ");
                try {
                    stock = Integer.parseInt(scanner.nextLine());
                    if (stock >= 0) break;
                } catch (Exception e) {}
                System.out.println("Số lượng không hợp lệ!");
            }

            Food food = new Food(id, oldFood.getName(), desc, price, stock);

            if (foodDAO.updateFood(food)) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Cập nhật thất bại");
            }
        }
    }
}