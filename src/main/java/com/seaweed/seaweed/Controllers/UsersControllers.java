package com.seaweed.seaweed.Controllers;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Users;
import com.seaweed.seaweed.Repositories.UsersRepos;
import com.seaweed.seaweed.Services.LoginService;
import com.seaweed.seaweed.Services.UserService;
import com.seaweed.seaweed.dto.login.PasswordReq;
import com.seaweed.seaweed.dto.login.SignUpRequest;
import com.seaweed.seaweed.dto.login.SignUpResponse;
import com.seaweed.seaweed.dto.users.UsersRequest;
import com.seaweed.seaweed.dto.users.UsersResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    @Autowired
    private JavaMailSender mailSender;

    public UsersControllers(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping("create")
    public ResponseEntity<UsersResponse> create(@RequestBody UsersRequest request) throws MessagingException, UnsupportedEncodingException {

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

        sendVerificationEmail(request.getEmail(), request.getFirstName()+" "+request.getLastName(), request.getEmail(), "123");

        UsersResponse response = new UsersResponse();
        response.setFirstName(u.getFirstName());
        response.setLastName(u.getLastName());
        response.setGender(u.getGender());
        response.setAddress(u.getAddress());
        response.setEmail(u.getEmail());
        response.setPhoneNumber(u.getPhoneNumber());

        return ResponseEntity.ok(response);
    }

    private void sendVerificationEmail(String email,String name,String username,String password)
            throws MessagingException, UnsupportedEncodingException {
        String fromAddress = "SeaweedSystem";
        String senderName = "Notification email";
        String subject = "Your account has been created";
        String content = "Dear [[name]],<br>"
                + "Please use your USERNAME as [[username]] and [[password]] as PASSWORD to login in the system<br>"
                + "<h3><a href=\"[[URL]]\">GO TO LOGIN SCREEN</a></h3>"
                + "Thank you,<br>"
                + "Rahima SysAdmin.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);

        content = content.replace("[[name]]", name);
        content = content.replace("[[username]]", username);
        content = content.replace("[[password]]", password);

//        String loginURL = siteURL + "url for login page";

//        content = content.replace("[[URL]]", loginURL);

        helper.setText(content, true);

        mailSender.send(message);

        System.out.println("Email has been sent");
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

    @PutMapping("updatePass/{id}")
    public ResponseEntity<Integer> updatePass(@RequestBody PasswordReq req, @PathVariable long id){
        String userPass = Base64.getEncoder().encodeToString((req.getOldPassword()).getBytes());
        Login u = loginService.getById(id);

        Integer res;
        if(u.getPassword().equals(userPass)) {

            u.setPassword(Base64.getEncoder().encodeToString((req.getNewPassword()).getBytes()));
            loginService.insert(u);

            res = 200;
        }else{
            res = 500;
        }

        return ResponseEntity.ok(res);
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
