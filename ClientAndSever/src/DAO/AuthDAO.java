//package DAO;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.util.Base64;
//
//public class PasswordUtil {
//
//    // Tạo salt
//    public static String getSalt() throws NoSuchAlgorithmException {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return Base64.getEncoder().encodeToString(salt);
//    }
//
//    // Mã hóa mật khẩu với salt
//    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(Base64.getDecoder().decode(salt));
//        byte[] hashedPassword = md.digest(password.getBytes());
//        return Base64.getEncoder().encodeToString(hashedPassword);
//    }
//
//    // Kiểm tra mật khẩu
//    public static boolean checkPassword(String password, String hashedPassword, String salt) throws NoSuchAlgorithmException {
//        String hashedInput = hashPassword(password, salt);
//        return hashedInput.equals(hashedPassword);
//    }
//}
package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {
    private static final String DB_URL = "jdbc:sqlserver://DESKTOP-0CGBO3O\\SQLEXPRESS:1433;databaseName=Game";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "12345";

    public static boolean authenticate(String username, String password) {
        String hashedPassword = hashPassword(password);
        String query = "SELECT * FROM NewAcc WHERE username = ? AND pass = ?";
        
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, hashedPassword);
            
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
