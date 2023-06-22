package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepos extends JpaRepository<Products,Long> {
    Optional<Products> findProductsById(Long id);
}
