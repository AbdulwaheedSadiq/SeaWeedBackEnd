package com.seaweed.seaweed.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SignUpResponse {
    public Long id;
    public String username;
    public int status;
    public Long role;


    public SignUpResponse() {

    }
}
