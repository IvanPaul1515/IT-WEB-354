package org.example.sender01.sender;

import jakarta.jms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class MessageSender {

    private final ConnectionFactory factory;
    private final Random random;

    @Autowired
    public MessageSender(@Qualifier("connectionFactory") ConnectionFactory factory) {
        this.factory = factory;
        this.random = new Random();
    }

    @Scheduled(fixedDelay = 60000 )
    public void sendMessage() {
        try (Connection connection = factory.createConnection()) {
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("notificacion_sensores");
            MessageProducer producer = session.createProducer(queue);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = LocalDateTime.now().format(formatter);

            String json = """
                    {
                      "fechaGeneracion": "%s",
                      "IdDispositivo": %d,
                      "temperatura": %d,
                      "humedad": %d
                    }
                    """.formatted(
                    fechaFormateada,
                    1,
                    random.nextInt(11) + 10,
                    random.nextInt(11) + 10
            );

            System.out.println("Mensaje JSON enviado:");
            System.out.println(json);

            TextMessage message = session.createTextMessage(json);
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
