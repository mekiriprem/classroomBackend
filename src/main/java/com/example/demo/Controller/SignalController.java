//package com.example.demo.Controller;
//
//import org.springframework.messaging.handler.annotation.*;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//import com.example.demo.Entity.SignalMessage;
//
//@Controller
//public class SignalController {
//
//  private final SimpMessagingTemplate messagingTemplate;
//
//  public SignalController(SimpMessagingTemplate messagingTemplate) {
//    this.messagingTemplate = messagingTemplate;
//  }
//
//  // Handles /app/signal/{code} and relays to /topic/signal/{code}
//  @MessageMapping("/signal/{code}")
//  public void relaySignal(@DestinationVariable String code, @Payload SignalMessage signal) {
//    messagingTemplate.convertAndSend("/topic/signal/" + code, signal);
//  }
//}
//
