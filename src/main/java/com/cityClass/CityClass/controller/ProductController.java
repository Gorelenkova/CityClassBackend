package com.cityClass.CityClass.controller;

import com.cityClass.CityClass.DTO.ProductDto;
import com.cityClass.CityClass.Model.Cart;
import com.cityClass.CityClass.Model.Cart_Item;
import com.cityClass.CityClass.Model.Product;
import com.cityClass.CityClass.Repo.CartItemRepository;
import com.cityClass.CityClass.Repo.CartRepository;
import com.cityClass.CityClass.Repo.ProductRepository;
import com.cityClass.CityClass.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService service;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public ProductController(ProductService service,CartRepository cartRepository, CartItemRepository cartItemRepository){
        this.service = service;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }
    @GetMapping("/show")
    public ResponseEntity<List<ProductDto>> getUserProducts(@RequestParam Integer userId) {
        // 1. Находим корзину пользователя по ID
        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Корзина не найдена
        }

        // 2. Извлекаем элементы корзины
        List<Cart_Item> cartItems = cartItemRepository.findByCartId(cart.getId());

        // 3. Преобразуем данные в DTO для продуктов
        List<ProductDto> products = cartItems.stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    return new ProductDto(
                            product.getId(),
                            product.getProduct_name(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getSizes(),
                            product.getImage_url(),
                            cartItem.getQuantity()
                    );
                })
                .toList();

        // 4. Возвращаем JSON с данными о продуктах
        return ResponseEntity.ok(products);
    }
    @GetMapping("/man")
    public ResponseEntity<List<Product>> getProductMen(){
        List<Integer> ids = List.of(9, 10, 11, 12);
        List<Product> products = service.getProductWithId(ids);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/woman")
    public ResponseEntity<List<Product>> getProductWoman(){
        List<Integer> ids = List.of(13,14,15,16);
        List<Product> products = service.getProductWithId(ids);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/bag")
    public ResponseEntity<List<Product>> getProductBags(){
        List<Integer> ids = List.of(17,18,19,20);
        List<Product> products = service.getProductWithId(ids);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/accessory")
    public ResponseEntity<List<Product>> getProductAcc(){
        List<Integer> ids = List.of(21,22,23,24);
        List<Product> products = service.getProductWithId(ids);
        return ResponseEntity.ok(products);
    }
}
