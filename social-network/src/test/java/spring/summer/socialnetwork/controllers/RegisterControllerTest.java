package spring.summer.socialnetwork.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.filter.CorsFilter;
import spring.summer.socialnetwork.TestSecurityConfig;
import spring.summer.socialnetwork.config.SecurityConfig;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.jwt.JwtFilter;
import spring.summer.socialnetwork.repositories.UserRepository;
import spring.summer.socialnetwork.repositories.VerificationTokenRepository;
import spring.summer.socialnetwork.services.JwtService;
import spring.summer.socialnetwork.services.RegisterService;
import spring.summer.socialnetwork.services.TokenService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {RegisterController.class})
@AutoConfigureMockMvc
@Import({JacksonAutoConfiguration.class, TestSecurityConfig.class})
public class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private VerificationTokenRepository tokenRepository;

    @MockBean
    private RegisterService registerService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_user_test() throws Exception {
        var user = UserDTO.builder()
                .email("jan.kowalski@example.com")
                .name("Jan")
                .surname("K")
                .password("secret123#")
                .build();
        String data = objectMapper.writeValueAsString(user);



        mvc.perform(post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data)
                )
                .andExpect(status().isOk());

    }



}
