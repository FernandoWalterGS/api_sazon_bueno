package sazondelbueno.web.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountResponse {
    Boolean status;
    String message;
}
