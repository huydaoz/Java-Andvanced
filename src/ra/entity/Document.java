package ra.entity;

import java.util.Scanner;

public class Document {
    private String documentId;
    private String documentName;
    private double fileSize;
    private  int downloads;

    public Document(){
    }

    public Document(String documentId, String documentName, double fileSize, int downloads){
        this.documentId = documentId;
        this.documentName = documentName;
        this.fileSize = fileSize;
        this.downloads = downloads;
    }

    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId){
        this.documentId = documentId;
    }

    public String getDocumentName(){
        return documentName;
    }

    public void setDocumentName(String documentName){
        this.documentName = documentName;
    }

    public double getFileSize(){
        return fileSize;
    }

    public void setFileSize(double fileSize){
        this.fileSize = fileSize;
    }

    public int getDownloads(){
        return downloads;
    }

    public void setDownloads(int downloads){
        this.downloads = downloads;
    }

    public void inputData(Scanner scanner){
        System.out.print("Nhập mã tài liệu: ");
        this.documentId = scanner.nextLine();

        System.out.print("Nhập tên tài liệu: ");
        this.documentName = scanner.nextLine();

        while (true){
            try{
                System.out.print("Nhập dung lượng file: (MB > 0): ");
                this.fileSize = Double.parseDouble(scanner.nextLine());
                if (fileSize > 0){
                    break;
                }
                System.out.println("Dung lượng phải > 0");
            }catch (Exception e){
                System.out.println("Nhập sai định dạng");
            }
        }
        while (true){
            try{
                System.out.print("Nhập lượt tải (>=0): ");
                this.downloads = Integer.parseInt(scanner.nextLine());
                if (downloads >= 0){
                    break;
                }
                System.out.println("Lượt tải phải >= 0");
            }catch (Exception e){
                System.out.println("Nhập sai định dạng");
            }
        }
    }
    public void displayData(){
        System.out.printf("%-10s %-20s %-10.2f %-10d\n", documentId, documentName, fileSize, downloads);
    }
}





























