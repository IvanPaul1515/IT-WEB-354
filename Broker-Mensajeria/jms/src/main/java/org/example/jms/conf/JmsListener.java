package org.example.jms.conf;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsListener {

    private final SimpMessagingTemplate messagingTemplate;

    public JmsListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void iniciarListener() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://activemq:61616");
        String queueName = "notificacion_sensores";

        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue queue = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(queue);

            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage textMessage) {
                    String json = null;
                    try {
                        json = textMessage.getText();
                        // Envía el mensaje JSON a través de WebSocket
                        messagingTemplate.convertAndSend("/topic/greetings", json);
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Received message of unexpected type: " + message.getClass().getName());
                }
            });

            System.out.println("Waiting for sensor data...");
            while (true) {

            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
