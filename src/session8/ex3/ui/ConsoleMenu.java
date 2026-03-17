package session8.ex3.ui;

import session8.ex3.command.*;
import session8.ex3.device.*;
import session8.ex3.remote.RemoteControl;

import java.util.Scanner;

public class ConsoleMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {

        RemoteControl remote = new RemoteControl();

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    assignCommand(remote, light, fan, ac);
                    break;
                case 2:
                    System.out.print("Nhấn nút: ");
                    int slot = scanner.nextInt();
                    remote.pressButton(slot);
                    break;
                case 3:
                    remote.undo();
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
        System.out.println("\n===== REMOTE =====");
        System.out.println("1. Gán nút");
        System.out.println("2. Nhấn nút");
        System.out.println("3. Undo");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }

    private static void assignCommand(RemoteControl remote, Light light, Fan fan, AirConditioner ac) {

        System.out.println("Chọn nút:");
        int slot = scanner.nextInt();

        System.out.println("Chọn lệnh:");
        System.out.println("1. Light ON");
        System.out.println("2. Light OFF");
        System.out.println("3. Fan ON");
        System.out.println("4. Fan OFF");
        System.out.println("5. Set AC Temp");

        int type = scanner.nextInt();

        Command command = null;

        switch (type) {
            case 1:
                command = new LightOnCommand(light);
                break;
            case 2:
                command = new LightOffCommand(light);
                break;
            case 3:
                command = new FanOnCommand(fan);
                break;
            case 4:
                command = new FanOffCommand(fan);
                break;
            case 5:
                System.out.print("Nhập nhiệt độ: ");
                int temp = scanner.nextInt();
                command = new ACSetTemperatureCommand(ac, temp);
                break;
        }

        if (command != null) {
            remote.setCommand(slot, command);
        }
    }
}