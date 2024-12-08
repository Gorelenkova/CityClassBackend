package com.cityClass.CityClass.Service;

import com.cityClass.CityClass.Model.Product;
import com.cityClass.CityClass.Repo.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> getProductWithId(List<Integer> ids){
        List<Product> products = productRepository.findByIds(ids);
        return products;
    }
}
