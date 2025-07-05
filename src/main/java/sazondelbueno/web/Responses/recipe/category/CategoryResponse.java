package sazondelbueno.web.Responses.recipe.category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private Boolean status;
    private String message;

}
