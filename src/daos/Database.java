package daos;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class Database {
    public static Connection getDatabaseConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "";


        return DriverManager.getConnection(url, user, password);
    }
}
