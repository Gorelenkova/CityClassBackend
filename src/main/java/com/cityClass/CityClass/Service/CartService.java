package com.cityClass.CityClass.Service;

import com.cityClass.CityClass.DTO.ProductDto;
import com.cityClass.CityClass.Exeption.ProductNotFoundException;
import com.cityClass.CityClass.Model.Cart;
import com.cityClass.CityClass.Model.Cart_Item;
import com.cityClass.CityClass.Model.Product;
import com.cityClass.CityClass.Model.User;
import com.cityClass.CityClass.Repo.CartRepository;
import com.cityClass.CityClass.Repo.ProductRepository;
import com.cityClass.CityClass.Repo.UserRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository, UserRepository userRepository1, UserRepository userRepository2) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository2;
    }

    public void addProductToCart(Long userId, Long productId) {
        // Найти пользователя по userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExpressionException("Пользователь не найден"));

        // Найти продукт по productId
        Product product = productRepository.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new ProductNotFoundException("Продукт не найден"));

        // Найти корзину пользователя или создать новую
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        // Проверить, есть ли продукт уже в корзине
        Cart_Item cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            // Обновить количество
            cartItem.setQuantity(cartItem.getQuantity() + 1); // Увеличиваем количество на 1
        } else {
            // Добавить новый продукт в корзину
            cartItem = new Cart_Item();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(1); // Устанавливаем начальное количество 1
            cart.getItems().add(cartItem);
        }

        // Сохранить обновленную корзину
        cartRepository.save(cart);
    }

}
