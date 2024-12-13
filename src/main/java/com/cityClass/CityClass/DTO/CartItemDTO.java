package com.cityClass.CityClass.DTO;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private String productName;
    private Integer quantity;

    // Конструкторы, геттеры и сеттеры
    public CartItemDTO(Long id, String productName, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

