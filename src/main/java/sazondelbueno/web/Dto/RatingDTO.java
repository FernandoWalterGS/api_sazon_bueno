package sazondelbueno.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingDTO {
    private Integer rating;
    private Long accountId;
    private Long recipeId;


}
