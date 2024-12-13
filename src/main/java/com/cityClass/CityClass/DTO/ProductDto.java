package com.cityClass.CityClass.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String productName; // Название продукта
    private String description; // Описание продукта
    private BigDecimal price;   // Цена продукта
    private List<Integer> sizes; // Размеры продукта
    private String imageUrl;    // Ссылка на изображение продукта
    private Integer quantity;
}