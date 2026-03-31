package project.presentation;

import project.dao.*;
import project.model.Booking;
import project.model.Food;
import project.model.PC;
import project.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {

    private PCDAO pcDAO = new PCDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private FoodDAO foodDAO = new FoodDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();

    private Scanner scanner = new Scanner(System.in);

    // ================= MENU =================
    public void showMenu(User user) {

        while (true) {
            System.out.println("\n===== MENU KHÁCH HÀNG =====");
            System.out.println("1. Xem máy");
            System.out.println("2. Đặt máy");
            System.out.println("3. Xem đơn của tôi");
            System.out.println("4. Xem số dư");
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
                    showPCs();
                    break;
                case 2:
                    bookingFlow(user);
                    break;
                case 3:
                    showMyBookings(user);
                    break;
                case 4:
                    showBalance(user);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    // ================= CHỌN KHU VỰC =================
    private int chooseCategory() {

        int categoryId;

        while (true) {
            System.out.println("\nChọn khu vực:");
            System.out.println("1. STANDARD");
            System.out.println("2. VIP");
            System.out.print("Chọn: ");

            try {
                categoryId = Integer.parseInt(scanner.nextLine());
                if (categoryId == 1 || categoryId == 2) return categoryId;
            } catch (Exception e) {}

            System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    // ================= XEM PC =================
    private void showPCs() {

        int categoryId = chooseCategory();

        List<PC> list = pcDAO.getAllPCs();

        System.out.println("\n--- DANH SÁCH MÁY ---");
        System.out.printf("%-5s %-15s %-10s %-10s %-10s\n",
                "ID", "Tên máy", "Giá", "Trạng thái", "Khu vực");

        for (PC pc : list) {

            if (pc.getCategoryId() == categoryId) {

                String category = (categoryId == 1) ? "STANDARD" : "VIP";

                System.out.printf("%-5d %-15s %-10.0f %-10s %-10s\n",
                        pc.getId(),
                        pc.getPcName(),
                        pc.getPricePerHour(),
                        pc.getStatus(),
                        category);
            }
        }
    }

    // ================= ĐẶT MÁY =================
    private void bookingFlow(User user) {

        int categoryId = chooseCategory();

        List<PC> list = pcDAO.getAllPCs();

        System.out.println("\n--- DANH SÁCH MÁY AVAILABLE ---");

        for (PC pc : list) {
            if (pc.getCategoryId() == categoryId && pc.getStatus().equals("AVAILABLE")) {

                String category = (categoryId == 1) ? "STANDARD" : "VIP";

                System.out.printf("ID: %d | %s | %.0f | %s\n",
                        pc.getId(),
                        pc.getPcName(),
                        pc.getPricePerHour(),
                        category);
            }
        }

        int pcId;

        // ===== CHỈ CHỌN AVAILABLE =====
        while (true) {
            System.out.print("Chọn ID máy: ");
            try {
                pcId = Integer.parseInt(scanner.nextLine());

                int finalPcId = pcId;

                boolean valid = list.stream().anyMatch(pc ->
                        pc.getId() == finalPcId &&
                                pc.getCategoryId() == categoryId &&
                                pc.getStatus().equals("AVAILABLE")
                );

                if (valid) break;

            } catch (Exception e) {}

            System.out.println("ID không hợp lệ hoặc máy không khả dụng!");
        }

        LocalDateTime start;
        LocalDateTime end;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // ===== START =====
        while (true) {
            try {
                System.out.print("Nhập giờ bắt đầu (yyyy-MM-dd HH:mm): ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                start = LocalDateTime.parse(input, formatter);

                if (start.isBefore(LocalDateTime.now())) {
                    System.out.println("Không được chọn giờ quá khứ!");
                    continue;
                }

                break;

            } catch (Exception e) {
                System.out.println("Sai định dạng!");
            }
        }

        // ===== END =====
        while (true) {
            try {
                System.out.print("Nhập giờ kết thúc (yyyy-MM-dd HH:mm): ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                end = LocalDateTime.parse(input, formatter);

                if (!end.isAfter(start)) {
                    System.out.println("Giờ kết thúc phải sau giờ bắt đầu!");
                    continue;
                }

                break;

            } catch (Exception e) {
                System.out.println("Sai định dạng!");
            }
        }

        // ===== CHECK TRÙNG =====
        if (bookingDAO.isPCBooked(pcId, start, end)) {
            System.out.println("Máy đã có người đặt!");
            return;
        }

        Booking booking = new Booking(user.getId(), pcId, start, end);

        int bookingId = bookingDAO.createBooking(booking);

        if (bookingId != -1) {
            System.out.println("Đặt máy thành công - ID: " + bookingId);

            int orderId = orderDAO.createOrder(bookingId);
            orderFood(orderId, bookingId);

        } else {
            System.out.println("Đặt máy thất bại");
        }
    }

    // ================= GỌI MÓN =================
    private void orderFood(int orderId, int bookingId) {

        List<Food> list = foodDAO.getAllFoods();

        double foodTotal = 0;

        System.out.println("\n--- MENU ĐỒ ĂN ---");

        for (Food f : list) {
            System.out.printf("ID: %d | %s | %.0f\n",
                    f.getId(),
                    f.getName(),
                    f.getPrice());
        }

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

        while (true) {

            int foodId;

            while (true) {
                System.out.print("Chọn ID món: ");
                try {
                    foodId = Integer.parseInt(scanner.nextLine());

                    int finalFoodId = foodId;
                    boolean exists = list.stream().anyMatch(f -> f.getId() == finalFoodId);
                    if (exists) break;

                } catch (Exception e) {}

                System.out.println("ID không hợp lệ!");
            }

            int qty;

            // ===== VALIDATE SỐ LƯỢNG =====
            while (true) {
                System.out.print("Số lượng: ");
                try {
                    qty = Integer.parseInt(scanner.nextLine());
                    if (qty > 0) break;
                } catch (Exception e) {}

                System.out.println("Số lượng không hợp lệ!");
            }

            double price = 0;

            for (Food f : list) {
                if (f.getId() == foodId) {
                    price = f.getPrice();
                    break;
                }
            }
            FoodDAO foodDAO = new FoodDAO();

            boolean updated = foodDAO.decreaseStock(foodId, qty);

            if (!updated) {
                System.out.println("Không đủ hàng trong kho!");
                continue;
            }

            orderDetailDAO.addFood(orderId, foodId, qty, price);

            foodTotal += price * qty;

            while (true) {
                System.out.print("Tiếp tục gọi món? (Y/N): ");
                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("Y")) {
                    break;
                } else if (choice.equalsIgnoreCase("N")) {

                    double pcPrice = bookingDAO.calculatePCPrice(bookingId);

                    double total = foodTotal + pcPrice;

                    System.out.println("\n===== HÓA ĐƠN =====");
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Tiền máy: " + pcPrice);
                    System.out.println("Tiền đồ ăn: " + foodTotal);
                    System.out.println("TỔNG: " + total);

                    return;

                } else {
                    System.out.println("Chỉ nhập Y hoặc N!");
                }
            }
        }
    }

    // ================= XEM BOOKING =================
    private void showMyBookings(User user) {

        List<Booking> list = bookingDAO.getByUser(user.getId());

        System.out.println("\n--- ĐƠN CỦA TÔI ---");

        for (Booking b : list) {
            System.out.println("ID: " + b.getId() + " | Trạng thái: " + b.getStatus());
        }
    }

    // ================= SỐ DƯ =================
    private void showBalance(User user) {

        double balance = userDAO.getBalance(user.getId());

        System.out.println("\n--- TÀI KHOẢN ---");
        System.out.println("Số dư: " + balance);
    }
}