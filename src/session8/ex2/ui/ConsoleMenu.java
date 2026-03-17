package session8.ex2.ui;

import session8.ex2.adapter.ThermometerAdapter;
import session8.ex2.facade.SmartHomeFacade;
import session8.ex2.sensor.OldThermometer;
import session8.ex2.sensor.TemperatureSensor;

import java.util.Scanner;

public class ConsoleMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {

        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor sensor = new ThermometerAdapter(oldThermometer);

        SmartHomeFacade facade = new SmartHomeFacade(sensor);

        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    facade.getCurrentTemperature();
                    break;
                case 2:
                    facade.leaveHome();
                    break;
                case 3:
                    facade.sleepMode();
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
        System.out.println("\n===== MENU =====");
        System.out.println("1. Xem nhiệt độ");
        System.out.println("2. Rời nhà");
        System.out.println("3. Chế độ ngủ");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }
}