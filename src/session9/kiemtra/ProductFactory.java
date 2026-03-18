package session9.kiemtra;

import java.util.Scanner;

class ProductFactory {
    public static Product createProduct(int type, Scanner sc){
        System.out.print("Nhập ID: ");
        String id = sc.nextLine();

        System.out.print("Nhập Name: ");
        String name = sc.nextLine();

        System.out.print("Nhập Price: ");
        double price = Double.parseDouble(sc.nextLine());
        if (type == 1) {
            System.out.print("Nhập Weight: ");
            double weight = Double.parseDouble(sc.nextLine());
            return new PhysicalProduct(id, name, price, weight);
        } else if (type == 2) {
            System.out.print("Nhập Size (MB): ");
            double size = Double.parseDouble(sc.nextLine());
            return new DigitalProduct(id, name, price, size);
        }
        return null;
    }
}
