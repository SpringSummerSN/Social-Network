package com.example.demo.services;

import com.example.demo.models.Message;
import com.example.demo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSerive {
    private MessageRepository messageRepository;

    @Autowired
    public MessageSerive(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save_message(Message message){
        messageRepository.save(message);
    }


}
