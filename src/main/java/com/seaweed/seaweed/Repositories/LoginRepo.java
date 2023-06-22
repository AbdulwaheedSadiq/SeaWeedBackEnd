package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<Login,Long>{
}
