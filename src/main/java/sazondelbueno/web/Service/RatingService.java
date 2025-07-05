package sazondelbueno.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sazondelbueno.web.Dto.Converter.RatingConverter;
import sazondelbueno.web.Dto.RatingDTO;
import sazondelbueno.web.Model.Rating;
import sazondelbueno.web.Repository.RatingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    public RatingDTO ratingRegister(Rating rating) {
        Optional<Rating> exist = repository.findByAccountIdAndRecipeId(rating.getAccount().getId(), rating.getRecipe().getId());

        System.out.println(repository.AvgRatingByRecipeId(2L));
        if (exist.isPresent()) {
            Rating existingRating = exist.get();
            existingRating.setRating(rating.getRating());
            return RatingConverter.converterRating(repository.save(existingRating));
        } else {
            return RatingConverter.converterRating(repository.save(rating));
        }
    }

    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    public RatingDTO getByAccountAndRecipe(Long idAccount, Long idRecipe){
        Optional<Rating> rating = repository.findByAccountIdAndRecipeId(idAccount,idRecipe);

        if (rating.isPresent()){
            Rating r = rating.get();
            return RatingConverter.converterRating(r);
        }else {
            return new RatingDTO(0,idAccount,idRecipe);    }

    }

    public Double getAvgByRecipe(Long idRecipe){
      return repository.AvgRatingByRecipeId(idRecipe);

    }




}
