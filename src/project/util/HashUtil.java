package project.util;

import java.security.MessageDigest;

public class HashUtil {

    public static String hashPassword(String password) {

        if (password == null) {
            return "";
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < hashBytes.length; i++) {
                result.append(String.format("%02x", hashBytes[i]));
            }

            return result.toString();

        } catch (Exception e) {
            System.out.println("Lỗi mã hóa mật khẩu");
            return "";
        }
    }
}