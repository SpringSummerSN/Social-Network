package spring.summer.socialnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;

import java.util.List;

@RepositoryRestController
public class TestRestController {

    private UserRepository userRepository;
    @Autowired
    public TestRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users/recent")
    public ResponseEntity<List<User>> recent_users(){
        List<User> userList = userRepository.findFirst10ByOrderByIdAsc();
        return ResponseEntity.ok(userList);

    }



}

