package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
 

    private static DatabaseConnection instance;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        // Always return a NEW connection, never reuse!
        String url = "jdbc:mysql://localhost:3306/gestion_paiement_project";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}