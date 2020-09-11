package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private final String url;
    private final String user;
    private final String password;

    public DBConnector() {
        this.url = "jdbc:mysql://localhost:3306/cashiersdb";
        this.user = "root";
        this.password = "root";
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
