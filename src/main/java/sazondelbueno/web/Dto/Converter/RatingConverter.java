package sazondelbueno.web.Dto.Converter;

import sazondelbueno.web.Dto.RatingDTO;
import sazondelbueno.web.Model.Rating;
import sazondelbueno.web.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RatingConverter {


    public static RatingDTO converterRating(Rating rating){
        return new RatingDTO(
                rating.getRating(),
                rating.getAccount().getId(),
                rating.getRecipe().getId());

    }

    public static List<RatingDTO> converterRatings(List<Rating> ratings){
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        ratings.forEach((r)-> ratingDTOS.add( new RatingDTO(r.getRating(),r.getAccount().getId(),r.getRecipe().getId())));

        return ratingDTOS;

    }
}
