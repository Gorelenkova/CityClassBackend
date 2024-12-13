package com.cityClass.CityClass.Repo;

import com.cityClass.CityClass.Model.Cart;
import com.cityClass.CityClass.Model.Cart_Item;
import com.cityClass.CityClass.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUser(User user);
    Cart findByUserId(Integer userId);
    Cart findCartByUserId(Integer userId);

}
