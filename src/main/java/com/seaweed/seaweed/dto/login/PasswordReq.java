package com.seaweed.seaweed.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordReq {
    public String newPassword;
    public String oldPassword;
}
