package com.example.demo.kafka;

import com.example.demo.services.InvitationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Component;

@KafkaListener(id = "myid", topics = "friends")
@EnableKafka
@Component
public  class FriendsRequestListener {
    private InvitationService service;

    @Autowired
    public FriendsRequestListener(InvitationService service) {
        this.service = service;
    }

    @KafkaHandler
    public void listen(String message){
        JSONObject json = new JSONObject(message);
        service.create_invitation(json);

    }

}
