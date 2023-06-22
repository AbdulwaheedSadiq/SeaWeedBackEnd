package com.seaweed.seaweed.dto.login;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    public Long id;
    public String username;
    public int status;


}
