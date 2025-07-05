package sazondelbueno.web.Service;

import sazondelbueno.web.Dto.RecipeDTO;
import sazondelbueno.web.Model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe saveRecipe(Recipe recipe);

    List<Recipe> listRecipes(Long categoryId,Long difficultyId,String regex);

    List<RecipeDTO>listRecipesByAccount(Long id);

    RecipeDTO getById(Long id);

    boolean deleteById(Long id);

    Recipe updateById(Long id, Recipe recipe);
}
