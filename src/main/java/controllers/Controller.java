package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.DBConnector;
import dao.DBoperations;
import domain.ProductRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.decimal4j.util.DoubleRounder;

public class Controller {
    Double creditLimit = 0.00;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField productName;

    @FXML
    private TextField priceField;

    @FXML
    private TextField qtyField;

    @FXML
    private Button addToListBtn;

    @FXML
    private TextField creditLimitField;

    @FXML
    private Button setLimitBtn;

    @FXML
    private Text creditLimitText;

    @FXML
    private Text orderTotalText;

    @FXML
    private TextArea orderList;

    @FXML
    private Rectangle indicator;

    @FXML
    private TextField orderIdField;

    @FXML
    private Button newOrderBtn;

    @FXML
    private Button openOrderBtn;

    public Controller() {
    }

    @FXML
    void initialize() {
        DBoperations dBoperations = new DBoperations();
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();
        List<ProductRequest> requests= new ArrayList<ProductRequest>();


        addToListBtn.setOnAction(event -> {
            try {
                ProductRequest newRequest = ProductRequest.builder()
                        .withDescription(productName.getText())
                        .withQty(Integer.parseInt(qtyField.getText()))
                        .withCost(Double.parseDouble(priceField.getText()))
                        .withTransactionId(orderIdField.getText())
                        .build();
                requests.add(newRequest);
                dBoperations.addProductRequest(newRequest,connection);
                UpdateFields(requests);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        setLimitBtn.setOnAction(event -> {
            creditLimit=  Double.parseDouble(creditLimitField.getText());
            creditLimitText.setText(creditLimit.toString()+"$");
        });

        newOrderBtn.setOnAction(event ->{
            creditLimit=0.0;
            creditLimitText.setText(creditLimit.toString()+"$");
            requests.clear();
            orderTotalText.setText("0$");
            orderList.clear();
            orderIdField.clear();
        });

        openOrderBtn.setOnAction(event -> {
            try {
                List<ProductRequest> pulledInfo = dBoperations.openPreviousOrder(orderIdField.getText(),connection);
                UpdateFields(pulledInfo);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    private void UpdateFields(List<ProductRequest> requests) {
        orderTotalText.setText(updateTotalCost(requests)+"$");
        orderList.setText(updateTotalList(requests));
        if (checkCreditAvailability(updateTotalCost(requests),creditLimit)) {indicator.setFill(Paint.valueOf("#1fff3d"));}
        else {indicator.setFill(Paint.valueOf("#ff2121"));}
        productName.clear();
        qtyField.clear();
        priceField.clear();
    }

    private boolean checkCreditAvailability(String totalAmount, Double creditLimit) {
    Double currentTotal = Double.parseDouble(totalAmount);
    if (currentTotal<creditLimit) return true;
    else return false;
    }

    private String updateTotalList(List<ProductRequest> requests) {
       StringBuilder totalList = new StringBuilder();
        for (ProductRequest request : requests) {
            totalList.append(request.getDescription())
                    .append("   ")
                    .append("Cost=")
                    .append(request.getCost())
                    .append("$")
                    .append("   ")
                    .append("Q-ty=")
                    .append(request.getQty())
                    .append("\n");
        }
        return totalList.toString();
    }

    private String updateTotalCost(List<ProductRequest> requests) {
        Double updatedTotalCost=0.00;
        for (ProductRequest request : requests) {
            updatedTotalCost += DoubleRounder.round((request.getCost() * request.getQty()),2);
        }
        return updatedTotalCost.toString();
    }
}
