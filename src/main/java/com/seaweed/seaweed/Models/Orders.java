package com.seaweed.seaweed.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sw_orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public Long id;
    public Long productId;
    public Long customerId;
    public Date createdDate;
    public int quantity;
    public int price;
    public String paymentReference;
    public String paymentStatus;
    public String orderStatus;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "order_product",joinColumns = @JoinColumn(name="order_product"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    public List<Products> products;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "order_customer",joinColumns = @JoinColumn(name="order_login"),
//            inverseJoinColumns = @JoinColumn(name = "login_id"))
//    public List<Login> logins;

}
