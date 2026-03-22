package session11.ex5.PRESENTATION;
import session11.ex5.BUSINESS.DoctorService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DoctorService service = new DoctorService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");

            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    service.showDoctors();
                    break;
                case 2:
                    service.addDoctor();
                    break;
                case 3:
                    service.statistic();
                    break;
                case 4:
                    System.out.println("Thoát...");
                    return;
                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }
}
//    PHẦN 1
//    Lỗi trùng khóa chính
//    Lỗi vượt độ dài cột
//    Lỗi dữ liệu rỗng / sai định dạng
//    Lỗi kết nối DB
//    Lỗi SQL Injection