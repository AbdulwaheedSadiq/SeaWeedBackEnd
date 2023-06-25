package com.seaweed.seaweed.dto.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {
    public  String firstName;
    public  String lastName;
    public  String email;
    public  String phoneNumber;
    public  String address;
    public  String gender;
    public LocalDateTime createdDate;

}
