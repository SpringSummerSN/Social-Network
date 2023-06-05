package spring.summer.socialnetwork.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.exceptions.EmailExistsException;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.models.VerificationToken;
import spring.summer.socialnetwork.repositories.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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


    private void registerValidate(UserDTO userDTO) throws EmailExistsException {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            System.out.println("Test");
            throw new EmailExistsException();
        }

    }


    public String register(UserDTO userDTO) throws EmailExistsException, MessagingException, IOException {
        registerValidate(userDTO);
        var user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .surname(userDTO.getSurname())
                .name(userDTO.getName())
                .build();


        userRepository.save(user);

        VerificationToken verificationToken = tokenService.createTokenforUser(user);

        String confirmationText = Files.readString(Paths.get("src/main/resources/confirmationtext.txt"));
        confirmationText = confirmationText.replace("{name}", userDTO.getName());
        confirmationText = confirmationText.replace("{token}", verificationToken.getToken());
        sendConfirmEmail(userDTO.getEmail(), confirmationText);
        return "Registration succeed";
    }

    private void sendConfirmEmail(String to, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(to);
        helper.setSubject("Complete registration!");
        message.setContent(text, "text/html");
        javaMailSender.send(message);
    }
}