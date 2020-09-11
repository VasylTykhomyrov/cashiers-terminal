package provider;


import dao.DBConnector;
import dao.TablesCreation;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseProvider extends Thread{
    private final TablesCreation tablesCreation;
    private final DBConnector dbConnector;

    public DatabaseProvider(TablesCreation tablesCreation,
                            DBConnector dbConnector) {
        this.tablesCreation = tablesCreation;
        this.dbConnector = dbConnector;
    }
    public void run() {
        try {
            Connection connection = dbConnector.getConnection();
            tablesCreation.createTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
