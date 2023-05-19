package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.LoginDTO;
import spring.summer.socialnetwork.dto.RefreshTokenDTO;
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


    public ResponseEntity<TokenDTO> authenticate(LoginDTO LoginDTO) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(LoginDTO.getEmail(), LoginDTO.getPassword())

            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(404).body(new TokenDTO().builder()
                    .message("Your data is invalid")
                    .build());
        }
        User user = userRepository.findByEmail(LoginDTO.getEmail()).get();
        String jwttoken = jwtService.generateToken(user);
        return ResponseEntity.ok(
                TokenDTO.builder()
                        .token(jwttoken)
                        .message("This is your auth token")
                        .role(user.getRoles())
                        .build()
        );


    }
    public ResponseEntity<RefreshTokenDTO> refreshTokenDTOResponseEntity(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(
                new RefreshTokenDTO("This is your refresh token", jwtToken));

    }




}
