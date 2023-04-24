package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.LoginDTO;
import spring.summer.socialnetwork.dto.TokenDTO;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

@Service
public class LoginService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    public TokenDTO authenticate(LoginDTO LoginDTO){
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(LoginDTO.getEmail(), LoginDTO.getPassword())

            );
        } catch (AuthenticationException e) {
            return null;
        }
        User user = userRepository.findByEmail(LoginDTO.getEmail()).get();
        String jwttoken = jwtService.generateToken(user);
        return TokenDTO.builder()
                .token(jwttoken)
                .message("This is your auth token")
                .build();



    }


}
