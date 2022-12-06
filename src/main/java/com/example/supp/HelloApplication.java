package com.example.supp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class HelloApplication extends Application {
    @FXML
    public static  Group root;
    public static String emailId;
    public static DatabaseConnection connection;

    @Override
    public void start(Stage primaryStage) throws IOException,SQLException {
        emailId="";
        connection = new DatabaseConnection();

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        root = new Group();
        Header header =new Header();
        ProductPage products = new ProductPage();
        ListView<HBox> productList = products.showProducts();
        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productList);
        root.getChildren().addAll(header.root,productPane);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Supply Chain");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e->{
            try{
                connection.con.close();
                System.out.println("connection is closed");
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}