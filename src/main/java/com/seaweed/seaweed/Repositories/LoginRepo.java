package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.dto.login.SignUpResponse;
import com.seaweed.seaweed.dto.users.UsersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<Login,Long>{
    @Query(value = "select * from sw_login where username =?1 AND password =?2 LIMIT 1;",nativeQuery = true)
    Optional<Login> findUser(String u, String p);

    Optional<Login> findLoginById(Long id);


}
