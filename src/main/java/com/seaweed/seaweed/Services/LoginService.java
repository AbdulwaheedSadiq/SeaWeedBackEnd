package com.seaweed.seaweed.Services;


import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Repositories.LoginRepo;
import com.seaweed.seaweed.dto.login.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    private LoginRepo loginRepo;

    @Autowired
    public LoginService(LoginRepo loginRepo){
        this.loginRepo = loginRepo;
    }

    public Login insert(Login login){
        return loginRepo.save(login);
    }

    public List<Login> getAll(){
        return loginRepo.findAll();
    }

    public Optional<Login> getLogin(String u, String p){
        return loginRepo.findUser(u,p);
    }

    public Long getTotal(){
        return loginRepo.count();
    }

}
