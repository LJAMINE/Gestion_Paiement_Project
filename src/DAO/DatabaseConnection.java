package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class DatabaseConnection {
//
//
//    private static DatabaseConnection instance;
//
//    private DatabaseConnection() {
//    }
//
//    public static DatabaseConnection getInstance() {
//        if (instance == null) {
//            instance = new DatabaseConnection();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/gestion_paiement_project";
//        String user = "root";
//        String password = "";
//        return DriverManager.getConnection(url, user, password);
//    }
//}

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_paiement_project";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConnection() {
        // Optionally load the JDBC driver here, if needed
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}