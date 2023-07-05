package com.seaweed.seaweed.Services;
import com.seaweed.seaweed.Exceptions.findProductsByIdNotFoundException;
import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Products;
import com.seaweed.seaweed.Repositories.ProductsRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductsRepos productsRepos;

    public Products insert(Products products){
        return productsRepos.save(products);
    }

    public List<Products> getAll(){
        return productsRepos.findAll();
    }

    public Optional<Products> getByIds(Long id){
        return productsRepos.findById(id);
    }

    public Products getById(Long id){
        return productsRepos.findProductsById(id).orElseThrow(()-> new findProductsByIdNotFoundException("The Resorce with "+id+"Not Found"));
    }

    public List<Products> getByLogin(Login login){
        return productsRepos.findByInsertedBy(login);
    }

    public Long getTotal(){
        return productsRepos.count();
    }

    public List<Products> getByStatus(String status) {
        return productsRepos.findByStatus(status);
    }
}
