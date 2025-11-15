package com.ss.authservice.controller;

import com.ss.authservice.dto.LoginRequestDTO;
import com.ss.authservice.dto.LoginResponseDTO;
import com.ss.authservice.dto.RegisterRequestDTO;
import com.ss.authservice.dto.RegisterResponseDTO;
import com.ss.authservice.model.User;
import com.ss.authservice.service.AuthService;
import com.ss.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    public AuthController(AuthService authService,  UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        if (userService.existsByEmail(registerRequestDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }

        User user = userService.createUser(
                registerRequestDTO.getEmail(),
                registerRequestDTO.getPassword(),
                registerRequestDTO.getRole()
        );

        RegisterResponseDTO response = new RegisterResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedDate()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);
        if (tokenOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = tokenOptional.get();
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Generate Token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
