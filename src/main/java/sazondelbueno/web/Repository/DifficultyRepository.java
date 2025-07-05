package sazondelbueno.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sazondelbueno.web.Model.Difficulty;
@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
}
