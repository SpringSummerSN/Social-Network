package spring.summer.socialnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.summer.socialnetwork.models.ImageData;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}