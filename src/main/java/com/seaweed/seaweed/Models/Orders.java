package com.seaweed.seaweed.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    public LocalDateTime createdDate;
    public int quantity;
    public int price;
    public String paymentReference;
    public String paymentStatus;
    public String orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Login login;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Products products;

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
