package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Dto.Converter.RatingConverter;
import sazondelbueno.web.Dto.RatingDTO;
import sazondelbueno.web.Model.Rating;
import sazondelbueno.web.Service.RatingService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/rating")

public class RatingController {

    @Autowired
    RatingService service;


    @PostMapping
    public ResponseEntity<RatingDTO> saveRating(@RequestBody Rating rating) {
        RatingDTO r = service.ratingRegister(rating);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RatingDTO>> saveRating() {
        List<RatingDTO> ratingDTOS = RatingConverter.converterRatings(service.getAllRatings());
        return new ResponseEntity<>(ratingDTOS, HttpStatus.OK);
    }
    @GetMapping("/account/{idAccount}/recipe/{idRecipe}")
    public ResponseEntity<RatingDTO> getByAccountAndRecipe(@PathVariable Long idAccount, @PathVariable Long idRecipe ){

        return new ResponseEntity<>(service.getByAccountAndRecipe(idAccount,idRecipe),HttpStatus.OK);
    }

    @GetMapping("recipe/{idRecipe}")
    public ResponseEntity<Double> getAvgByRecipe(@PathVariable Long idRecipe){
        return new ResponseEntity<>(service.getAvgByRecipe(idRecipe),HttpStatus.OK);
    }





}
