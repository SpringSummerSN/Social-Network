package spring.summer.socialnetwork.migrations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.models.Role;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

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

        if(!userRepository.existsByEmail("admin@gmail.com")){
           var new_user = User.builder()
                    .email("admin@gmail.com")
                    .password("Admin123*")
                   .name("admin")
                   .surname("admin")
                    .roles(Role.ADMIN)
                    .build();
            userRepository.save(new_user);
        }



    }


}
