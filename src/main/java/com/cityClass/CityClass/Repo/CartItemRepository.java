package com.cityClass.CityClass.Repo;

import com.cityClass.CityClass.Model.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Item,Integer> {
    List<Cart_Item> findByCartId(Integer cartId);
}
