package spring.summer.socialnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.summer.socialnetwork.services.TokenService;

@Controller
public class RegisterTokenVerificationController {

    private TokenService tokenService;
    @Autowired
    public RegisterTokenVerificationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/registrationConfirm")
    public String user_register_confirmation(@RequestParam("token") String token) {
        return tokenService.confirm_user_account(token);
    }
}
