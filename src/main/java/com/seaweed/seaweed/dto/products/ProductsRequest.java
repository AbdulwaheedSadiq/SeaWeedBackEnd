package com.seaweed.seaweed.dto.products;

import com.seaweed.seaweed.Models.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsRequest {
    public  String title;
    public  String image;
    public  String description;
    public  String location;
    public  int quantity;
    public  int price;
    public  String status;
    public  int type;
    public Long login;
}
