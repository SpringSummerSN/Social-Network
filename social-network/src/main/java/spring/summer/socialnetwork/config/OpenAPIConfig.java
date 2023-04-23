package spring.summer.socialnetwork.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.summer.socialnetwork.repositories.GroupRepository;
import spring.summer.socialnetwork.repositories.UserRepository;
import spring.summer.socialnetwork.services.GroupService;

@Configuration
public class OpenAPIConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
