package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Model.Recipe;
import sazondelbueno.web.Service.RecipeService;

import java.util.List;
@RestController
@RequestMapping("/public/recipes")
@CrossOrigin("*")

public class PublicController {

    @Autowired
    RecipeService recipeService;

    @GetMapping()
    public ResponseEntity<List<Recipe>> getRecipes(
            @RequestParam(name="search",required = false)String search,
            @RequestParam(name = "category" , required = false) Long category,
            @RequestParam(name = "difficulty" , required = false) Long difficulty
    ) {
        List<Recipe> lista = recipeService.listRecipes(category,difficulty,search);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
