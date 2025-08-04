package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Dto.FavoriteRecipes;
import sazondelbueno.web.Dto.FavoriteResponse;
import sazondelbueno.web.Model.Favorite;
import sazondelbueno.web.Service.FavoriteService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/favorite")

public class FavoriteController {

    @Autowired
    FavoriteService service;

    @PostMapping
    public ResponseEntity<FavoriteResponse> saveFavorite(@RequestBody Favorite f) {
        FavoriteResponse favorite = service.saveFavorite(f);
        return new ResponseEntity<>(favorite, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getFavorites() {
        List<Favorite> favoriteList = service.listFavorites();
        return new ResponseEntity<>(favoriteList, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<List<FavoriteRecipes>> getFavoriteRecipeByAccount(@PathVariable("id") Long id) {
        List<FavoriteRecipes> favorites = service.getAllRecipesByAccountId(id);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Favorite>> getById(@PathVariable("id") Long id) {
        Optional<Favorite> favorite = service.getById(id);
        return new ResponseEntity<>(favorite, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        boolean exit = service.deleteById(id);
        return new ResponseEntity<>(exit, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable("id") Long id, @RequestBody Favorite favorite) {
        try {
            return new ResponseEntity<>(service.updateById(id, favorite), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
