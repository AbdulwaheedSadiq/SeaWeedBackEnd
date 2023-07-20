package com.seaweed.seaweed.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequestUpdate {
    public Long id;
    public LocalDateTime createdDate;
    public int quantity;
    public int price;
    public String paymentReference;
    public String paymentStatus;
    public String orderStatus;
    public Products products;
    public Login orderedBy;
}
