package sazondelbueno.web.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Dto.AuthResponse;
import sazondelbueno.web.Dto.LoginRequest;
import sazondelbueno.web.Model.Account;
import sazondelbueno.web.Service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) throws Exception {
        System.out.println(request);
        return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Account request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
