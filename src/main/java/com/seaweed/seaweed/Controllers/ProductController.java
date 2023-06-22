package com.seaweed.seaweed.Controllers;

import com.seaweed.seaweed.Models.Products;
import com.seaweed.seaweed.Services.ProductService;
import com.seaweed.seaweed.dto.products.ProductsRequest;
import com.seaweed.seaweed.dto.products.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("products/")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("create")
    public ResponseEntity<ProductsResponse> create(@RequestBody ProductsRequest request){
        Products p = new Products();

        p.setLocation(request.getLocation());
        p.setTitle(request.getTitle());
        p.setPrice(request.getPrice());
        p.setDescription(request.getDescription());
        p.setStatus("ForSale");
        p.setQuantity(request.getQuantity());
        p.setLogin(request.getLogin());
        productService.insert(p);

        //response
        ProductsResponse response = new ProductsResponse();
        response.setDescription(p.getDescription());
        response.setLocation(p.getLocation());
        response.setQuantity(p.getQuantity());
        response.setPrice(p.getPrice());
        response.setStatus(p.getStatus());
        response.setTitle(p.getTitle());

        return ResponseEntity.ok(response);

    }

    @GetMapping("getAll")
    public ResponseEntity<List<Products>> getAll(){
        List<Products> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Products> getById(@PathVariable Long id){
        Products products = productService.getById(id);
        return ResponseEntity.ok(products);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductsResponse> update(@RequestBody ProductsRequest request,@PathVariable Long id){

        Products p = productService.getById(id);


        p.setLocation(request.getLocation());
        p.setTitle(request.getTitle());
        p.setPrice(request.getPrice());
        p.setDescription(request.getDescription());
        p.setStatus("ForSale");
        p.setQuantity(request.getQuantity());
        p.setLogin(request.getLogin());
        productService.insert(p);

        //response
        ProductsResponse response = new ProductsResponse();
        response.setDescription(p.getDescription());
        response.setLocation(p.getLocation());
        response.setQuantity(p.getQuantity());
        response.setPrice(p.getPrice());
        response.setStatus(p.getStatus());
        response.setTitle(p.getTitle());

        return ResponseEntity.ok(response);
    }
}
