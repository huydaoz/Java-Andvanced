package session1.ex5;

import session1.ex3.User;

public class ex5 {
    static void main(String[] args) {
        User user = new User(13);
        try {
            user.setAge(-2);
        } catch (InvalidAgeException e) {
            System.out.println("Loi: " +e.getMessage());
        }
        System.out.println("Chuong trinh tiep tuc chay");
    }
}