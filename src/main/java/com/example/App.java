import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String userInput = scanner.nextLine();

        // ❌ SQL Injection vulnerability
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "root", "password");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

        } catch (Exception e) {
            // ❌ Poor exception handling (Sonar will flag this)
            e.printStackTrace();
        }

        // ❌ Hardcoded credentials
        String password = "admin123";
        System.out.println("Password is: " + password);

        // ❌ Resource leak (scanner not closed)
    }
}
