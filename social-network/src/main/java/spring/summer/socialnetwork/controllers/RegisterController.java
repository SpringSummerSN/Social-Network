package spring.summer.socialnetwork.controllers;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.exceptions.EmailExistsException;
import spring.summer.socialnetwork.services.RegisterService;
import spring.summer.socialnetwork.validator.ValidPassword;

@RestController
@Validated
@RequestMapping("api/v1/register")
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO userDTO) throws EmailExistsException, MessagingException {
        return ResponseEntity.ok(registerService.register(userDTO));
    }


}
