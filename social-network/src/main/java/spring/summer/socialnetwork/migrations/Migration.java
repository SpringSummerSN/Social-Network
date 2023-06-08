package spring.summer.socialnetwork.migrations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.models.Role;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

@Service
public class Migration {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String email;
    @Value("${admin.password}")
    private String password;
    @Value("${admin.name}")
    private String name;

    @Autowired
    public Migration(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void initializeDb() {
        this.createAdminUser();
    }

    @Transactional
    public void createAdminUser() {
        if (!userRepository.existsByEmail(email)) {
            var newUser = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .surname(name)
                    .roles(Role.ADMIN)
                    .build();
            userRepository.save(newUser);
        }
    }
}
