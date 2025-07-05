package sazondelbueno.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import sazondelbueno.web.Model.Favorite;

@Data
@AllArgsConstructor
public class FavoriteResponse {
    Favorite favorite;
    String message;

    public FavoriteResponse(String msg) {
        this.message = msg;
    }
}
