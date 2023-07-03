package model;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class EmployeeReceiver {
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private  MessageConsumer consumer;
    public void receiveMessage() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("employeQueue");

        consumer  = session.createConsumer(destination);
        while (true){
            Message message = consumer.receive();
            if(message instanceof ObjectMessage){
                ObjectMessage objectMessage = (ObjectMessage) message;
                Employee employee = (Employee) objectMessage.getObject();
            }
        }
    }
}
