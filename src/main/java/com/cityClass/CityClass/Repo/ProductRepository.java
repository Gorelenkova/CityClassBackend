package com.cityClass.CityClass.Repo;

import com.cityClass.CityClass.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.id IN :ids")
    List<Product> findByIds(@Param("ids") List<Integer> ids);
    Optional<Product> findById(@Param("ids") Integer ids);

}
