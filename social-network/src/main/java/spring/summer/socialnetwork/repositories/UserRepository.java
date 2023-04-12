package spring.summer.socialnetwork.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.summer.socialnetwork.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
}