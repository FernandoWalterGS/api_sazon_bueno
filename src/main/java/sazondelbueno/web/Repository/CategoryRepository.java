package sazondelbueno.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sazondelbueno.web.Model.Category;
@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {
}
