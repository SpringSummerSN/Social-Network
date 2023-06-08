package spring.summer.socialnetwork.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.services.FriendsService;

import java.util.List;

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
    @GetMapping()
    public ResponseEntity<List<User>> getFriends() {
        List<User> friends = friendsService.getUserFriends();
        return ResponseEntity.ok(friends);
    }

    @PostMapping("friend/{friendId}")
    public void addToFriends(@PathVariable Long friendId) {
        friendsService.addUserToFriends(friendId);

    }

}
