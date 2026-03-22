package session11.ex5.BUSINESS;
import session11.ex5.DAO.DoctorDAO;
import session11.ex5.MODEL.Doctor;

import java.util.List;
import java.util.Scanner;

public class DoctorService {

    private DoctorDAO dao = new DoctorDAO();
    private Scanner sc = new Scanner(System.in);

    // ===== SHOW =====
    public void showDoctors() {
        List<Doctor> list = dao.getAllDoctors();

        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }

        for (Doctor d : list) {
            System.out.println(
                    d.getId() + " | " + d.getName() + " | " + d.getSpecialty()
            );
        }
    }

    // ===== ADD =====
    public void addDoctor() {
        try {
            System.out.print("Nhập ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập tên: ");
            String name = sc.nextLine();

            System.out.print("Nhập chuyên khoa: ");
            String spec = sc.nextLine();

            if (name.isEmpty() || spec.isEmpty()) {
                System.out.println("Không được để trống!");
                return;
            }

            Doctor d = new Doctor(id, name, spec);

            if (dao.insertDoctor(d)) {
                System.out.println("Thêm thành công!");
            }

        } catch (NumberFormatException e) {
            System.out.println("ID phải là số!");
        }
    }

    // ===== STAT =====
    public void statistic() {
        dao.countBySpecialty();
    }
}