package spring.summer.socialnetwork.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.summer.socialnetwork.models.Group;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long>
{
    public List<Group> findAll();
}
