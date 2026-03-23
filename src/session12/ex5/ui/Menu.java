package session12.ex5.ui;

import session12.ex5.model.Patient;
import session12.ex5.service.PatientService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static PatientService service = new PatientService();

    public static void start() {
        int choice;

        do {
            System.out.println("\n===== RHMS MENU =====");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Tiếp nhận bệnh nhân mới");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & Tính phí");
            System.out.println("5. Thoát chương trình.");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    showPatients();
                    break;
                case 2:
                    addPatient();
                    break;
                case 3:
                    updateDisease();
                    break;
                case 4:
                    discharge();
                    break;
            }

        } while (choice != 5);
    }

    static void showPatients() {
        List<Patient> list = service.getAllPatients();
        for (Patient p : list) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getAge() + " | " + p.getDepartment());
        }
    }

    static void addPatient() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Department: ");
        String dept = sc.nextLine();

        service.addPatient(name, age, dept);
    }

    static void updateDisease() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Disease: ");
        String dis = sc.nextLine();

        service.updateDisease(id, dis);
    }

    static void discharge() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());

        service.discharge(id);
    }
}