import DAO.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            if (conn != null) {
                System.out.println("Connection successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}