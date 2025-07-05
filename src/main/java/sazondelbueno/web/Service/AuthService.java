package sazondelbueno.web.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sazondelbueno.web.Dto.AuthResponse;
import sazondelbueno.web.Dto.LoginRequest;
import sazondelbueno.web.Model.Account;
import sazondelbueno.web.Repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Object login(LoginRequest request) throws Exception {
        AuthResponse response = null;
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            Account account = repository.findByUsername(request.getEmail()).orElseThrow();

            if (account.getActive()) {
                UserDetails user = account;
                String token = jwtService.getToken(account);
                return AuthResponse.builder()
                        .token(token)
                        .email(user.getUsername())
                        .userId(account.getId())
                        .name(account.getName())
                        .isAdmin(account.getIsAdmin())
                        .build();
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario no existe o no está activo");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error durante el inicio de sesión");
        }

    }

    public AuthResponse register(Account account) {
        if (account.getIsAdmin() == null) {
            account.setIsAdmin(false);
        }
        Account user = Account.builder()
                .name(account.getName())
                .password(passwordEncoder.encode(account.getPassword()))
                .date_of_birth(account.getDate_of_birth())
                .username(account.getUsername())
                .isAdmin(account.getIsAdmin())
                .build();
        repository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}