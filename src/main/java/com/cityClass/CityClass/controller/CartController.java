package com.cityClass.CityClass.controller;


import com.cityClass.CityClass.DTO.CartItemDTO;
import com.cityClass.CityClass.DTO.ProductDto;
import com.cityClass.CityClass.Exeption.ProductNotFoundException;
import com.cityClass.CityClass.Model.Cart;
import com.cityClass.CityClass.Model.Cart_Item;
import com.cityClass.CityClass.Model.User;
import com.cityClass.CityClass.Repo.CartItemRepository;
import com.cityClass.CityClass.Repo.CartRepository;
import com.cityClass.CityClass.Repo.ProductRepository;
import com.cityClass.CityClass.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

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

    @GetMapping("/show")
    public ResponseEntity<List<CartItemDTO>> showCart(@RequestParam Integer userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Если корзина не найдена
        }

        List<Cart_Item> cartItems = cartItemRepository.findByCartId(cart.getId());

        List<CartItemDTO> cartItemDTOs = cartItems.stream()
                .map(cartItem -> new CartItemDTO(
                        Long.valueOf(cartItem.getProduct().getId()), // Преобразование в Long
                        cartItem.getProduct().getProduct_name(),
                        cartItem.getQuantity()               // Количество
                ))
                .toList();

        return ResponseEntity.ok(cartItemDTOs); // Возвращаем список DTO
    }

    @DeleteMapping("/delate")
    public ResponseEntity<String> delateProduct(@RequestParam Integer productId, @RequestParam Integer userId) {
        try {
            cartService.dalateProduct(productId, userId);
            return ResponseEntity.ok("Product successfully deleted from the cart.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the product.");
        }
    }


}
