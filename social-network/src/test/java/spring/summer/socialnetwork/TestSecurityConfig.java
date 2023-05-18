package spring.summer.socialnetwork;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
@EnableWebSecurity
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChains(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .requestMatchers("/**") // Dostęp dla wszystkich endpointów
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .build();
    }
}