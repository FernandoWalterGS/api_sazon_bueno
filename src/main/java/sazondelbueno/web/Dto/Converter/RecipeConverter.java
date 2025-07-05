package sazondelbueno.web.Dto.Converter;

import sazondelbueno.web.Dto.RecipeDTO;
import sazondelbueno.web.Model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeConverter {

    public static RecipeDTO convertRecipe(Optional<Recipe> recipe) {
        if (recipe.isPresent()) {
            Recipe rec = recipe.get();
            return new RecipeDTO(
                    rec.getId(),
                    rec.getName(),
                    rec.getDescription(),
                    rec.getImage(),
                    rec.getTime_min(),
                    rec.getIngredients(),
                    rec.getPreparation(),
                    rec.getDifficulty().getName(),
                    rec.getCategory().getName(),
                    rec.getCreatedAt(),
                    rec.getAccount().getName()
            );
        } else {
            return new RecipeDTO();
        }
    }
    public static List<RecipeDTO> convertRecipes(Optional<List<Recipe>> recipes) {
        List<RecipeDTO> listRecipes = new ArrayList<>();

        recipes.ifPresent(allRecipes -> allRecipes.forEach((rec) -> {
            listRecipes.add(new RecipeDTO(
                    rec.getId(),
                    rec.getName(),
                    rec.getDescription(),
                    rec.getImage(),
                    rec.getTime_min(),
                    rec.getIngredients(),
                    rec.getPreparation(),
                    rec.getDifficulty().getName(),
                    rec.getCategory().getName(),
                    rec.getCreatedAt(),
                    rec.getAccount().getName()
            ));
        }));

        return listRecipes;
    }
}
