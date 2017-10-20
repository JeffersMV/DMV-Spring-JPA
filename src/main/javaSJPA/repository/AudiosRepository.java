package repository;

import entity.Audios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudiosRepository extends JpaRepository<Audios, Long> {
}
