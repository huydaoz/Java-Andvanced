package session8.ex5.ui;

import session8.ex5.command.*;
import session8.ex5.device.*;
import session8.ex5.observer.*;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {

        // Device
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        // Sensor (Observer)
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(fan);
        sensor.attach(ac);

        // Command (Macro)
        Command sleepCommand = new SleepModeCommand(Arrays.asList(
                new LightOffCommand(light),
                new ACSetTempCommand(ac, 28),
                new FanLowCommand(fan)
        ));

        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    sleepCommand.execute();
                    break;
                case 2:
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = scanner.nextInt();
                    sensor.setTemperature(temp);
                    break;
                case 3:
                    fan.showStatus();
                    ac.showStatus();
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
        System.out.println("\n===== SMART SLEEP =====");
        System.out.println("1. Kích hoạt chế độ ngủ");
        System.out.println("2. Thay đổi nhiệt độ");
        System.out.println("3. Xem trạng thái");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }
}