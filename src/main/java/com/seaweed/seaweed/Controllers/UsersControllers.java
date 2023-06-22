package com.seaweed.seaweed.Controllers;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Users;
import com.seaweed.seaweed.Services.LoginService;
import com.seaweed.seaweed.Services.UserService;
import com.seaweed.seaweed.dto.users.UsersRequest;
import com.seaweed.seaweed.dto.users.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("users/")
public class UsersControllers {
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @PostMapping("create")
    public ResponseEntity<UsersResponse> create(@RequestBody UsersRequest request){

        Login login = new Login();
        login.setUsername(request.getEmail());
        login.setPassword("123");
        //login.setRoles(1);
        login.setStatus(1);


        Users u = new Users();
        u.setFirstName(request.getFirstName());
        u.setLastName(request.getLastName());
        u.setGender(request.getGender());
        u.setAddress(request.getAddress());
        u.setEmail(request.getEmail());
        u.setPhoneNumber(request.getPhoneNumber());
        //u.setCreatedDate();
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
