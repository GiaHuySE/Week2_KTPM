module com.example.javafxmessagequeue {
    requires javafx.controls;
    requires javafx.fxml;
    requires  activemq.all;
    requires java.naming;


    opens com.example.javafxmessagequeue to javafx.fxml;
    exports com.example.javafxmessagequeue;
}