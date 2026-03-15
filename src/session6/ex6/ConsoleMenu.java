package session6.ex6;
import java.util.Scanner;

public class ConsoleMenu {

    private CinemaManager manager =
            new CinemaManager();

    public void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Xem thống kê");
            System.out.println("3. Dừng mô phỏng");
            System.out.println("4. Thoát");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Số phòng: ");
                    int rooms = sc.nextInt();

                    System.out.print("Vé/phòng: ");
                    int capacity = sc.nextInt();

                    System.out.print("Số quầy: ");
                    int counters = sc.nextInt();

                    manager.init(rooms, capacity, counters);

                    break;

                case 2:

                    manager.statistics();

                    break;

                case 3:

                    manager.stopSimulation();

                    break;

                case 4:

                    manager.stopSimulation();
                    return;
            }
        }
    }
}