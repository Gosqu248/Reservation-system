package pl.urban.authservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import pl.urban.authservice.entity.User;
import pl.urban.authservice.request.LoginRequest;
import pl.urban.authservice.request.UserRequest;
import pl.urban.authservice.response.JwtResponse;
import pl.urban.authservice.response.UserResponse;
import pl.urban.authservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody @Valid UserRequest request
    ) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestBody @Valid LoginRequest loginRequest
    ) {
            final String jwt = userService.loginUser(loginRequest);
            return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(
            @RequestBody @Valid UserRequest request
    ) {
        userService.updateUser(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/exist/{email}")
    public ResponseEntity<Boolean> existsByEmail(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }

    @GetMapping("/exist/{email}")
    public ResponseEntity<UserResponse> findByEmail(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("email") String email
    ) {
        userService.deleteUser(email);
        return ResponseEntity.accepted().build();
    }
}
