package spring.summer.socialnetwork.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.summer.socialnetwork.services.FriendsService;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendsController {
   private FriendsService friendsService;

   @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping("/{id}")
    public void test_kafka(@PathVariable Long id) throws JsonProcessingException {
        friendsService.send_request(id);
    }

}
