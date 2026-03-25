package session14.kiemtra;

import session14.kiemtra.dao.AccountDAO;
import session14.kiemtra.service.TransferService;

public class Main {

    public static void main(String[] args) {

        TransferService service = new TransferService();
        AccountDAO dao = new AccountDAO();

        String from = "ACC01";
        String to = "ACC02";
        double amount = 1000;

        System.out.println("===== TRƯỚC KHI CHUYỂN =====");
        dao.printAccount(from);
        dao.printAccount(to);

        service.transfer(from, to, amount);

        System.out.println("\n===== SAU KHI CHUYỂN =====");
        dao.printAccount(from);
        dao.printAccount(to);
    }
}