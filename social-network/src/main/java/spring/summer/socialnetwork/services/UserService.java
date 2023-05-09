package spring.summer.socialnetwork.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    public ResponseEntity deleteUser(String id) {
        if (userRepository.findById(Long.parseLong(id)).equals(Optional.empty()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else {
            userRepository.deleteById(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(String id) {
        if (userRepository.findById(Long.parseLong(id)).equals(Optional.empty()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else {

            var user = userRepository.findById(Long.parseLong(id)).get();

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @Transactional
    public ResponseEntity<User> updateUser(String id, UserDTO userDTO) {

        if (userRepository.findById(Long.parseLong(id)).equals(Optional.empty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            var user = userRepository.findById(Long.parseLong(id)).get();
            user.setEmail(userDTO.getEmail());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }


    }
}
