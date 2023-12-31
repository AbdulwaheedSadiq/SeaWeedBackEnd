package com.seaweed.seaweed.Controllers;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Products;
import com.seaweed.seaweed.Services.LoginService;
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

    @Autowired
    LoginService loginService;

    @PostMapping("create")
    public ResponseEntity<ProductsResponse> create(@RequestBody ProductsRequest request){
        Products p = new Products();
        Login login = new Login();
        login.setId(request.getLogin());

        p.setLocation(request.getLocation());
        p.setTitle(request.getTitle());
        p.setImage(request.getImage());
        p.setPrice(request.getPrice());
        p.setDescription(request.getDescription());
        p.setStatus("ForSale");
        p.setType(request.getType());
        p.setQuantity(request.getQuantity());
        p.setInsertedBy(login);
        productService.insert(p);

        //response
        ProductsResponse response = new ProductsResponse();
        response.setDescription(p.getDescription());
        response.setLocation(p.getLocation());
        response.setQuantity(p.getQuantity());
        response.setPrice(p.getPrice());
        response.setStatus(p.getStatus());
        response.setType(p.getType());
        response.setTitle(p.getTitle());
        response.setImage(p.getImage());

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

    @GetMapping("getByLoginId/{id}")
    public ResponseEntity<List<Products>> getByLoginId(@PathVariable Long id){
        Login l = loginService.getById(id);
        List<Products> products = productService.getByLogin(l);
        return ResponseEntity.ok(products);
    }

    @GetMapping("getByStatus")
    public ResponseEntity<List<Products>> getByStatus(){
        String status = "ForSale";
        List<Products> products = productService.getByStatus(status);
        return ResponseEntity.ok(products);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<ProductsResponse> update(@RequestBody ProductsRequest request,@PathVariable Long id){

        Products p = productService.getById(id);
        Login login = new Login();
        login.setId(request.getLogin());


        p.setLocation(request.getLocation());
        p.setTitle(request.getTitle());
        p.setImage(request.getImage());
        p.setPrice(request.getPrice());
        p.setType(request.getType());
        p.setDescription(request.getDescription());
        p.setQuantity(request.getQuantity());
        productService.insert(p);

        //response
        ProductsResponse response = new ProductsResponse();
        response.setDescription(p.getDescription());
        response.setLocation(p.getLocation());
        response.setQuantity(p.getQuantity());
        response.setPrice(p.getPrice());
        response.setStatus(p.getStatus());
        response.setTitle(p.getTitle());
        response.setImage(p.getImage());
        return ResponseEntity.ok(response);
    }



}
