package project.presentation;

import project.dao.BookingDAO;
import project.dao.OrderDAO;
import project.dao.UserDAO;
import project.model.Booking;

import java.util.List;
import java.util.Scanner;

public class StaffUI {

    private BookingDAO bookingDAO = new BookingDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();

    private Scanner scanner = new Scanner(System.in);

    // ================= MENU =================
    public void showMenu() {

        while (true) {
            System.out.println("\n===== NHÂN VIÊN =====");
            System.out.println("1. Xem danh sách booking");
            System.out.println("2. Cập nhật trạng thái");
            System.out.println("3. Nạp tiền cho khách");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showBookings();
                    break;
                case 2:
                    updateStatus();
                    break;
                case 3:
                    topUpBalance();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    // ================= HIỂN THỊ =================
    private void showBookings() {

        List<Booking> list = bookingDAO.getAll();

        System.out.println("\n===== DANH SÁCH BOOKING =====");
        System.out.printf("%-5s %-15s %-10s %-15s %-10s\n",
                "ID", "USERNAME", "PC", "STATUS", "TOTAL");

        for (Booking b : list) {

            // ===== LẤY USERNAME =====
            String username = userDAO.getUsernameById(b.getUserId());

            // ===== TÍNH TIỀN =====
            double pcPrice = bookingDAO.calculatePCPrice(b.getId());
            double foodPrice = orderDAO.calculateFoodPrice(b.getId());
            double total = pcPrice + foodPrice;

            System.out.printf("%-5d %-15s %-10d %-15s %-10.0f\n",
                    b.getId(),
                    username,
                    b.getPcId(),
                    b.getStatus(),
                    total);
        }
    }

    // ================= VALID WORKFLOW =================
    private boolean isValidTransition(String current, String next) {

        switch (current) {
            case "PENDING":
                return next.equals("CONFIRMED");

            case "CONFIRMED":
                return next.equals("SERVING");

            case "SERVING":
                return next.equals("COMPLETED");

            default:
                return false;
        }
    }

    // ================= UPDATE STATUS =================
    private void updateStatus() {

        System.out.print("Nhập ID booking: ");
        int bookingId = Integer.parseInt(scanner.nextLine());

        String currentStatus = bookingDAO.getStatus(bookingId);

        if (currentStatus == null) {
            System.out.println("Không tìm thấy booking");
            return;
        }

        System.out.println("Trạng thái hiện tại: " + currentStatus);

        System.out.println("Chọn trạng thái mới:");
        System.out.println("1. CONFIRMED");
        System.out.println("2. SERVING");
        System.out.println("3. COMPLETED");

        System.out.print("Chọn: ");
        int choice = Integer.parseInt(scanner.nextLine());

        String newStatus = "";

        switch (choice) {
            case 1:
                newStatus = "CONFIRMED";
                break;
            case 2:
                newStatus = "SERVING";
                break;
            case 3:
                newStatus = "COMPLETED";
                break;
            default:
                newStatus = "";
        }

        if (newStatus.equals("")) {
            System.out.println("Lựa chọn không hợp lệ");
            return;
        }

        if (!isValidTransition(currentStatus, newStatus)) {
            System.out.println("Không thể chuyển trạng thái");
            return;
        }

        if (newStatus.equals("COMPLETED")) {

            double pcPrice = bookingDAO.calculatePCPrice(bookingId);
            double foodPrice = orderDAO.calculateFoodPrice(bookingId);
            double total = pcPrice + foodPrice;

            int userId = bookingDAO.getUserIdByBooking(bookingId);
            double balance = userDAO.getBalance(userId);

            if (balance < total) {
                System.out.println("Không đủ tiền");
                return;
            }

            double newBalance = balance - total;
            userDAO.updateBalance(userId, newBalance);

            System.out.println("Tổng tiền: " + total);
            System.out.println("Đã trừ tiền");
        }

        boolean result = bookingDAO.updateStatus(bookingId, newStatus);

        if (result) {
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Cập nhật thất bại");
        }
    }

    private void topUpBalance() {

        int userId;

        while (true) {
            System.out.print("Nhập ID user: ");
            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                userId = Integer.parseInt(input);

                double balance = userDAO.getBalance(userId);

                if (userDAO.isUserExists(userId)) break;

            } catch (Exception e) {}

            System.out.println("ID không hợp lệ!");
        }

        double amount;

        while (true) {
            System.out.print("Nhập số tiền nạp: ");
            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Không được để trống!");
                    continue;
                }

                amount = Double.parseDouble(input);

                if (amount > 0) break;

            } catch (Exception e) {}

            System.out.println("Số tiền không hợp lệ!");
        }

        double currentBalance = userDAO.getBalance(userId);

        double newBalance = currentBalance + amount;

        boolean result = userDAO.updateBalance(userId, newBalance);

        if (result) {
            System.out.println("Nạp tiền thành công!");
            System.out.println("Số dư mới: " + newBalance);
        } else {
            System.out.println("Nạp tiền thất bại!");
        }
    }
}