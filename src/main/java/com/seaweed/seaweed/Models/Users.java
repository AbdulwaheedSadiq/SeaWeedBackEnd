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
@Table(name = "sw_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long id;
    public  String firstName;
    public  String lastName;
    public  String email;
    public  String phoneNumber;
    public  String address;
    public  String gender;
    public Date createdDate;
    public  String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="login_id",referencedColumnName = "login_id")
    public Login login;
}
