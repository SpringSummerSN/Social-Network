package spring.summer.socialnetwork.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.summer.socialnetwork.models.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();

    public User findByEmail(String email);
}