package spring.summer.socialnetwork.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.InvitationRequestDTO;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

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

}
