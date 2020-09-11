package dao;

import domain.ProductRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBoperations {
    private final String addNewProduct = "INSERT INTO `cashiersdb`.`order`(`description`,`cost`,`qty`,`ordernumb`) VALUES (?,?,?,?);";
    private final String findOrder = "SELECT `description`,`cost`,`qty` FROM `order` WHERE (ordernumb = ?);";
    public void addProductRequest(ProductRequest productRequest, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(addNewProduct);
        statement.setString(1, productRequest.getDescription());
        statement.setDouble(2, productRequest.getCost());
        statement.setDouble(3, productRequest.getQty());
        statement.setString(4, productRequest.getTransactionId());
        System.out.println(statement);
        statement.executeUpdate();
    }

    public List<ProductRequest> openPreviousOrder(String orderId, Connection connection) throws SQLException {
        List<ProductRequest> olderOrder = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(findOrder);
        statement.setString(1, orderId);
        ResultSet result;
        result = statement.executeQuery();
        while (result.next()) {
             ProductRequest request = ProductRequest.builder()
                    .withDescription(result.getString(1))
                    .withQty(result.getInt(3))
                    .withCost(result.getDouble(2))
                    .withTransactionId(orderId)
                    .build();
            olderOrder.add(request);
        }
        return olderOrder;
    }
}
