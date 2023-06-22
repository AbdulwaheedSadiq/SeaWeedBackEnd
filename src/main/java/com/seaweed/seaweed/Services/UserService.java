package com.seaweed.seaweed.Services;

import com.seaweed.seaweed.Exceptions.findUsersByIdNotFoundException;
import com.seaweed.seaweed.Models.Users;
import com.seaweed.seaweed.Repositories.UsersRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UsersRepos usersRepos;

    public Users insert(Users users){
        return usersRepos.save(users);
    }

    public List<Users> getAll(){
        return usersRepos.findAll();
    }


    public Users getById(Long id){
        return usersRepos.findUsersById(id).orElseThrow(()-> new findUsersByIdNotFoundException("The Resource Not  Found"));
    }

    public Long getTotal(){
        return usersRepos.count();
    }
}
