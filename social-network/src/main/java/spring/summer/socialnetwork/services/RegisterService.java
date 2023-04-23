package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.exceptions.EmailExistsException;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private void register_walidace(UserDTO userDTO) throws EmailExistsException {
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new EmailExistsException("That email already exists");
        }

    }


    public String register(UserDTO userDTO) throws EmailExistsException {
        register_walidace(userDTO);
        var user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .surname(userDTO.getSurname())
                .name(userDTO.getName())
                .build();

        userRepository.save(user);
        return "Registration suceed";

    }

}
