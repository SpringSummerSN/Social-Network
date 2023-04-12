package spring.summer.socialnetwork.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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
    public void add(UserDTO userDTO) {
        userService.saveUser(userDTO);
    }

    // DELETE USER BY ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        userService.deleteUser(id);
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
