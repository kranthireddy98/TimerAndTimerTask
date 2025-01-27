import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseUtil {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=DBName";
    private static final String USER = "sa";
    private static final String PASSWORD = "Pass";

    private DatabaseUtil() {

    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (ClassNotFoundException e){
            System.err.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
            return null;
        }
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

}
