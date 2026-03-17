package session8.ex1.ui;
import session8.ex1.connection.HardwareConnection;
import session8.ex1.device.Device;
import session8.ex1.factory.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Device> deviceList = new ArrayList<>();

    public static void start() {
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    connectHardware();
                    break;
                case 2:
                    createDevice();
                    break;
                case 3:
                    turnOnDevice();
                    break;
                case 4:
                    turnOffDevice();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Kết nối phần cứng");
        System.out.println("2. Tạo thiết bị");
        System.out.println("3. Bật thiết bị");
        System.out.println("4. Tắt thiết bị");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }

    private static void connectHardware() {
        HardwareConnection.getInstance().connect();
    }

    private static void createDevice() {
        System.out.println("Chọn loại thiết bị:");
        System.out.println("1. Đèn");
        System.out.println("2. Quạt");
        System.out.println("3. Điều hòa");

        int type = scanner.nextInt();
        DeviceFactory factory = null;

        switch (type) {
            case 1:
                factory = new LightFactory();
                break;
            case 2:
                factory = new FanFactory();
                break;
            case 3:
                factory = new AirConditionerFactory();
                break;
            default:
                System.out.println("Không hợp lệ.");
                return;
        }

        Device device = factory.createDevice();
        deviceList.add(device);
    }

    private static void turnOnDevice() {
        if (deviceList.isEmpty()) {
            System.out.println("Chưa có thiết bị.");
            return;
        }

        showDevices();
        System.out.print("Chọn thiết bị: ");
        int index = scanner.nextInt();

        if (index >= 1 && index <= deviceList.size()) {
            deviceList.get(index - 1).turnOn();
        } else {
            System.out.println("Không hợp lệ.");
        }
    }

    private static void turnOffDevice() {
        if (deviceList.isEmpty()) {
            System.out.println("Chưa có thiết bị.");
            return;
        }

        showDevices();
        System.out.print("Chọn thiết bị: ");
        int index = scanner.nextInt();

        if (index >= 1 && index <= deviceList.size()) {
            deviceList.get(index - 1).turnOff();
        } else {
            System.out.println("Không hợp lệ.");
        }
    }

    private static void showDevices() {
        System.out.println("Danh sách thiết bị:");
        for (int i = 0; i < deviceList.size(); i++) {
            System.out.println((i + 1) + ". Device " + (i + 1));
        }
    }
}