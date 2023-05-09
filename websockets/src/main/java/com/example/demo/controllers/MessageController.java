package com.example.demo.controllers;

import com.example.demo.models.Message;
import com.example.demo.services.MessageSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
@Controller
public class MessageController {

    private SimpMessagingTemplate template;

    private MessageSerive messageSerive;
    @Autowired
    public MessageController(SimpMessagingTemplate template, MessageSerive messageSerive) {
        this.template = template;
        this.messageSerive = messageSerive;
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        System.out.println(message);
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        template.convertAndSendToUser(message.getReceiverName(),"/private",message);
        messageSerive.save_message(message);
        System.out.println(message.toString());
        return message;
    }

}
