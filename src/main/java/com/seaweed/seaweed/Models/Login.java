package com.seaweed.seaweed.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    public Roles roles;

}
