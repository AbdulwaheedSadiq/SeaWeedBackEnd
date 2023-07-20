package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Products;
import com.seaweed.seaweed.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepos extends JpaRepository<Products,Long> {
    Optional<Products> findProductsById(Long id);

    List<Products> findByInsertedBy(Login login);

    List<Products> findByStatus(String status);


    int countProductsByInsertedBy(Login insertedBy);
    @Query(value = "select COUNT(product_id) from sw_products",nativeQuery = true)
    int countProducts();
}
