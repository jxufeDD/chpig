module com.example.egguser {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.egguser to javafx.fxml;
    exports com.example.egguser;
}