package spring.summer.socialnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.summer.socialnetwork.models.FileData;
import spring.summer.socialnetwork.models.User;


import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Long> {
    Optional<FileData> findByName(String fileName);
    Optional<FileData> findByUser(User user);

    Optional<FileData> findById(Long id);
}