package sazondelbueno.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FavoriteRecipes {
    private Long idFavorite;
    private Long id;
    private String name;
    private String description;
    private String image;
    private int time_min;
    private String ingredients;
    private String preparation;
    private Date createdAt;

}
