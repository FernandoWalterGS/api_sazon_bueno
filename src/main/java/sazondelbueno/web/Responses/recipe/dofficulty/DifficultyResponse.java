package sazondelbueno.web.Responses.recipe.dofficulty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DifficultyResponse {
    private Boolean status;
    private String  message;
}
