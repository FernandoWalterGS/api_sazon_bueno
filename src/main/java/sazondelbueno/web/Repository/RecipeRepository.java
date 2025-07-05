package sazondelbueno.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sazondelbueno.web.Model.Account;
import sazondelbueno.web.Model.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<List<Recipe>> findAllByAccountId(Long id);

    List<Recipe> findRecipesByCategoryId(Long id);

    List<Recipe> findRecipesByDifficultyId(Long id);

    List<Recipe>findByNameContaining(String name);

    void deleteAllByAccountId(Long id);

}
