package com.Library.Controller;

import com.Library.DTO.*;
import com.Library.Entity.Book;
import com.Library.Entity.Borrow;
import com.Library.Entity.User;
import com.Library.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtTokenDTO(token));
    }

    @Operation(summary = "Register a new user", description = "Adds a new user to the system")
    @PostMapping("/addUser")
    public User register(@RequestBody UserDTO user) {
        return authService.addUser(user);
    }

    @GetMapping("/books")
    public ResponseEntity<String> getBooks() {
        return ResponseEntity.ok("Books for admin");
    }
}

