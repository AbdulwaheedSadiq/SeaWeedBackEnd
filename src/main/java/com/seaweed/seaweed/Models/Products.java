package com.seaweed.seaweed.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sw_products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public  Long id;
    public  String title;
    @Column(length = 70000)
    public  String image;
    public  String description;
    public  String location;
    public  int quantity;
    public  int price;
    public  int type;
    public  String status;

    @ManyToOne
    @JoinColumn(name = "inserted_by",referencedColumnName = "login_id")
    public Login insertedBy;

//    @OneToMany(mappedBy = "products")
//    public List<Orders> orders;

}
