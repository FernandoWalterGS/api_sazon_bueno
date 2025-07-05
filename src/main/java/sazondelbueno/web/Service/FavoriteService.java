package sazondelbueno.web.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sazondelbueno.web.Dto.FavoriteRecipes;
import sazondelbueno.web.Dto.FavoriteResponse;
import sazondelbueno.web.Model.Favorite;
import sazondelbueno.web.Repository.FavoriteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository repository;

    public FavoriteResponse saveFavorite(Favorite favorite) {

        Optional<Favorite> exist = repository.findByAccountIdAndRecipeId(favorite.getAccount().getId(), favorite.getRecipe().getId());
        if (exist.isEmpty()){
            repository.save(favorite);
            return new FavoriteResponse("Recetas agregada satisfactoriamente a favoritos");
        }
        System.out.println(exist.get());
        return new FavoriteResponse("Ya esta registrada como favorita");


    }


    public List<FavoriteRecipes> getAllRecipesByAccountId(Long id) {

        List<FavoriteRecipes> favoriteList = new ArrayList<>();
        Optional<List<Favorite>> lista = repository.findAllByAccountId(id);

        lista.ifPresent(favorites -> {

            favorites.forEach(favorite -> {
                favoriteList.add(new FavoriteRecipes(
                        favorite.getId(),
                        favorite.getRecipe().getId(),
                        favorite.getRecipe().getName(),
                        favorite.getRecipe().getDescription(),
                        favorite.getRecipe().getImage(),
                        favorite.getRecipe().getTime_min(),
                        favorite.getRecipe().getIngredients(),
                        favorite.getRecipe().getPreparation(),
                        favorite.getRecipe().getCreatedAt()
                ));
            });
        });

        return favoriteList;
    }

    public List<Favorite> listFavorites() {
        return repository.findAll();
    }

    public Optional<Favorite> getById(Long id) {
        return repository.findById(id);
    }

    public Favorite updateById(Long id, Favorite favorite) {

        Favorite reference = repository.getReferenceById(id);
        BeanUtils.copyProperties(favorite, reference, "id");
        return repository.save(reference);

    }

    public boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
