package sazondelbueno.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sazondelbueno.web.Model.Favorite;

import java.util.List;
import java.util.Optional;
@Repository
public interface FavoriteRepository  extends JpaRepository<Favorite,Long> {

    @Query("SELECT f FROM Favorite f WHERE f.account.id = :accountId AND f.recipe.id = :recipeId")
    Optional<Favorite> findByAccountIdAndRecipeId(Long accountId, Long recipeId);

    Optional<List<Favorite>> findAllByAccountId(Long accountId );

    void deleteAllByRecipeId(Long id);
    void deleteAllByAccountId(Long id);
}
