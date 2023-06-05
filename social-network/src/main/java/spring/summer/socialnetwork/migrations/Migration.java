package spring.summer.socialnetwork.migrations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.models.Role;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

@Service
public class Migration {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            var newUser = User.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("Admin123#"))
                    .name("admin")
                    .surname("admin")
                    .roles(Role.ADMIN)
                    .build();
            userRepository.save(newUser);
        }
    }
}
