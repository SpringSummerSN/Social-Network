package spring.summer.socialnetwork.DaoTests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void add_user_with_vaild_data(){
        User user = new User();
        user.setEmail("jan.kowalski@example.com");
        user.setName("Jan");
        user.setSurname("Kowalski");
        user.setPassword("Secret123#");

        // When
        userRepository.save(user);

        // Then
        User savedUser = userRepository.findByEmail("jan.kowalski@example.com").get();
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Jan");
        assertThat(savedUser.getSurname()).isEqualTo("Kowalski");

    }
}
