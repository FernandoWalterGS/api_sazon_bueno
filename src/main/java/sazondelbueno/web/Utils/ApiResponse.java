package sazondelbueno.web.Utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;

    public ApiResponse(String message, String data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.data = httpStatus;
    }
}
