package session1.ex2;
import java.util.Scanner;

public class ex2 {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Nhap tong so nguoi: ");
            int tongSoNguoi = sc.nextInt();
            System.out.println("Nhap so nhom: ");
            int soNhom = sc.nextInt();
            double ketQua = tongSoNguoi/soNhom;
            System.out.println("ket qua: "+ ketQua);
        } catch ( ArithmeticException  e) {
            System.out.println("khong the chia cho 0");
        }
        System.out.println("Chuong trinh tiep tuc chay");
    }
}