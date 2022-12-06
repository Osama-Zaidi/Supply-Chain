package com.example.supp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ProductPage {
    ListView<HBox> products;

    ListView<HBox> showProductsbyName(String search) throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products = new ListView<>(); //instantiating a listView
        Label name= new Label();
        Label price= new Label();
        Label id= new Label();
        HBox details = new HBox();
        name.setMinWidth(50);
        id.setMinWidth(50);
        price.setMinWidth(50);
        name.setText("Name");
        price.setText("Price");
        id.setText("productID");
        details.getChildren().addAll(name, price, id);
        productList.add(details);
        while (res.next()) {
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())){
                Label productName = new Label();
                Label productPrice = new Label();
                Label productID = new Label();
                Button buy= new Button();
                HBox productDetails = new HBox();
                productName.setMinWidth(50);
                productID.setMinWidth(50);
                productPrice.setMinWidth(50);
                buy.setText("Buy");
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(HelloApplication.emailId.equals("")){
                            Dialog<String> dialog= new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Login first before placing order");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        }
                        else{
                            try{
                                Order place = new Order();
                                place.placeOrder(productID.getText());
                            }
                            catch (SQLException e){
                                e.printStackTrace();
                            }
                            System.out.println("You clicked Buy button!");
                        }
                    }
                });
                productName.setText(res.getString("productName"));
                productPrice.setText(res.getString("price"));
                productID.setText("" + res.getInt("productID"));
                productDetails.getChildren().addAll(productName, productPrice,productID, buy);
                productList.add(productDetails);
            }

        }
        if(productList.size()==1){
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("No such product is available!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }

    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products = new ListView<>(); //instantiating a listView
        Label name= new Label();
        Label price= new Label();
        Label id= new Label();
        HBox details = new HBox();
        name.setMinWidth(50);
        id.setMinWidth(50);
        price.setMinWidth(50);
        name.setText("Name");
        price.setText("Price");
        id.setText("productID");
        details.getChildren().addAll(name, price, id);
        productList.add(details);
        while (res.next()) {
            Label productName = new Label();
            Label productPrice = new Label();
            Label productID = new Label();
            Button buy= new Button();
            HBox productDetails = new HBox();
            productName.setMinWidth(50);
            productID.setMinWidth(50);
            productPrice.setMinWidth(50);
            buy.setText("Buy");
            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailId.equals("")){
                        Dialog<String> dialog= new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Login first before placing order");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else{
                        try{
                            Order place = new Order();
                            place.placeOrder(productID.getText());
                        }
                        catch (SQLException e){
                            e.printStackTrace();
                        }
                        System.out.println("You clicked Buy button!");
                    }
                }
            });
            productName.setText(res.getString("productName"));
            productPrice.setText(res.getString("price"));
            productID.setText("" + res.getInt("productID"));
            productDetails.getChildren().addAll(productName, productPrice,productID, buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}
