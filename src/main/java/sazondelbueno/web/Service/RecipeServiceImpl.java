package sazondelbueno.web.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sazondelbueno.web.Dto.Converter.RecipeConverter;
import sazondelbueno.web.Dto.RecipeDTO;
import sazondelbueno.web.Model.Recipe;
import sazondelbueno.web.Repository.CommentRepository;
import sazondelbueno.web.Repository.FavoriteRepository;
import sazondelbueno.web.Repository.RatingRepository;
import sazondelbueno.web.Repository.RecipeRepository;
import sazondelbueno.web.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> listRecipes(Long categoryId, Long difficultyId, String search) {

        System.out.println(search);

        if (categoryId != null) {
            return recipeRepository.findRecipesByCategoryId(categoryId);
        }
        if (difficultyId != null) {
            return recipeRepository.findRecipesByDifficultyId(difficultyId);
        }

        if (search != null) {

            return recipeRepository.findByNameContaining(search);
        }

        return recipeRepository.findAll();
    }

    @Override
    public List<RecipeDTO> listRecipesByAccount(Long id) {
        Optional<List<Recipe>> recipes = recipeRepository.findAllByAccountId(id);
        return RecipeConverter.convertRecipes(recipes);
    }

    @Override
    public RecipeDTO getById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return RecipeConverter.convertRecipe(recipe);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        try {
            ratingRepository.deleteAllByRecipeId(id);
            favoriteRepository.deleteAllByRecipeId(id);
            commentRepository.deleteAllByRecipeId(id);
            recipeRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Recipe updateById(Long id, Recipe payload) {


        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            Recipe existingRecipe = recipe.get();
            existingRecipe.setName(payload.getName());
            existingRecipe.setDescription(payload.getDescription());
            existingRecipe.setImage(payload.getImage());
            existingRecipe.setTime_min(payload.getTime_min());
            existingRecipe.setIngredients(payload.getIngredients());
            existingRecipe.setPreparation(payload.getPreparation());
            existingRecipe.setDifficulty(payload.getDifficulty());
            existingRecipe.setCategory(payload.getCategory());

            return recipeRepository.save(existingRecipe);
        } else {
            throw new ResourceNotFoundException("Recipe not found with id: " + id);
        }
    }
}
