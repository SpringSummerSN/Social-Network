package spring.summer.socialnetwork.migrations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.models.Role;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;
import spring.summer.socialnetwork.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class Migration {
    private  UserRepository userRepository;
    @Autowired
    public Migration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public  void  initialize_db(){
        this.create_admin_user();
    }

    @Transactional
    public void create_admin_user(){
        var user=  userRepository.findByEmail("admin@gmail.com");

        if(user == null){
           var new_user = User.builder()
                    .email("admin@gmail.com")
                    .password("admin")
                    .roles(Arrays.asList(new Role[]{new Role("ADMIN")}))
                    .build();
            userRepository.save(new_user);
        }



    }


}
