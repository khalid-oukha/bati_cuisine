package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final DatabaseConfig INSTANCE = new DatabaseConfig();
    private static final String SERVER_NAME = "localhost";
    private static final String PORT_NUMBER = "5432";
    private static final String DB_NAME = "bati_cuisine";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static Connection connection = null;

    private DatabaseConfig() {
    }

    public static DatabaseConfig getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + SERVER_NAME + ":" + PORT_NUMBER + "/" + DB_NAME,
                    USERNAME,
                    PASSWORD
            );
        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = null;
    }
}
