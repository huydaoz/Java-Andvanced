package session9.kiemtra;
import java.util.Scanner;

public class Main {
    public static void showMenu() {
        System.out.println("\n---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
        System.out.println("1. Thêm mới sản phẩm");
        System.out.println("2. Xem danh sách sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Thoát");
        System.out.println("----------------------------------------------------------------");
        System.out.print("Lựa chọn của bạn: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            showMenu();
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("1. Physical Product");
                    System.out.println("2. Digital Product");
                    int type = Integer.parseInt(sc.nextLine());

                    Product p = ProductFactory.createProduct(type, sc);
                    if (p != null) {
                        db.addProduct(p);
                        System.out.println("Thêm thành công!");
                    }
                    break;

                case 2:
                    for (Product product : db.getAllProducts()) {
                        product.displayInfo();
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID cần sửa: ");
                    String updateId = sc.nextLine();

                    Product updateProduct = db.findById(updateId);
                    if (updateProduct != null) {
                        System.out.print("Tên mới: ");
                        updateProduct.setName(sc.nextLine());

                        System.out.print("Giá mới: ");
                        updateProduct.setPrice(Double.parseDouble(sc.nextLine()));

                        System.out.println("Cập nhật thành công!");
                    } else {
                        System.out.println("Không tìm thấy sản phẩm!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập ID cần xóa: ");
                    String deleteId = sc.nextLine();
                    db.deleteProduct(deleteId);
                    System.out.println("Xóa thành công!");
                    break;

                case 5:
                    System.out.println("Thoát...");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}