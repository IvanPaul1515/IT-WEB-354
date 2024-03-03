package org.example.jms.conf;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsListenerComponent {

    private final SimpMessagingTemplate messagingTemplate;
    public JmsListenerComponent(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @JmsListener(destination = "notificacion_sensores")
    public void handleMessage(String message) {
        System.out.println("Received message: " + message);
        messagingTemplate.convertAndSend("/topic/greetings", message);
    }
}