package com.example.javafxmessagequeue;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.Employee;
import model.EmployeeReceiver;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sceen2Controller {
    private EmployeeReceiver employeeReceiver;

    @FXML
    private TextArea txtArea;

    private  ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    @FXML
    public void receiveMessage() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connectionFactory.setTrustAllPackages(true);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("employeQueue");

        consumer  = session.createConsumer(destination);
        consumer.setMessageListener(message -> {
            if (message instanceof  ObjectMessage){
                try{
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    Employee employee = (Employee) objectMessage.getObject();
                    txtArea.appendText(employee.toString() + "\n");
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
