package org.example.todos.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.todos.Model.User;
import org.example.todos.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        authService.register(user);
        return ResponseEntity.status(200).body("registered");
    }


    @PostMapping("/login")
    public ResponseEntity login() {
        return ResponseEntity.status(200).body(("Welcome Back"));
    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body(("Thank you"));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @RequestBody @Valid User user) {
        authService.updateUser(userId, user);
        return ResponseEntity.status(200).body(("User updated successfully"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        authService.deleteUser(userId);
        return ResponseEntity.status(200).body(("User deleted successfully"));
    }

}
