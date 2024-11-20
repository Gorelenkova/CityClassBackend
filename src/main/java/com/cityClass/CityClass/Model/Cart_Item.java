package com.cityClass.CityClass.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cart_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    private Integer quantity;
}
