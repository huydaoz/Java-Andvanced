package session8.ex4.ui;

import session8.ex4.observer.*;

import java.util.Scanner;

public class ConsoleMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {

        TemperatureSensor sensor = new TemperatureSensor();

        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerDevice(sensor);
                    break;
                case 2:
                    setTemperature(sensor);
                    break;
                case 0:
                    System.out.println("Thoát.");
                    break;
                default:
                    System.out.println("Không hợp lệ.");
            }

        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\n===== OBSERVER MENU =====");
        System.out.println("1. Đăng ký thiết bị");
        System.out.println("2. Set nhiệt độ");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }

    private static void registerDevice(TemperatureSensor sensor) {

        System.out.println("Chọn thiết bị:");
        System.out.println("1. Quạt");
        System.out.println("2. Máy tạo ẩm");

        int type = scanner.nextInt();

        switch (type) {
            case 1:
                sensor.attach(new Fan());
                System.out.println("Quạt: Đã đăng ký nhận thông báo");
                break;
            case 2:
                sensor.attach(new Humidifier());
                System.out.println("Máy tạo ẩm: Đã đăng ký");
                break;
            default:
                System.out.println("Không hợp lệ.");
        }
    }

    private static void setTemperature(TemperatureSensor sensor) {
        System.out.print("Nhập nhiệt độ: ");
        int temp = scanner.nextInt();
        sensor.setTemperature(temp);
    }
}