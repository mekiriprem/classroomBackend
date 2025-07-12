package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketSignalController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketSignalController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/signal/{code}")
    public void handleSignal(@DestinationVariable String code, @Payload String signal) {
        messagingTemplate.convertAndSend("/topic/signal/" + code, signal);
    }
}