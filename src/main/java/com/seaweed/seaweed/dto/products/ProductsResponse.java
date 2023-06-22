package com.seaweed.seaweed.dto.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponse{

    public  Long id;
    public  String title;
    public  String description;
    public  String location;
    public  int quantity;
    public  int price;
    public  String status;


}
