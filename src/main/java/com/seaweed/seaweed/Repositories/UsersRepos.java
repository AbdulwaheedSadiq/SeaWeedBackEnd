package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepos extends JpaRepository<Users,Long> {
    Optional<Users> findUsersById(Long id);

    @Query(value = "select * from sw_users where user_id =?1 LIMIT 1;",nativeQuery = true)
    Optional<Users> findUser(Long id);
    @Query(value = "select COUNT(user_id) from sw_users",nativeQuery = true)
   int countUsers();
}
