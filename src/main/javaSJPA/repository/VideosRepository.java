package repository;

import entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {
}
