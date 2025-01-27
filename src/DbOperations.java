import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimerTask;

public class DbOperations extends TimerTask {

    @Override
    public void run() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            System.out.println("Connected to he database!");

            String fetchMaxIdQuery = "SELECT ISNULL(MAX(studid),0) as maxId FROM DBO.STUDENTS";

            int maxId = 0;
            try (PreparedStatement fetchMaxIdstmt = connection.prepareStatement(fetchMaxIdQuery);
                 ResultSet resultset = fetchMaxIdstmt.executeQuery()) {
                if (resultset.next()) {
                    maxId = resultset.getInt("maxId");
                }
            }
            int newId = maxId + 1;

            String insertQuery = "INSERT INTO DBO.STUDENTS(studid,studname) VALUES (?,?)";

            try (PreparedStatement insertsmt = connection.prepareStatement(insertQuery)) {
                insertsmt.setInt(1, newId);
                insertsmt.setString(2, "raghu");

                int rowsInserted = insertsmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Inserted row with ID " + newId + "at" + new java.util.Date());

                }
            }
        } catch (SQLException e) {
            System.err.println("Database operation failed: " + e.getMessage());
        }
    }
}
