package com.cityClass.CityClass.Config;

import com.cityClass.CityClass.Model.User;
import com.cityClass.CityClass.Service.UserServiceSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody User user) {
        Optional<User> existingUser = userServiceSecurity.findByMail(user.getMail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already in use"));
        }
        userServiceSecurity.registerUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", user.getId());  // assuming you have a method to get the user ID
        return ResponseEntity.ok(response);
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> user = userServiceSecurity.findByMail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            // Формируем ответ с userId
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", user.get().getId()); // Возвращаем userId

            return ResponseEntity.ok(response);
        }

        // Формируем ответ для ошибки
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Invalid email or password");

        return ResponseEntity.badRequest().body(errorResponse);
    }


}