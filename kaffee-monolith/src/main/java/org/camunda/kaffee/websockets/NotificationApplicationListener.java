package org.camunda.kaffee.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class NotificationApplicationListener {
    public static final String DESTINATION = "/topic/message";
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
   
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {	
    }
   
    public void notify(String message) {
        System.out.println("Sending " + message);
    		messagingTemplate.convertAndSend(DESTINATION, message);
        System.out.println("Done");
    }

}
