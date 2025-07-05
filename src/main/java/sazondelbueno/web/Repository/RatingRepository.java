package sazondelbueno.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sazondelbueno.web.Model.Rating;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    Optional<Rating> findByAccountIdAndRecipeId(Long idAccount, Long idRecipe);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.recipe.id = :recipeId")
    Double AvgRatingByRecipeId(Long recipeId);
    void deleteAllByRecipeId(Long id);
    void deleteAllByAccountId(Long id);





}
