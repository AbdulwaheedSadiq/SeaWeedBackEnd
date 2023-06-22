package com.seaweed.seaweed.Services;


import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Repositories.LoginRepo;
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

    public Optional<Login> getById(Long id){
        return loginRepo.findById(id);
    }

    public Long getTotal(){
        return loginRepo.count();
    }

}
