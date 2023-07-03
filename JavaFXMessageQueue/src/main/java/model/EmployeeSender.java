package model;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class EmployeeSender {
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    static String queueName = "MESSAGE_QUEUE";

    public void sendMessage(Employee employee) throws JMSException{
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("employeQueue");
        producer = session.createProducer(destination);

        ObjectMessage message = session.createObjectMessage();
        message.setObject(employee);
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
    }
}
