package se.lexicon;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "your_password";
        return DriverManager.getConnection(url, username, password);
    }
}
