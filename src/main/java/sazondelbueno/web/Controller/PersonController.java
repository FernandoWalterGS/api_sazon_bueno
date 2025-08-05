package sazondelbueno.web.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class PersonController {

    @GetMapping
    String welcome() {
        return "welcome spring security";
    }
}
