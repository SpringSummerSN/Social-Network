package spring.summer.socialnetwork.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.exceptions.EmailExistsException;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.models.VerificationToken;
import spring.summer.socialnetwork.repositories.UserRepository;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private JavaMailSender javaMailSender;

    private TokenService tokenService;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.tokenService = tokenService;
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

        VerificationToken verificationToken = tokenService.createTokenforUser(user);

        String confirmation_text =
                "To fully register use this confirmation link"
                        + "\n"
                         +"http://localhost:8080/registrationConfirm?token="
                        + verificationToken.getToken();

        send_confirm_email(userDTO.getEmail(),confirmation_text );

        return "Registration suceed";

    }

    private void send_confirm_email(String to, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);
        javaMailSender.send(message);

    }


}
