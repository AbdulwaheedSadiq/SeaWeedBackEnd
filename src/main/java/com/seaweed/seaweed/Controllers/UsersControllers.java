package com.seaweed.seaweed.Controllers;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Users;
import com.seaweed.seaweed.Repositories.UsersRepos;
import com.seaweed.seaweed.Services.LoginService;
import com.seaweed.seaweed.Services.UserService;
import com.seaweed.seaweed.dto.login.SignUpRequest;
import com.seaweed.seaweed.dto.login.SignUpResponse;
import com.seaweed.seaweed.dto.users.UsersRequest;
import com.seaweed.seaweed.dto.users.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("users/")
public class UsersControllers {
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    @Autowired
    UsersRepos usersRepos;

    @PostMapping("create")
    public ResponseEntity<UsersResponse> create(@RequestBody UsersRequest request){

        Login login = new Login();
        login.setUsername(request.getEmail());
        login.setPassword(Base64.getEncoder().encodeToString(("123").getBytes()));
        login.setRole(2L);
        login.setStatus(1);


        Users u = new Users();
        u.setFirstName(request.getFirstName());
        u.setLastName(request.getLastName());
        u.setGender(request.getGender());
        u.setAddress(request.getAddress());
        u.setEmail(request.getEmail());
        u.setPhoneNumber(request.getPhoneNumber());
        u.setCreatedDate(LocalDateTime.now());
        u.setLogin(login);
        userService.insert(u);

        //response

        UsersResponse response = new UsersResponse();
        response.setFirstName(u.getFirstName());
        response.setLastName(u.getLastName());
        response.setGender(u.getGender());
        response.setAddress(u.getAddress());
        response.setEmail(u.getEmail());
        response.setPhoneNumber(u.getPhoneNumber());

        return ResponseEntity.ok(response);
    }


    @PostMapping("signIn")
    public ResponseEntity<SignUpResponse> signIn(@RequestBody SignUpRequest request){

        Optional<Login> u = loginService.getLogin(request.username, Base64.getEncoder().encodeToString((request.getPassword()).getBytes()));


        if (u.isPresent()){
            SignUpResponse response =  new SignUpResponse();
            response.setId(u.get().getId());
            response.setRole(u.get().getRole());
            response.setStatus(u.get().getStatus());

            Optional<Users> us = usersRepos.findUser(u.get().getId());
            response.setUsername(us.get().getFirstName() +" "+ us.get().getLastName());

            return ResponseEntity.ok(response);
        }else{
            SignUpResponse response =  new SignUpResponse();
            response.setStatus(0);
            response.setId(null);
            response.setUsername("notfound");

             return ResponseEntity.ok(response);
        }
    }



    @GetMapping("getAll")
    public ResponseEntity<List<Users>> getAll(){
        List<Users> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id){
        Users user = userService.getById(id);
        return  ResponseEntity.ok(user);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UsersResponse> update(@RequestBody UsersRequest request,@PathVariable Long id){

        Users u = userService.getById(id);

        u.setFirstName(request.getFirstName());
        u.setLastName(request.getLastName());
        u.setGender(request.getGender());
        u.setAddress(request.getAddress());
        u.setEmail(request.getEmail());
        u.setPhoneNumber(request.getPhoneNumber());
        userService.insert(u);


        //response

        UsersResponse response = new UsersResponse();
        response.setFirstName(u.getFirstName());
        response.setLastName(u.getLastName());
        response.setGender(u.getGender());
        response.setAddress(u.getAddress());
        response.setEmail(u.getEmail());
        response.setPhoneNumber(u.getPhoneNumber());


        return ResponseEntity.ok(response);
    }
}
