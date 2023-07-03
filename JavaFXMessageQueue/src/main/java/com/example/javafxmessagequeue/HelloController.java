package com.example.javafxmessagequeue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import model.EmployeeSender;
import org.apache.activemq.ActiveMQMessageProducer;

import javax.jms.JMSException;
import java.io.IOException;

public class HelloController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private Button sendButton,btnSceen2;

    private EmployeeSender employeeSender;

    @FXML
    private void initailize(){
        employeeSender = new EmployeeSender();
    }

    @FXML
    private void handleSendButton() throws JMSException, IOException {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        Employee employee = new Employee(name,age);
        employeeSender = new EmployeeSender();
        employeeSender.sendMessage(employee);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceen2.fxml"));
        Stage windown = (Stage) btnSceen2.getScene().getWindow();
        Parent root = fxmlLoader.load();
        windown.setScene(new Scene(root));
    }

    @FXML
    private void switchToSceen2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceen2.fxml"));
        Stage windown = (Stage) btnSceen2.getScene().getWindow();
        Parent root = fxmlLoader.load();
        windown.setScene(new Scene(root));
    }
}