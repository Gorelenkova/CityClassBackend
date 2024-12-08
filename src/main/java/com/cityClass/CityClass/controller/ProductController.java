package com.cityClass.CityClass.controller;

import com.cityClass.CityClass.Model.Product;
import com.cityClass.CityClass.Repo.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductRepository productRepository;
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Integer> ids = List.of(9, 10, 11, 12);
        List<Product> products = productRepository.findByIds(ids);
        return ResponseEntity.ok(products);
    }

}
