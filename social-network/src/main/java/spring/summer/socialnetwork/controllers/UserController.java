package spring.summer.socialnetwork.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.models.User;
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
    @PostMapping
    public ResponseEntity<User> add(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    // DELETE USER BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }

    // GET ALL USERS
    @GetMapping
    public List<User> userList() {
        return userService.getAllUsers();
    }

    // UPDATE USER BY ID
    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, UserDTO userDTO) {
        userService.updateUser(id, userDTO);
    }
}
