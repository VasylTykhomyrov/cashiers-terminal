package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TablesCreation {
    private final String DROP_TABLE = "DROP TABLE `cashiersdb`.`order`;";
    private final String CREATE_ORDER_TABLE = "CREATE TABLE `cashiersdb`.`order` (\n" +
            "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `description` VARCHAR(45) NOT NULL,\n" +
            "  `cost` DOUBLE NOT NULL,\n" +
            "  `qty` INT(11) NOT NULL,\n" +
            "  `ordernumb` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8mb4\n" +
            "COLLATE = utf8mb4_0900_ai_ci;";


    public void createTables(Connection connection) throws SQLException {
        try {
            Statement statement = null;
            statement = connection.createStatement();

                statement.executeUpdate(DROP_TABLE);
                statement.executeUpdate(CREATE_ORDER_TABLE);

        } catch (SQLException e) {
            throw new SQLException("Couldn't create tables from file", e);
        }
    }
}
