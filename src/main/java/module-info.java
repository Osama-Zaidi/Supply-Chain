module com.example.supp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supp to javafx.fxml;
    exports com.example.supp;
}