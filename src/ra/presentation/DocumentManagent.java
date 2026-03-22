package ra.presentation;

import ra.business.DocumentBusiness;
import ra.entity.Document;

import javax.print.Doc;
import java.util.Scanner;

public class DocumentManagent {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        DocumentBusiness business = DocumentBusiness.getInstance();

        while (true){
            try {
                System.out.println("***** QUẢN LÝ TÀI LIỆU SỐ *****");
                System.out.println("1. Hiển thị danh sách toàn bộ tài liệu.");
                System.out.println("2. Thêm mới tài liệu.");
                System.out.println("3. Cập nhật thông tin tài liệu theo mã tài liệu.");
                System.out.println("4. Xóa tài liệu theo mã tài liệu.");
                System.out.println("5. Tìm kiếm tài liệu theo tên.");
                System.out.println("6. Lọc danh sách tài liệu phổ biến (Lượt tải >= 1000)");
                System.out.println("7. Sắp xếp danh sách tài liệu giảm dần theo lượt tải.");
                System.out.println("8. Thoát.");
                System.out.print("Lựa chọn của bạn: ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        business.displayAll();
                        break;
                    case 2:
                        while (true) {
                            Document doc = new Document();
                            doc.inputData(scanner);

                            if (!business.addDocument(doc)){
                                System.out.println("Mã tài liệu đã tồn tại");
                                continue;
                            }
                            System.out.println("Thêm thành công.");

                            System.out.print("Nhập tiếp? (y/n): ");
                            if (!scanner.nextLine().equalsIgnoreCase("y")){
                                break;
                            }
                        }
                        break;
                    case 3:
                        System.out.print("Nhập ID: ");
                        business.updateDocument(scanner.nextLine(), scanner);
                        System.out.println("Cập nhật thành công.");
                        break;
                    case 4:
                        System.out.print("Nhập ID: ");
                        business.deleteDocument(scanner.nextLine());
                        System.out.println("Xóa thành công.");
                        break;
                    case 5:
                        System.out.print("Nhập tên: ");
                        business.searchByName(scanner.nextLine());
                    case 6:
                        business.filterPopular();
                        break;
                    case 7:
                        business.sortByDownloads();
                        break;
                    case 8:
                        System.out.println("Thoát chương trình.");
                        System.exit(0);
                    default:
                        System.out.println("Sai lựa chọn.");
                }
            }catch (Exception e){
                System.out.println("Lỗi nhập");
            }
        }
    }
}





















