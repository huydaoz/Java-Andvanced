package session13.ex4.presentation;

import session13.ex4.dao.DashboardDao;
import session13.ex4.model.BenhNhanDTO;
import session13.ex4.model.DichVu;

import java.util.List;

public class MainDashboard {
    public static void main(String[] args) {
        System.out.println("Đang kết nối tải dữ liệu Dashboard Y Tá Trưởng...");
        long startTime = System.currentTimeMillis();

        DashboardDao dao = new DashboardDao();
        List<BenhNhanDTO> dashboardData = dao.getDashboardData();

        long endTime = System.currentTimeMillis();

        System.out.println("==================================================");
        System.out.println("   DASHBOARD KHOA CẤP CỨU - RIKKEI HOSPITAL");
        System.out.println("==================================================");

        for (BenhNhanDTO p : dashboardData) {
            System.out.println("\n[BN] " + p.getTenBenhNhan() + " (Mã: " + p.getMaBenhNhan() + ")");
            List<DichVu> dvList = p.getDsDichVu();

            if (dvList.isEmpty()) {
                System.out.println("   -> (Chưa có dịch vụ/thuốc nào được kê)");
            } else {
                for (DichVu dv : dvList) {
                    System.out.println("   " + dv.toString());
                }
            }
        }

        System.out.println("\n==================================================");
        System.out.println(">> Tải xong " + dashboardData.size() + " bệnh nhân.");
        System.out.println(">> Thời gian phản hồi (Response Time): " + (endTime - startTime) + " ms");
    }
}