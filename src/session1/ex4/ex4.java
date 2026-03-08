package session1.ex4;
import java.io.IOException;

public class ex4 {
    public static void saveToFile() throws IOException{
        System.out.println("Dang luu du lieu vao file");
        throw  new IOException("Loi khi ghi file");
    }
    public  static  void processUserData() throws IOException{
        System.out.println("Dang su ly du lieu nguoi dung");
        saveToFile();
    }
    static void main(String[] args) {
        try {
            processUserData();
        }catch (IOException e){
            System.out.println("Da xay ra loi: "+ e.getMessage());
        }
        System.out.println("Chuong trinh van tiep tuc chay");
    }
}