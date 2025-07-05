package sazondelbueno.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Integer time_min;
    private String ingredients;
    private String preparation;
    private String difficulty;
    private String category;
    private Date createdAt;
    private String user;
}
