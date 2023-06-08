package spring.summer.socialnetwork.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.InvitationRequestDTO;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class FriendsService {

    private KafkaTemplate template;
    private UserRepository userRepository;

    @Autowired
    public FriendsService(KafkaTemplate template, UserRepository userRepository) {
        this.template = template;
        this.userRepository = userRepository;
    }

    public void send_request(Long id) throws JsonProcessingException {
        User sender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User receiver = userRepository.findById(id).get();
        InvitationRequestDTO requestDTO = new InvitationRequestDTO(
                sender.getEmail(), receiver.getEmail());
        ObjectMapper mapper = new ObjectMapper();

        template.send("friends", mapper.writeValueAsString(requestDTO));
    }

    public List<User> getUserFriends() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).orElse(null);

        Long userId = userRepository.findByEmail(auth.getName()).get().getId();
        if (user != null) {
            return user.getFriends();
        }
        return Collections.emptyList();
    }

    public void addUserToFriends(Long friendId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).orElse(null);
        User friend = userRepository.findById(friendId).orElse(null);
        if(user!=null && friend!=null){
            user.addToFriends(friend);
            //friend.addToFriends(user);
            userRepository.save(user);
            //userRepository.save(friend);

            return;
        }
        System.out.println("brak usera o podanym id");

    }
}
