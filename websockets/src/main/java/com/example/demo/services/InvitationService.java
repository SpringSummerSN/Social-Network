package com.example.demo.services;

import com.example.demo.models.Invitation;
import com.example.demo.models.Message;
import com.example.demo.repositories.InvitationRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
@Service
public class InvitationService {
    private InvitationRepository repository;

    @Autowired
    public InvitationService(InvitationRepository repository) {
        this.repository = repository;
    }



    public void create_invitation(JSONObject json){
        Invitation invitation = Invitation.builder()
                .receiver_email(json.getString("receiver"))
                .sender_email(json.getString("sender"))
                .build();
        repository.save(invitation);

    }


}
