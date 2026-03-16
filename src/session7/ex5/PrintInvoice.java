package session7.ex5;

public class PrintInvoice implements NotificationService {
    public void send(String message) {
        System.out.println("In hóa đơn giấy cho khách");
    }
}