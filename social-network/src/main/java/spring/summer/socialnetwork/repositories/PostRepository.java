package spring.summer.socialnetwork.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.summer.socialnetwork.models.Post;
import spring.summer.socialnetwork.models.User;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();

    List<Post> findPostByCreator(User user);
}
