package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Dto.RecipeDTO;
import sazondelbueno.web.Model.Recipe;
import sazondelbueno.web.Responses.recipe.PutResponse;
import sazondelbueno.web.Service.RecipeService;

import java.util.List;

@Controller
@RequestMapping("/recipe")
@CrossOrigin("*")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping()
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe r) {
        Recipe recipe = recipeService.saveRecipe(r);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Recipe>> getRecipes(
            @RequestParam(name="search",required = false)String search,
            @RequestParam(name = "category" , required = false) Long category,
            @RequestParam(name = "difficulty" , required = false) Long difficulty
    ) {
        List<Recipe> lista = recipeService.listRecipes(category,difficulty,search);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<List<RecipeDTO>> sendRecipesByAccount(@PathVariable("id") Long id) {
        List<RecipeDTO> list = recipeService.listRecipesByAccount(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getById(@PathVariable("id") Long id) {
        RecipeDTO r = recipeService.getById(id);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        boolean exit = recipeService.deleteById(id);
        return new ResponseEntity<>(exit, HttpStatus.OK);
    }

    @SuppressWarnings("null")
    @PutMapping("/{id}")
    public ResponseEntity<PutResponse> updateReceta(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
        try {
            Recipe recipe1 = recipeService.updateById(id, recipe);
            return new ResponseEntity<>(new PutResponse("receta actualizada","ok"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PutResponse("Asegurese de llenar todos los valores del solicitados, son abligatorios","bad-request"), HttpStatus.BAD_REQUEST);

        }
    }

}
