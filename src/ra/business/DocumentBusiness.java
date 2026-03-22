package ra.business;

import ra.entity.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DocumentBusiness {
    private static DocumentBusiness instance;
    private List<Document> documentList;

    private DocumentBusiness(){
        documentList = new ArrayList<>();
    }

    public static DocumentBusiness getInstance(){
        if (instance == null){
            instance = new DocumentBusiness();
        }
        return instance;
    }

    public void displayAll(){
        if (documentList.isEmpty()){
            System.out.println("Danh sách rỗng");
            return;
        }
        System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Size", "Downloads");
        documentList.forEach(Document::displayData);
    }
    public boolean addDocument(Document doc){
        boolean exists = documentList.stream()
                .anyMatch(d -> d.getDocumentId().equals(doc.getDocumentId()));

        if (exists){
            return false;
        }
        documentList.add(doc);
        return true;
    }
    public void updateDocument(String id, Scanner scanner){
        Optional<Document> opt = documentList.stream()
                .filter(d -> d.getDocumentId().equals(id))
                .findFirst();
        if(opt.isEmpty()){
            System.out.println("không tìm thấy");
            return;
        }
        Document doc = opt.get();

        System.out.println("1. Tên");
        System.out.println("2. Dung lượng");
        System.out.println("3. Lượt tải");
        System.out.println("Chọn: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice){
            case 1:
                System.out.print("Tên mới: ");
                doc.setDocumentName(scanner.nextLine());
                break;
            case 2:
                System.out.print("Dung lượng mới:");
                doc.setFileSize(Double.parseDouble(scanner.nextLine()));
                break;
            case 3:
                System.out.print("Lượt tải mới: ");
                doc.setDownloads(Integer.parseInt(scanner.nextLine()));
                break;
            default:
                System.out.println("Không hợp lệ");
        }
    }
    public void deleteDocument(String id){
        boolean removed = documentList.removeIf(d -> d.getDocumentId().equals(id));
        if (!removed){
            System.out.println("Không tồn tại tài liệu");
        }
    }
    public void searchByName(String keyword){
        List<Document> result = documentList.stream()
                .filter(d -> d.getDocumentName().toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()){
            System.out.println("Không tìm thấy");
            return;
        }
        result.forEach(Document::displayData);
        System.out.println("Tổng: " + result.size());
    }

    public void sortByDownloads(){
        List<Document> sorted = documentList.stream()
                .sorted((a,b) -> b.getDownloads() - a.getDownloads())
                .collect(Collectors.toList());

        sorted.forEach(Document::displayData);
    }

    public void filterPopular(){
        List<Document> result = documentList.stream()
                .filter(d -> d.getDownloads() >= 1000)
                .collect(Collectors.toList());

        result.forEach(Document::displayData);
    }
}


































