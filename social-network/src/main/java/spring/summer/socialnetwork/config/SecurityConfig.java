package spring.summer.socialnetwork.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import spring.summer.socialnetwork.jwt.JwtFilter;


@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final CorsFilter corsFilter;


    @Autowired
    public SecurityConfig(JwtFilter jwtFilter, CorsFilter corsFilter) {
        this.jwtFilter = jwtFilter;
        this.corsFilter = corsFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChains(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/login", "/api/v1/register")
                .requestMatchers("/**")
                .permitAll()
//                .requestMatchers("/api/v1/**")
//                .authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter, JwtFilter.class);
        return http.build();
    }
}