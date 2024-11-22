package com.cityClass.CityClass.Config;

import com.cityClass.CityClass.Model.User;
import com.cityClass.CityClass.Service.UserServiceSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserServiceSecurity userServiceSecurity;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        Optional<User> existingUser = userServiceSecurity.findByMail(user.getMail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        userServiceSecurity.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> user = userServiceSecurity.findByMail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.badRequest().body("Invalid email or password");
    }

}
