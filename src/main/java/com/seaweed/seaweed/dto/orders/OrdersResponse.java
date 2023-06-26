package com.seaweed.seaweed.dto.orders;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Products;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {

    public Long id;
    public Products products;
    public Login customer;
    public LocalDateTime createdDate;
    public int quantity;
    public int price;
    public String paymentReference;
    public String paymentStatus;
    public String orderStatus;


}
