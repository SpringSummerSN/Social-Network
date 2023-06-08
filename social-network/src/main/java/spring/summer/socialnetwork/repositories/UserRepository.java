package spring.summer.socialnetwork.repositories;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.summer.socialnetwork.models.ImageData;
import spring.summer.socialnetwork.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
     List<User> findAll();

     Optional<User> findByEmail(String email);

     boolean existsByEmail(String email);

     List<User> findFirst10ByOrderByIdAsc();

     Optional<User> getUserById(Long userId);

     @Query("SELECT u.image FROM User u WHERE u.id = :userId")
     Optional <ImageData> findUserImageById(@Param("userId") Long userId);
}

