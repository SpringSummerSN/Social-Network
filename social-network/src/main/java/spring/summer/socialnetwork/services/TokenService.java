package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.models.VerificationToken;
import spring.summer.socialnetwork.repositories.UserRepository;
import spring.summer.socialnetwork.repositories.VerificationTokenRepository;

import java.util.Calendar;
import java.util.UUID;

@Service
public class TokenService {


    private VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public TokenService(VerificationTokenRepository tokenRepository,
                        UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public VerificationToken createTokenforUser(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
        return verificationToken;

    }

    public String confirm_user_account(String token) {
        VerificationToken verificationToken = null;
        try {
             verificationToken = tokenRepository.findByToken(token).get();
        }catch (Exception e){
            return "confirmationerror";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "confirmationerror";
        } else {
            user.setEnabled(true);
            userRepository.save(user);
            System.out.println("tutaj");
            return "confirmation";
        }
    }

}
