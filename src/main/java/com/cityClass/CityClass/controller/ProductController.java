package com.cityClass.CityClass.controller;

import com.cityClass.CityClass.Model.Product;
import com.cityClass.CityClass.Repo.ProductRepository;
import com.cityClass.CityClass.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService service;
    public ProductController(ProductService service){
        this.service = service;
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
