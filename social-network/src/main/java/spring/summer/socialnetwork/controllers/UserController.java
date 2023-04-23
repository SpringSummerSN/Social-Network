package spring.summer.socialnetwork.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.UserRepository;
import spring.summer.socialnetwork.services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    // ADD USER


    // DELETE USER BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    // GET ALL USERS
    @GetMapping
    public List<User> userList() {
        return userService.getAllUsers();
    }

    // UPDATE USER BY ID
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }
}
