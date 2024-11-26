package com.cityClass.CityClass.controller;


import com.cityClass.CityClass.DTO.ProductDto;
import com.cityClass.CityClass.Exeption.ProductNotFoundException;
import com.cityClass.CityClass.Model.User;
import com.cityClass.CityClass.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addToCart(@RequestParam Long userId, @RequestParam Long productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Логика добавления товара в корзину
            cartService.addProductToCart(userId, productId);

            // Формирование JSON-ответа
            response.put("statusCode", 200);
            response.put("message", "Product added to cart");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace();

            // Формирование JSON-ответа с ошибкой
            response.put("statusCode", 500);
            response.put("message", "Error adding product to cart");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
