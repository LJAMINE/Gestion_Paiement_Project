package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
//    private static DatabaseConnection instance;
//    private Connection connection;
//
//    private static final String URL = "jdbc:mysql://localhost:3306/Gestion_Paiement_Project";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
//
//    private DatabaseConnection() throws SQLException {
//        connection = DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    public static DatabaseConnection getInstance() throws SQLException {
//        if (instance == null) {
//            instance = new DatabaseConnection();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }



    private static DatabaseConnection instance;

    private DatabaseConnection() {}

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
        String password = ""; // Or your password
        return DriverManager.getConnection(url, user, password);
    }
}