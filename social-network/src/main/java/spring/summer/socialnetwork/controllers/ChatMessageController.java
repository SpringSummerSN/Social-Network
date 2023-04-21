package spring.summer.socialnetwork.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import spring.summer.socialnetwork.dto.MessageDto;

@Controller
public class ChatMessageController {
    @MessageMapping("/chat")
    @SendTo("/topic")
    public MessageDto get(MessageDto messageDto){
        messageDto.setMessage(messageDto.getMessage()+" +server adnotation");
        return messageDto;
    }
}
