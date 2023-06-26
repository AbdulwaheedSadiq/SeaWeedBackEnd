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
@Table(name = "sw_login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    public Long id;
    public String username;
    public String password;
    public int status; //1 for active 0 for inactive
    @Column(name = "role_id")
    public Long role;

//    @OneToMany(mappedBy = "login")
//    public List<Orders> orders;
}
