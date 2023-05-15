package spring.summer.socialnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.models.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);
}