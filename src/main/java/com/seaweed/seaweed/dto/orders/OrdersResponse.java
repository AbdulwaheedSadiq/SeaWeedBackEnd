package com.seaweed.seaweed.dto.orders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {

    public Long id;
    public Long productId;
    public Long customerId;
    public Date createdDate;
    public int quantity;
    public int price;
    public String paymentReference;
    public String paymentStatus;
    public String orderStatus;


}
